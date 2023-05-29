package com.xunmeng.youxuan.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.domain.YxProductInfo;
import com.xunmeng.youxuan.domain.YxShopInfo;
import com.xunmeng.youxuan.domain.YxShoppingCart;
import com.xunmeng.youxuan.enums.ConstantEnum;
import com.xunmeng.youxuan.enums.ErrorCodeEnum;
import com.xunmeng.youxuan.logic.BaseLogic;
import com.xunmeng.youxuan.mapper.YxShippingCartMapper;
import com.xunmeng.youxuan.requestqo.CartNumQo;
import com.xunmeng.youxuan.requestqo.CommonIdQo;
import com.xunmeng.youxuan.requestqo.ShoppingCartAddQo;
import com.xunmeng.youxuan.responsedto.CartShopDto;
import com.xunmeng.youxuan.responsedto.CartUserDto;
import com.xunmeng.youxuan.responsedto.ShoppingCartDto;
import com.xunmeng.youxuan.responsedto.ShoppingCartSumDto;
import com.xunmeng.youxuan.service.IYxProductInfoService;
import com.xunmeng.youxuan.service.IYxShopInfoService;
import com.xunmeng.youxuan.service.IYxShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: YxShoppingCartServiceImpl
 * Package: com.xunmeng.youxuan.service.Impl
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/26 10:06
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class YxShoppingCartServiceImpl extends ServiceImpl<YxShippingCartMapper, YxShoppingCart>
        implements IYxShoppingCartService {
    private final BaseLogic baseLogic;
    private final IYxProductInfoService yxProductInfoService;
    private final IYxShopInfoService yxShopInfoService;

    @Override
    public Result<T> addToCart(ShoppingCartAddQo requestModel) {
        UserInfo userInfo = baseLogic.getCurrentUserInfo();
        if (userInfo == null) {
            return Result.newFailedResponse(ErrorCodeEnum.SESSION_TIMEOUT);
        }

        // 先检查有没有同样商品同购买用户的订单，就是有没有相同订单
        LambdaQueryWrapper<YxShoppingCart> queryWrapper = new LambdaQueryWrapper<YxShoppingCart>()
                .eq(YxShoppingCart::getProductId, requestModel.getProductId())
                .eq(YxShoppingCart::getUserId, userInfo.getUserId())
                .eq(YxShoppingCart::getShopId, requestModel.getShopId())
                .eq(YxShoppingCart::getDataStatus, ConstantEnum.NORMAL)
                .and(StringUtils.isNotEmpty(requestModel.getOtherSku()), wrapper -> wrapper
                        .eq(YxShoppingCart::getOtherSku, requestModel.getOtherSku()));
        // 该用户该商品购物车订单
        List<YxShoppingCart> carts = this.list(queryWrapper);
        //商品信息
        YxProductInfo productInfo = yxProductInfoService.getById(requestModel.getProductId());
        if (productInfo == null || !productInfo.getShopId().equals(requestModel.getShopId())) {
            return Result.newFailedResponse(ErrorCodeEnum.FAIL);
        }
        if (productInfo.getStock() == null || productInfo.getStock() < 1) {
            return Result.newFailedResponse(ErrorCodeEnum.FAIL,
                    productInfo.getProductName() + "商品库存不足，无法加入购物车");
        }

        YxShoppingCart updateModel = new YxShoppingCart();
        if (carts != null && carts.size() > 0) {
            // 有同样订单的直接在原有订单上修改数量
            updateModel.setCartId(carts.get(0).getCartId());
            updateModel.setBuyCount((carts.get(0).getBuyCount() + 1));

            if (productInfo.getStock() < updateModel.getBuyCount()) {
                return Result.newFailedResponse(ErrorCodeEnum.FAIL, productInfo.getProductName()
                        + "商品库存不足，无法加入购物车");
            }
        } else {
            updateModel.setProductId(requestModel.getProductId())
                    .setUserId(userInfo.getUserId())
                    .setProductName(productInfo.getProductName())
                    .setShopId(requestModel.getShopId())
                    .setBuyCount(1)
                    .setPrice(productInfo.getPrice())
                    .setProductImage(productInfo.getImagePath())
                    .setDataSource(ConstantEnum.NORMAL)
                    .setAddTime(LocalDateTime.now())
                    //商品附加备注信息   如果为空保持不变
                    .setOtherSku(StringUtils.isNotEmpty(requestModel.getOtherSku()) ?
                            requestModel.getOtherSku() : updateModel.getOtherSku());
        }
        // 所以修改时间单独抽出来
        updateModel.setUpdateTime(LocalDateTime.now());
        if (this.saveOrUpdate(updateModel)) {
            return Result.newSuccessResponse(ErrorCodeEnum.SUCCESS);
        }
        return Result.newFailedResponse(ErrorCodeEnum.FAIL);
    }

    @Override
    public Result<T> clearCart(CommonIdQo requestModel) {
        UserInfo userInfo = baseLogic.getCurrentUserInfo();
        if (userInfo == null) {
            return Result.newFailedResponse(ErrorCodeEnum.SESSION_TIMEOUT);
        }

        List<YxShoppingCart> carts = this.list(new LambdaQueryWrapper<YxShoppingCart>()
                .eq(YxShoppingCart::getUserId, userInfo.getUserId())
                .eq(YxShoppingCart::getDataStatus, ConstantEnum.NORMAL)
                .eq(YxShoppingCart::getShopId, requestModel.getId()));

        if (carts != null && carts.size() > 0) {
            carts.forEach(item -> {
                        item.setDataStatus(ConstantEnum.DELETE);
                        item.setUpdateTime(LocalDateTime.now());
                    }
            );
            if (this.updateBatchById(carts)) {
                return Result.newSuccessResponse(ErrorCodeEnum.SUCCESS);
            }
            return Result.newFailedResponse(ErrorCodeEnum.FAIL);
        }
        return Result.newSuccessResponse(ErrorCodeEnum.SUCCESS);
    }

    @Override
    public Result<T> cartNumberChanger(CartNumQo requestModel) {
        YxShoppingCart cart = this.getById(requestModel.getCartId());
        if (cart == null) {
            return Result.newFailedResponse(ErrorCodeEnum.INFO_NOT_EXIST);
        }

        YxProductInfo productInfo = yxProductInfoService.getById(cart.getProductId());
        if (productInfo == null || productInfo.getStock() == null) {
            return Result.newFailedResponse(ErrorCodeEnum.FAIL);
        }

        int buyCount = requestModel.getNumber() + cart.getBuyCount();
        if (productInfo.getStock() < buyCount) {
            return Result.newFailedResponse(ErrorCodeEnum.FAIL, productInfo.getProductName()
                    + "商品超出库存，无法加入购物车！");
        }
        if (buyCount < 0) {
            cart.setDataStatus(ConstantEnum.DELETE);
        } else {
            cart.setBuyCount(buyCount);
        }

        if (this.updateById(cart)) {
            return Result.newSuccessResponse(ErrorCodeEnum.SUCCESS);
        }
        return Result.newFailedResponse(ErrorCodeEnum.FAIL);
    }

    @Override
    public Result<ShoppingCartSumDto> cartList(CommonIdQo requestModel) {
        UserInfo userInfo = baseLogic.getCurrentUserInfo();
        if (userInfo == null) {
            return Result.newFailedResponse(ErrorCodeEnum.SESSION_TIMEOUT);
        }

        ShoppingCartSumDto resultData = null;
        List<ShoppingCartDto> result = null;
        List<YxShoppingCart> carts = this.list(new LambdaQueryWrapper<YxShoppingCart>()
                .eq(YxShoppingCart::getUserId, userInfo.getUserId())
                .eq(YxShoppingCart::getDataStatus, ConstantEnum.NORMAL)
                .eq(YxShoppingCart::getShopId, requestModel.getId()));

        if (carts != null && carts.size() > 0) {
            BigDecimal sumPrice = BigDecimal.ZERO;
            for (YxShoppingCart data : carts) {
                sumPrice = sumPrice.add(data.getPrice().multiply(new BigDecimal(data.getBuyCount())));
            }
            result = JSONArray.parseArray(JSONArray.toJSONString(carts), ShoppingCartDto.class);

            resultData = new ShoppingCartSumDto()
                    .setCartList(result)
                    .setSumPrice(sumPrice);
        }
        return Result.newSuccessResponse(resultData);
    }

    @Override
    public Result<List<CartShopDto>> cartShopList() {
        UserInfo userInfo = baseLogic.getCurrentUserInfo();
        if (userInfo == null) {
            return Result.newFailedResponse(ErrorCodeEnum.SESSION_TIMEOUT);
        }
        // 查询该用户购物车订单
        List<YxShoppingCart> shoppingCarts = this.list(new LambdaQueryWrapper<YxShoppingCart>()
                .select(YxShoppingCart::getShopId, YxShoppingCart::getBuyCount)
                .eq(YxShoppingCart::getUserId, userInfo.getUserId())
                .eq(YxShoppingCart::getDataStatus, ConstantEnum.NORMAL)
                .groupBy(YxShoppingCart::getShopId));

        // 购物车为空返回
        if (CollectionUtils.isEmpty(shoppingCarts)) {
            return Result.newFailedResponse(ErrorCodeEnum.SHOPPING_CART_ISEMPTY);
        }

        List<YxShopInfo> shopInfos = yxShopInfoService.list(new LambdaQueryWrapper<YxShopInfo>()
                .eq(YxShopInfo::getDataStatus, ConstantEnum.SHOP_STATUS_PASSED));
        Map<Long, String> shopMap = shopInfos.stream()
                .collect(Collectors.toMap(YxShopInfo::getShopId, YxShopInfo::getShopName));
        // TODO 这种操作方式代码还需记忆
        List<CartShopDto> result = shoppingCarts.stream()
                .map(cart -> {
                    CartShopDto shopDto = new CartShopDto();
                    shopDto.setShopId(cart.getShopId());
                    shopDto.setBuyCount(cart.getBuyCount());
                    shopDto.setShopName(shopMap.getOrDefault(cart.getShopId(), ""));
                    return shopDto;
                })
                .collect(Collectors.toList());
        return Result.newSuccessResponse(result);
    }

    @Override
    public Result<List<CartUserDto>> userCartList() {
        UserInfo userInfo = baseLogic.getCurrentUserInfo();
        if (userInfo == null) {
            return Result.newFailedResponse(ErrorCodeEnum.SESSION_TIMEOUT);
        }

        List<YxShoppingCart> shoppingCarts = this.list(new LambdaQueryWrapper<YxShoppingCart>()
                .eq(YxShoppingCart::getUserId, userInfo.getUserId())
                .eq(YxShoppingCart::getDataStatus, ConstantEnum.NORMAL));

        if (CollectionUtils.isEmpty(shoppingCarts)) {
            return Result.newSuccessResponse(ErrorCodeEnum.SHOPPING_CART_ISEMPTY);
        }

        List<Long> shopIds = shoppingCarts.stream().map(YxShoppingCart::getShopId).collect(Collectors.toList());
        // 查询出订单中出现的shopId的商店的具体信息，  测试服yx_shop_Info就不全，只有4个店的信息
        List<YxShopInfo> shopInfos = yxShopInfoService.list(new LambdaQueryWrapper<YxShopInfo>()
                .in(YxShopInfo::getShopId, shopIds));
        // 店铺Id对应该店铺的所有订单
        Map<Long, List<YxShoppingCart>> shopMap = shoppingCarts.stream()
                .collect(Collectors.groupingBy(YxShoppingCart::getShopId));

        List<CartUserDto> result = shopMap.keySet().stream()
                .map(shopId -> {
                    CartUserDto cartUserDto = new CartUserDto();
                    cartUserDto.setShopId(shopId);

                    List<YxShopInfo> subShop = shopInfos.stream()
                            .filter(item -> shopId.equals(item.getShopId())).collect(Collectors.toList());
                    if (subShop.size() > 0) {
                        cartUserDto.setShopName(subShop.get(0).getShopName());
                    }

                    List<ShoppingCartDto> products = shopMap.get(shopId).stream()
                            .map(cart -> JSONArray.parseObject(JSONArray.toJSONString(cart)
                                    , ShoppingCartDto.class))
                            .collect(Collectors.toList());
                    cartUserDto.setProducts(products);

                    return cartUserDto;
                })
                .collect(Collectors.toList());

        return Result.newSuccessResponse(result);
    }
}
