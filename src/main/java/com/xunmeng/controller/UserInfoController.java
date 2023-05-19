package com.xunmeng.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.xunmeng.base.Response;
import com.xunmeng.domain.UserInfo;
import com.xunmeng.enums.CacheKeyEnum;
import com.xunmeng.requestqo.LoginQo;
import com.xunmeng.base.Results;
import com.xunmeng.service.IXmAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.CachedExpressionEvaluator;
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
public class UserInfoController{
    @Autowired
    private IXmAdminService xmAdminService;

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

        return xmAdminService.loginOn(requestModel);

       /* 注意这里的Response<UserInfo>  没加<UserInfo>的时候会报错
         Response<UserInfo> response = xmAdminService.login(requestModel);
         return Results.newResponse(response.getData(),response.getCode(),response.getMsg());
        这是最开始想着拆分原代码controller功能到service层想的办法，后来想到完全没有必要多绕一层，直接返回方法结果就可以了
        */
    }

    @ApiOperation(value = "登出",notes = "登出")
    @PostMapping("/login/out")
    public Response loginOut(){
        return xmAdminService.loginOut();
    }
}
