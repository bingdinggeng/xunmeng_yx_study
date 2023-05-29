package com.xunmeng.youxuan.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.domain.YxOrderInfo;
import com.xunmeng.youxuan.domain.YxOrderItem;
import com.xunmeng.youxuan.domain.YxShopInfo;
import com.xunmeng.youxuan.enums.ConstantEnum;
import com.xunmeng.youxuan.enums.ErrorCodeEnum;
import com.xunmeng.youxuan.logic.BaseLogic;
import com.xunmeng.youxuan.mapper.YxOrderInfoMapper;
import com.xunmeng.youxuan.requestqo.OrderQueryQo;
import com.xunmeng.youxuan.responsedto.OrderInfoDto;
import com.xunmeng.youxuan.responsedto.OrderItemDto;
import com.xunmeng.youxuan.service.IYxOrderInfoService;
import com.xunmeng.youxuan.service.IYxOrderItemService;
import com.xunmeng.youxuan.service.IYxShopInfoService;
import com.xunmeng.youxuan.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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

        IPage<YxOrderInfo> dataList = this.page(new Page<>(requestModel.getPageSize(),requestModel.getPageIndex())
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

        if(itemList != null && itemList.size() > 0){
            for(OrderInfoDto data: result.getRecords()){
                List<YxOrderItem> subOrderItem = itemList.stream().filter(item -> item.getOrderId()
                        .equals(data.getOrderId())).collect(Collectors.toList());
                if (subOrderItem.size() > 0) {
                    data.setItems(JSONArray.parseArray(JSONArray.toJSONString(subOrderItem), OrderItemDto.class));
                }
                if(shops != null && shops.size() > 0){
                    List<YxShopInfo> subShops = shops.stream().filter(item -> item.getShopId()
                            .equals(data.getShopId())).collect(Collectors.toList());
                    if(subShops.size() > 0){
                        data.setShopTel(subShops.get(0).getShopPhone());
                    }
                }
            }
        }
        return Result.newSuccessResponse(result);
    }
}
