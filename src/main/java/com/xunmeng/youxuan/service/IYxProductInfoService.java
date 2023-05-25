package com.xunmeng.youxuan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.domain.YxProductInfo;
import com.xunmeng.youxuan.requestqo.ProductSearchPageQo;
import com.xunmeng.youxuan.responsedto.ProductDto;

/**
 * ClassName: IYxProductInfoService
 * Package: com.xunmeng.youxuan.service
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/24 17:32
 * @Version 1.0
 */
public interface IYxProductInfoService extends IService<YxProductInfo> {
    /**
     * description:  商品信息列表
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Response<com.baomidou.mybatisplus.core.metadata.IPage<com.xunmeng.youxuan.responsedto.ProductDto>>
     * @author LTM
     * @date: 2023/5/25 10:03
     */
    Response<IPage<ProductDto>> productList(ProductSearchPageQo requestModel);
}
