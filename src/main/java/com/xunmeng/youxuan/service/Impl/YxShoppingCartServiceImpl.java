package com.xunmeng.youxuan.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.base.Results;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.domain.YxProductInfo;
import com.xunmeng.youxuan.domain.YxShoppingCart;
import com.xunmeng.youxuan.enums.ConstantEnum;
import com.xunmeng.youxuan.enums.ErrorCodeEnum;
import com.xunmeng.youxuan.logic.BaseLogic;
import com.xunmeng.youxuan.mapper.YxShippingCartMapper;
import com.xunmeng.youxuan.requestqo.CartNumQo;
import com.xunmeng.youxuan.requestqo.CommonIdQo;
import com.xunmeng.youxuan.requestqo.ShoppingCartAddQo;
import com.xunmeng.youxuan.service.IYxProductInfoService;
import com.xunmeng.youxuan.service.IYxShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    @Override
    public Response addToCart(ShoppingCartAddQo requestModel) {
        UserInfo userInfo = baseLogic.getCurrentUserInfo();
        if(userInfo == null){
            return Results.newFailedResponse(ErrorCodeEnum.SESSION_TIMEOUT);
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
        if(productInfo == null || !productInfo.getShopId().equals(requestModel.getShopId())){
            return Results.newFailedResponse(ErrorCodeEnum.FAIL);
        }
        if(productInfo.getStock() == null || productInfo.getStock() < 1){
            return Results.newFailedResponse(ErrorCodeEnum.FAIL.getCode(),
                    productInfo.getProductName() + "商品库存不足，无法加入购物车");
        }

        YxShoppingCart updateModel = new YxShoppingCart();
        if(carts != null && carts.size() > 0){
            // 有同样订单的直接在原有订单上修改数量
            updateModel.setCartId(carts.get(0).getCartId());
            updateModel.setBuyCount((carts.get(0).getBuyCount() + 1));

            if(productInfo.getStock() < updateModel.getBuyCount()){
                return Results.newFailedResponse(ErrorCodeEnum.FAIL, productInfo.getProductName()
                        + "商品库存不足，无法加入购物车");
            }
        }else{
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
        if(this.saveOrUpdate(updateModel)){
            return Results.newSuccessResponse(ErrorCodeEnum.SUCCESS);
        }
        return Results.newFailedResponse(ErrorCodeEnum.FAIL);
    }

    @Override
    public Response clearCart(CommonIdQo requestModel) {
        UserInfo userInfo = baseLogic.getCurrentUserInfo();
        if(userInfo == null){
            return Results.newFailedResponse(ErrorCodeEnum.SESSION_TIMEOUT);
        }

        List<YxShoppingCart> carts = this.list(new LambdaQueryWrapper<YxShoppingCart>()
                .eq(YxShoppingCart::getUserId, userInfo.getUserId())
                .eq(YxShoppingCart::getDataStatus, ConstantEnum.NORMAL)
                .eq(YxShoppingCart::getShopId, requestModel.getId()));

        if(carts != null && carts.size() > 0){
            carts.forEach(item ->{
                item.setDataStatus(ConstantEnum.DELETE);
                item.setUpdateTime(LocalDateTime.now());
                    }
            );
            if(this.updateBatchById(carts)){
                return Results.newSuccessResponse(ErrorCodeEnum.SUCCESS);
            }
            return Results.newFailedResponse(ErrorCodeEnum.FAIL);
        }
        return Results.newSuccessResponse(ErrorCodeEnum.SUCCESS);
    }

    @Override
    public Response cartNumberChanger(CartNumQo requestModel) {
        YxShoppingCart cart = this.getById(requestModel.getCartId());
        System.out.println(cart);
        if(cart == null){
            return Results.newFailedResponse(ErrorCodeEnum.INFO_NOT_EXIST);
        }

        YxProductInfo productInfo = yxProductInfoService.getById(cart.getProductId());
        if(productInfo == null || productInfo.getStock() == null){
            return Results.newFailedResponse(ErrorCodeEnum.FAIL);
        }

        int buyCount = requestModel.getNumber() + cart.getBuyCount();
        if(productInfo.getStock() < buyCount){
            return  Results.newFailedResponse(ErrorCodeEnum.FAIL.getCode(), productInfo.getProductName()
                    + "商品超出库存，无法加入购物车！");
        }
        if(buyCount < 0){
            cart.setDataStatus(ConstantEnum.DELETE);
        }else {
            cart.setBuyCount(buyCount);
        }

        if(this.updateById(cart)){
            return Results.newSuccessResponse(ErrorCodeEnum.SUCCESS);
        }
        return Results.newFailedResponse(ErrorCodeEnum.FAIL);
    }
}
