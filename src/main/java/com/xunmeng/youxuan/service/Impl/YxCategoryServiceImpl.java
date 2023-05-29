package com.xunmeng.youxuan.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.domain.YxCategory;
import com.xunmeng.youxuan.enums.ConstantEnum;
import com.xunmeng.youxuan.enums.ErrorCodeEnum;
import com.xunmeng.youxuan.mapper.YxCategoryMapper;
import com.xunmeng.youxuan.requestqo.CommonRequestIdPageQo;
import com.xunmeng.youxuan.responsedto.CategoryDto;
import com.xunmeng.youxuan.service.IYxCategoryService;
import com.xunmeng.youxuan.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * ClassName: YxCategoryServiceImpl
 * Package: com.xunmeng.youxuan.service.Impl
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/24 17:46
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class YxCategoryServiceImpl extends ServiceImpl<YxCategoryMapper, YxCategory> implements IYxCategoryService {
    @Override
    public Result<IPage<CategoryDto>> getCategoryList(CommonRequestIdPageQo requestModel) {
        PageUtil.initRequestPage(requestModel);

        LambdaQueryWrapper<YxCategory> queryWrapper = new LambdaQueryWrapper<YxCategory>()
                .eq(YxCategory::getShopId, requestModel.getId())
                .eq(YxCategory::getDataStatus, ConstantEnum.NORMAL);

        if (StringUtils.isNotEmpty(requestModel.getSortType()) && ConstantEnum.SORT_DESC
                .equalsIgnoreCase(requestModel.getSortType())) {
            queryWrapper = queryWrapper.orderByDesc(YxCategory::getSortNum);
        } else {
            queryWrapper = queryWrapper.orderByAsc(YxCategory::getSortNum);
        }

        IPage<YxCategory> dataList = this.page(new Page<>(requestModel.getPageIndex(), requestModel.getPageSize())
                , queryWrapper);

        if (dataList == null || dataList.getRecords() == null || dataList.getRecords().isEmpty()) {
            return Result.newFailedResponse(ErrorCodeEnum.FAIL);
        }

        IPage<CategoryDto> result = new Page<CategoryDto>()
                .setCurrent(dataList.getCurrent())
                .setTotal(dataList.getTotal())
                .setRecords(JSONArray.parseArray(JSONArray.toJSONString(dataList.getRecords()), CategoryDto.class))
                .setSize(dataList.getSize())
                .setPages(dataList.getPages());

        return Result.newSuccessResponse(result);
    }
}
