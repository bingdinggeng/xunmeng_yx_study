package com.xunmeng.youxuan.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.xunmeng.youxuan.base.Response;
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


    /**
     * description: 登录入口
     * @param:
     * @param requestModel
     * @return: com.xunmeng.base.Response<com.xunmeng.domain.UserInfo>
     * @author LTM
     * @date: 2023/5/15 9:51
     */
    @ApiOperation(value = "公司内部员工登录",notes = "公司内部员工密码登录")
    @PostMapping("/login/user")
    public Response<UserInfo> userLogin(@RequestBody @Validated LoginQo requestModel) {

        return xmAdminService.loginIn(requestModel);

       /* 注意这里的Response<UserInfo>  没加<UserInfo>的时候会报错
         Response<UserInfo> response = xmAdminService.login(requestModel);
         return Results.newResponse(response.getData(),response.getCode(),response.getMsg());
        这是最开始想着拆分原代码controller功能到service层想的办法，后来想到完全没有必要多绕一层，直接返回方法结果就可以了
        */
    }


    /**
     * description: 商家密码登录
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Response<com.xunmeng.youxuan.domain.UserInfo>
     * @author LTM
     * @date: 2023/5/20 16:03
     */
    @ApiOperation(value = "商家密码登录",notes = "商家密码登录")
    @PostMapping("/login/shop")
    public Response<UserInfo> shopLogin(@RequestBody @Validated LoginQo requestModel){

        //WXInfoDto wxInfoDto = new WXInfoDto();  原代码未开发功能
        return yxShopInfoService.loginIn(requestModel);

    }


    @ApiOperation(value = "商家修改登录密码", notes = "商家修改登录密码")
    @PostMapping("/password/shop/change")
    public Response passwordChange(@RequestBody @Validated ShopPasswordQo requestModel){

        //WXInfoDto wxInfoDto = new WXInfoDto();  原代码未开发功能
        return yxShopInfoService.passwordChange(requestModel);

    }

    /**
     * description: 用户登出
     * @param:
     * @return: com.xunmeng.base.Response
     * @author LTM
     * @date: 2023/5/20 11:25
     */
    @ApiOperation(value = "登出",notes = "登出")
    @PostMapping("/login/out")
    public Response loginOut(){
        return xmAdminService.loginOut();
    }


    @ApiOperation(value = "店注册", notes = "店注册")
    @PostMapping("/register/shop")
    public Response<UserInfo> registerForShop(@RequestBody @Validated WXShopInfoQo requestModel) {
        return yxShopInfoService.registerForShop(requestModel);
    }
}
