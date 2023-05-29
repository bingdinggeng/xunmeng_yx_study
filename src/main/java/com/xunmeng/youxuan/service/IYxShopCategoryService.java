package com.xunmeng.youxuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.domain.YxShopCategory;
import com.xunmeng.youxuan.responsedto.ShopCategoryDto;

import java.util.List;

/**
 * ClassName: IYxShopCategoryService
 * Package: com.xunmeng.youxuan.service
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/24 11:34
 * @Version 1.0
 */
public interface IYxShopCategoryService extends IService<YxShopCategory> {

    /**
     * description: /shop/category/list/all 店铺分类列表全
     * @param:
     * @return: com.xunmeng.youxuan.base.Result<java.util.List<com.xunmeng.youxuan.responsedto.ShopCategoryDto>>
     * @author LTM
     * @date: 2023/5/24 11:45
     */
    Result<List<ShopCategoryDto>> getCategoryList();
}
