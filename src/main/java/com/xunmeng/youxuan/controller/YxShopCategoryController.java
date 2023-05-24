package com.xunmeng.youxuan.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.responsedto.ShopCategoryDto;
import com.xunmeng.youxuan.service.IYxShopCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: YxShopCategoryController
 * Package: com.xunmeng.youxuan.controller
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/24 11:31
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(tags = "9.0 门店品类管理接口", value = "门店品类管理接口")
@ApiSort(value = 11)
@RequestMapping("/shop/category")
@RequiredArgsConstructor
public class YxShopCategoryController {
    private final IYxShopCategoryService yxShopCategoryService;

    @ApiOperation(value = "店铺分类列表全", notes = "店铺分类列表全")
    @PostMapping("/list/all")
    public Response<List<ShopCategoryDto>> getCategoryList(){
        return yxShopCategoryService.getCategoryList();
    }

}
