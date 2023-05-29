package com.xunmeng.youxuan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.youxuan.base.Result;
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
     * description: /product/list 商品信息列表
     * @param:
     * @param requestModel
     * @return: Result<IPage<ProductDto>>
     * @author LTM
     * @date: 2023/5/25 10:03
     */
    Result<IPage<ProductDto>> productList(ProductSearchPageQo requestModel);
}
