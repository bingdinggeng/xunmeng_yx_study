package com.xunmeng.youxuan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.domain.YxCategory;
import com.xunmeng.youxuan.requestqo.CommonRequestIdPageQo;
import com.xunmeng.youxuan.responsedto.CategoryDto;

/**
 * ClassName: IYxCategoryService
 * Package: com.xunmeng.youxuan.service
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/24 17:46
 * @Version 1.0
 */
public interface IYxCategoryService extends IService<YxCategory> {
    /**
     * description: /category/list 店铺分类列表,ID:shopId
     * @param:
     * @param requestModel
     * @return: Result<IPage<CategoryDto>>
     * @author LTM
     * @date: 2023/5/24 17:48
     */
    Result<IPage<CategoryDto>> getCategoryList(CommonRequestIdPageQo requestModel);
}
