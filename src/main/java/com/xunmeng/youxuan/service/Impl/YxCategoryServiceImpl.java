package com.xunmeng.youxuan.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.domain.YxCategory;
import com.xunmeng.youxuan.enums.ConstantEnum;
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
    public Response<IPage<CategoryDto>> getCategoryList(CommonRequestIdPageQo requestModel) {
        PageUtil.initRequestPage(requestModel);
        IPage<CategoryDto> result = null;
        Page<YxCategory> page = new Page<>(requestModel.getPageIndex(),requestModel.getPageSize());

        LambdaQueryWrapper<YxCategory> queryWrapper = new LambdaQueryWrapper<YxCategory>()
                .eq(YxCategory::getShopId, requestModel.getId())
                .eq(YxCategory::getDataStatus, ConstantEnum.NORMAL)
                .like(StringUtils.isNotBlank())

        return null;
    }
}
