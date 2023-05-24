package com.xunmeng.youxuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.requestqo.CommonRequestIdPageQo;
import com.xunmeng.youxuan.responsedto.CategoryDto;
import com.xunmeng.youxuan.service.IYxCategoryService;
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
 * ClassName: YxCategoryController
 * Package: com.xunmeng.youxuan.controller
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/24 17:29
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(tags = "4.0 品类管理接口",value = "品类管理接口")
@ApiSort(value = 4)
@RequestMapping("/category")
@RequiredArgsConstructor
public class YxCategoryController {

    private final IYxCategoryService yxCategoryService;

    @ApiOperation(value = "店铺分类列表,ID:shopId", notes = "店铺分类列表,ID:shopId")
    @PostMapping("/list")
    @ApiOperationSupport(order = 1)
    public Response<IPage<CategoryDto>> getCategoryList(@RequestBody @Validated CommonRequestIdPageQo requestModel){
        return yxCategoryService.getCategoryList(requestModel);
    }
}
