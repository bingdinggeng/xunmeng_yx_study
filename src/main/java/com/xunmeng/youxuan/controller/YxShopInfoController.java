package com.xunmeng.youxuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.requestqo.ShopListQo;
import com.xunmeng.youxuan.responsedto.ShopDto;
import com.xunmeng.youxuan.service.IYxShopInfoService;
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
 * ClassName: YxShopInfoController
 * Package: com.xunmeng.youxuan.controller
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/24 14:48
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(tags={"3.0 商戶管理接口"},value = "商戶管理接口")
@ApiSort(value = 3)
@RequestMapping("/shop")
@RequiredArgsConstructor
public class YxShopInfoController {

    private final IYxShopInfoService yxShopInfoService;

    @ApiOperation(value = "商家列表：用户", notes = "商家列表：用户")
    @PostMapping("/list")
    public Response<IPage<ShopDto>> getShopList(@RequestBody @Validated ShopListQo requestModel){

        return yxShopInfoService.getShopList(requestModel);
    }
}
