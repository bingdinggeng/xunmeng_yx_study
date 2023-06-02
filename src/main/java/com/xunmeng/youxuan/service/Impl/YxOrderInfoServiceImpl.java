package com.xunmeng.youxuan.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.domain.*;
import com.xunmeng.youxuan.enums.ConstantEnum;
import com.xunmeng.youxuan.enums.ErrorCodeEnum;
import com.xunmeng.youxuan.logic.BaseLogic;
import com.xunmeng.youxuan.mapper.YxOrderInfoMapper;
import com.xunmeng.youxuan.requestqo.OrderQueryQo;
import com.xunmeng.youxuan.requestqo.OrderSaveQo;
import com.xunmeng.youxuan.responsedto.OrderInfoDto;
import com.xunmeng.youxuan.responsedto.OrderItemDto;
import com.xunmeng.youxuan.service.IYxOrderInfoService;
import com.xunmeng.youxuan.service.IYxOrderItemService;
import com.xunmeng.youxuan.service.IYxShopInfoService;
import com.xunmeng.youxuan.service.IYxShoppingCartService;
import com.xunmeng.youxuan.utils.DataUtil;
import com.xunmeng.youxuan.utils.DateTimeUtil;
import com.xunmeng.youxuan.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ClassName: YxOrderInfoServiceImpl
 * Package: com.xunmeng.youxuan.service.Impl
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/29 15:12
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class YxOrderInfoServiceImpl extends ServiceImpl<YxOrderInfoMapper, YxOrderInfo> implements IYxOrderInfoService {
    private final BaseLogic baseLogic;
    private final IYxShopInfoService yxShopInfoService;
    private final IYxOrderItemService yxOrderItemService;
    private IYxShoppingCartService yxShoppingCartService;

    @Override
    public Result<IPage<OrderInfoDto>> orderList(OrderQueryQo requestModel) {
        UserInfo userInfo = baseLogic.getCurrentUserInfo();
        if(userInfo == null || userInfo.getRoleType() == ConstantEnum.USER_SHOP){
            return Result.newFailedResponse(ErrorCodeEnum.PERMISSION_DENIED);
        }

        PageUtil.initRequestPage(requestModel);
        LambdaQueryWrapper<YxOrderInfo> queryWrapper = new LambdaQueryWrapper<YxOrderInfo>()
                .ge(requestModel.getStartDate() != null, YxOrderInfo::getAdvanceTime, requestModel.getStartDate())
                .lt(requestModel.getEndDate() != null, YxOrderInfo::getAdvanceTime,
                        requestModel.getEndDate().plusDays(1))
                .eq(YxOrderInfo::getUserId, userInfo.getUserId())
                .eq(StringUtils.isNotEmpty(requestModel.getDataStatus()) &&
                        !requestModel.getDataStatus().equals(ConstantEnum.ORDER_STATUS_ALL.toString()),
                        YxOrderInfo::getDataStatus, requestModel.getDataStatus());
        if(StringUtils.isNotEmpty(requestModel.getSortType()) &&
                ConstantEnum.SORT_ASC.equalsIgnoreCase(requestModel.getSortType())){
            queryWrapper = queryWrapper.orderByAsc(YxOrderInfo::getAddTime);
        }else{
            queryWrapper = queryWrapper.orderByDesc(YxOrderInfo::getAddTime);
        }

        IPage<YxOrderInfo> dataList = this.page(new Page<>(requestModel.getPageIndex(),requestModel.getPageSize())
                , queryWrapper);

        if (dataList == null || dataList.getRecords() == null || dataList.getRecords().isEmpty()) {
            return Result.newFailedResponse(ErrorCodeEnum.ORDER_INFO_EMPTY);
        }
        // 出现的订单里所有的店铺Id 不重复的
        List<Long> shopIds = dataList.getRecords().stream().map(YxOrderInfo::getShopId).collect(Collectors.toList());
        //根据shopIds列表中的值，查询数据库表 YxShopInfo 中与之匹配的记录，并将结果存储在 shops 列表中
        List<YxShopInfo> shops = yxShopInfoService.list(new LambdaQueryWrapper<YxShopInfo>()
                .in(shopIds.size() > 0, YxShopInfo::getShopId,shopIds , null));

        IPage<OrderInfoDto> result = new Page<OrderInfoDto>()
                .setTotal(dataList.getTotal())
                .setCurrent(dataList.getCurrent())
                .setPages(dataList.getPages())
                .setSize(dataList.getSize())
                .setRecords(JSONArray.parseArray(JSONArray.toJSONString(dataList.getRecords()), OrderInfoDto.class));
        // 获取订单商品列表
        List<Long> orderIds = dataList.getRecords().stream().map(YxOrderInfo::getOrderId).collect(Collectors.toList());
        List<YxOrderItem> itemList = yxOrderItemService.list(new LambdaQueryWrapper<YxOrderItem>()
                .in(YxOrderItem::getOrderId, orderIds)
                .eq(YxOrderItem::getDataStatus, ConstantEnum.NORMAL));
        // 构建 orderId到subOrderItem的映射关系
        Map<Long, List<YxOrderItem>> orderIdToItemsMap = itemList.stream()
                .collect(Collectors.groupingBy(YxOrderItem::getOrderId));
        //Function.identity() 是一个静态方法引用，表示使用对象本身作为值,将流中的每个YxShopInfo对象以其shopId作为键，
        // 以YxShopInfo对象本身作为值，创建一个Map<Long, YxShopInfo>。这样就建立了一个通过shopId快速查找YxShopInfo对象的映射关系。
        Map<Long, YxShopInfo> shopIdToShopInfoMap = shops.stream()
                .collect(Collectors.toMap(YxShopInfo::getShopId, Function.identity()));

        result.getRecords().forEach(data -> {
                    List<YxOrderItem> subOrderItem = orderIdToItemsMap.get(data.getOrderId());
                    if (subOrderItem != null && !subOrderItem.isEmpty()) {
                        data.setItems(JSONArray.parseArray(JSONArray.toJSONString(subOrderItem), OrderItemDto.class));
                    }

                    YxShopInfo shopInfo = shopIdToShopInfoMap.get(data.getShopId());
                    if (shopInfo != null) {
                        data.setShopTel(shopInfo.getShopName());
                    }
                }
        );
        return Result.newSuccessResponse(result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<OrderInfoDto> saveOrder(OrderSaveQo requestModel) {
        baseLogic.checkRepeat(DataUtil.getMd5String(JSON.toJSONString(requestModel)));
        UserInfo userInfo = baseLogic.getCurrentUserInfo();
        if(userInfo == null || userInfo.getRoleType() == ConstantEnum.USER_SHOP){
            return Result.newFailedResponse(ErrorCodeEnum.PERMISSION_DENIED);
        }

        YxShopInfo shopInfo = yxShopInfoService.getById(requestModel.getShopId());
        if (shopInfo == null) {
            return Result.newFailedResponse(ErrorCodeEnum.INFO_NOT_EXIST);
        }
        if(!ConstantEnum.SHOP_STATUS_PASSED.equals(shopInfo.getDataStatus())){
            return Result.newFailedResponse(ErrorCodeEnum.ORDER_SHOP_CLOSE);
        }
        if (requestModel.getCartIds() == null || requestModel.getCartIds().size() < 1) {
            return Result.newFailedResponse(ErrorCodeEnum.SHOPPING_CART_NOT_EXIST);
        }

        LambdaQueryWrapper<YxShoppingCart> queryWrapper = new LambdaQueryWrapper<YxShoppingCart>()
                .in(YxShoppingCart::getCartId, requestModel.getCartIds())
                .eq(YxShoppingCart::getShopId, requestModel.getShopId())
                .eq(YxShoppingCart::getUserId, userInfo.getUserId())
                .eq(YxShoppingCart::getDataStatus, ConstantEnum.NORMAL);
        List<YxShoppingCart> carts = yxShoppingCartService.list(queryWrapper);
        if(carts == null || carts.size() < 1 || carts.size() != requestModel.getCartIds().size()){
            return Result.newFailedResponse(ErrorCodeEnum.SHOPPING_CART_LIST_ERROR);
        }

        // 开始生成订单
        String orderNo = DateTimeUtil.getYearMonthDay() + userInfo.getUserId() + DataUtil.getRandomNum(0);
        YxOrderInfo yxOrderInfo = new YxOrderInfo()
                .setUserId(userInfo.getUserId())
                .setShopId(requestModel.getShopId())
                .setOrderNo(orderNo)
                .setShopName(shopInfo.getShopName())
                .setDataSource(userInfo.getDataSource())
                .setAvatar(shopInfo.getAvatar());
        BigDecimal sumPrice = BigDecimal.ZERO;
        Integer itemCount = 0;

        // 生成订单商品列表
        List<YxOrderItem> itemList = new ArrayList<>();
        for (YxShoppingCart cart : carts) {
            //数目
            itemCount += cart.getBuyCount();
            //总价
            BigDecimal subPrice = cart.getPrice().multiply(new BigDecimal(cart.getBuyCount()));
            sumPrice = sumPrice.add(subPrice);
            YxOrderItem orderItem = new YxOrderItem()
                    .setOrderNo(orderNo)
                    .setUserId(userInfo.getUserId())
                    .setShopId(requestModel.getShopId())
                    .setProductId(cart.getProductId())
                    .setProductName(cart.getProductName())
                    .setOtherSku(cart.getOtherSku())
                    .setPrice(cart.getPrice())
                    .setBuyCount(cart.getBuyCount())
                    .setDataStatus(ConstantEnum.NORMAL)
                    .setAddTime(LocalDateTime.now())
                    .setUpdateTime(LocalDateTime.now())
                    .setOperatorId(userInfo.getUserId())
                    .setProductImage(cart.getProductImage());
            itemList.add(orderItem);
        }
        yxOrderInfo.setOrderNum(0)
                .setBuyCount(itemCount)
                .setTotalAmount(sumPrice)
                .setDataStatus(ConstantEnum.NORMAL)
                .setAddTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now());
        if (this.save(yxOrderInfo)) {
            itemList.forEach(item -> {
                item.setOrderId(yxOrderInfo.getOrderId());
            });
            if (!yxOrderItemService.saveBatch(itemList)) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Result.newFailedResponse(ErrorCodeEnum.FAIL);
            }
            //删除购物车
            carts.forEach(item -> {
                item.setDataStatus(ConstantEnum.SHOPPING_STATUS_COMMIT);
                item.setUpdateTime(LocalDateTime.now());
            });
            if (!yxShoppingCartService.updateBatchById(carts)) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
            OrderInfoDto result = new OrderInfoDto();
            BeanUtils.copyProperties(yxOrderInfo, result);
            result.setItems(JSONArray.parseArray(JSONArray.toJSONString(itemList), OrderItemDto.class));
            return Result.newSuccessResponse(result);
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Result.newFailedResponse(ErrorCodeEnum.FAIL);
    }
}
