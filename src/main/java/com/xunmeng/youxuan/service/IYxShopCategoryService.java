package com.xunmeng.youxuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.youxuan.base.Response;
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
     * description: 获取店铺分类
     * @param:
     * @return: com.xunmeng.youxuan.base.Response<java.util.List<com.xunmeng.youxuan.responsedto.ShopCategoryDto>>
     * @author LTM
     * @date: 2023/5/24 11:45
     */
    Response<List<ShopCategoryDto>> getCategoryList();
}
