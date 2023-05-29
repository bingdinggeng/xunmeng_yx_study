package com.xunmeng.youxuan.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.requestqo.LoginQo;
import com.xunmeng.youxuan.requestqo.ShopPasswordQo;
import com.xunmeng.youxuan.requestqo.WXShopInfoQo;
import com.xunmeng.youxuan.service.IXmAdminService;
import com.xunmeng.youxuan.service.IYxShopInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: UserInfoController
 * Package: com.xunmeng.controller
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/14 9:05
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(tags = "2.0用户管理接口",value = "用户管理接口")
@ApiSort(value = 2)
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserInfoController{

    private final IXmAdminService xmAdminService;

    private final IYxShopInfoService yxShopInfoService;



    @ApiOperation(value = "公司内部员工登录",notes = "公司内部员工密码登录")
    @PostMapping("/login/user")
    public Result<UserInfo> userLogin(@RequestBody @Validated LoginQo requestModel) {
        return xmAdminService.loginIn(requestModel);
    }

    @ApiOperation(value = "商家密码登录",notes = "商家密码登录")
    @PostMapping("/login/shop")
    public Result<UserInfo> shopLogin(@RequestBody @Validated LoginQo requestModel){

        //WXInfoDto wxInfoDto = new WXInfoDto();  原代码未开发功能
        return yxShopInfoService.loginIn(requestModel);

    }

    @ApiOperation(value = "商家修改登录密码", notes = "商家修改登录密码")
    @PostMapping("/password/shop/change")
    public Result<T> passwordChange(@RequestBody @Validated ShopPasswordQo requestModel){

        //WXInfoDto wxInfoDto = new WXInfoDto();  原代码未开发功能
        return yxShopInfoService.passwordChange(requestModel);

    }

    @ApiOperation(value = "登出",notes = "登出")
    @PostMapping("/login/out")
    public Result<T> loginOut(){
        return xmAdminService.loginOut();
    }


    @ApiOperation(value = "店注册", notes = "店注册")
    @PostMapping("/register/shop")
    public Result<UserInfo> registerForShop(@RequestBody @Validated WXShopInfoQo requestModel) {
        return yxShopInfoService.registerForShop(requestModel);
    }
}
