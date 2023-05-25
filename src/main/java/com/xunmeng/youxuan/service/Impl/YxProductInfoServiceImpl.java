package com.xunmeng.youxuan.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.base.Results;
import com.xunmeng.youxuan.domain.YxProductInfo;
import com.xunmeng.youxuan.enums.ConstantEnum;
import com.xunmeng.youxuan.enums.ErrorCodeEnum;
import com.xunmeng.youxuan.mapper.YxProductInfoMapper;
import com.xunmeng.youxuan.requestqo.ProductSearchPageQo;
import com.xunmeng.youxuan.responsedto.ProductDto;
import com.xunmeng.youxuan.service.IYxProductInfoService;
import com.xunmeng.youxuan.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * ClassName: YxProductInfoServiceImpl
 * Package: com.xunmeng.youxuan.service.Impl
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/24 17:33
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class YxProductInfoServiceImpl extends ServiceImpl<YxProductInfoMapper, YxProductInfo>
        implements IYxProductInfoService {

    @Override
    public Response<IPage<ProductDto>> productList(ProductSearchPageQo requestModel) {
        PageUtil.initRequestPage(requestModel);
        return getProductList(requestModel, false);
    }

    private Response<IPage<ProductDto>> getProductList(ProductSearchPageQo requestModel, boolean isManage) {

        LambdaQueryWrapper<YxProductInfo> queryWrapper = new LambdaQueryWrapper<YxProductInfo>()
                .like(StringUtils.isNotBlank(requestModel.getProductName()), YxProductInfo::getProductName
                        , requestModel.getProductName())
                .eq(requestModel.getShopId() != null && requestModel.getShopId() > 0,
                        YxProductInfo::getShopId, requestModel.getShopId())
                .eq(requestModel.getCategoryId() != null && requestModel.getCategoryId() > 0,
                        YxProductInfo::getCategoryId, requestModel.getCategoryId())
                .ge(isManage, YxProductInfo::getDataStatus, ConstantEnum.PRODUCT_OFF_SHELF)
                .eq(!isManage, YxProductInfo::getDataStatus, ConstantEnum.PRODUCT_ON_SHELF);
        if(requestModel.getCategoryId() == null || requestModel.getCategoryId() < 1){
            queryWrapper = queryWrapper.orderByDesc(YxProductInfo::getSortNum, YxProductInfo::getSellCount,
                    YxProductInfo::getCategoryId);
        } else if (StringUtils.isNotBlank(requestModel.getSortType()) &&
                ConstantEnum.SORT_ASC.equalsIgnoreCase(requestModel.getSortType())) {
                queryWrapper = queryWrapper.orderByDesc(YxProductInfo::getSellCount)
                        .orderByAsc(YxProductInfo::getSortNum);
        }else{
            queryWrapper = queryWrapper.orderByDesc(YxProductInfo::getSellCount, YxProductInfo::getSortNum);
        }

        IPage<YxProductInfo> dataList = this.page(new Page<>(requestModel.getPageIndex(), requestModel.getPageSize())
                , queryWrapper);
        if(dataList == null || dataList.getRecords() == null){
            return Results.newFailedResponse(ErrorCodeEnum.FAIL);
        }

        List<ProductDto> resultList = JSON.parseArray(JSON.toJSONString(dataList.getRecords()), ProductDto.class);
        //根据 goodComment 和 totalComment 的值计算并设置了 startCount 属性，以表示好评率或评分值
        resultList.forEach(item -> {
            if(item.getTotalComment() > 0){
                item.setStartCount(new BigDecimal(item.getGoodComment()).divide(new BigDecimal(item.getTotalComment()),1,BigDecimal.ROUND_HALF_UP));
            }
        });

        IPage<ProductDto> result = new Page<ProductDto>()
                .setCurrent(dataList.getCurrent())
                .setRecords(resultList)
                .setPages(dataList.getPages())
                .setSize(dataList.getSize())
                .setTotal(dataList.getTotal());

        return Results.newSuccessResponse(result);
    }
}
