package com.xunmeng.youxuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.requestqo.ProductSearchPageQo;
import com.xunmeng.youxuan.responsedto.ProductDto;
import com.xunmeng.youxuan.service.IYxProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: YxProductInfoController
 * Package: com.xunmeng.youxuan.controller
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/25 9:48
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(tags = "5.0 商品管理接口", value = "商品管理接口")
@ApiSort(value = 5)
@RequestMapping("/product")
@RequiredArgsConstructor
public class YxProductInfoController {

    private final IYxProductInfoService yxProductInfoService;

    @ApiOperation(value = "商品信息列表", notes = "商品信息列表，id：shopId")
    @PostMapping("/list")
    public Response<IPage<ProductDto>> list(@RequestBody @Validated ProductSearchPageQo requestModel){
        return yxProductInfoService.productList(requestModel);
    }
}
