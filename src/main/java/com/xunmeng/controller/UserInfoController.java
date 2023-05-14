package com.xunmeng.controller;

import com.alibaba.fastjson.JSON;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.xunmeng.base.Response;
import com.xunmeng.domain.UserInfo;
import com.xunmeng.domain.XmAdmin;
import com.xunmeng.enums.CacheKeyEnum;
import com.xunmeng.enums.ConstantEnum;
import com.xunmeng.requestqo.LoginQo;
import com.xunmeng.requestqo.Results;
import com.xunmeng.requestqo.UserCacheQo;
import com.xunmeng.service.IXmAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
public class UserInfoController {

    @Value("${user.cache.time}")
    private Long userCacheTime;
    @Resource
    private IXmAdminService xmAdminService;

    private UserInfo cacheUserInfoForAdmin(String openId, String sessionKey, XmAdmin admin, Integer dataSource) {
        String cacheKeyUser = CacheKeyEnum.USER_TOKEN_INFO + openId;
        if(StringUtils.isNotEmpty(admin.getWxOpenId())) {
            cacheKeyUser = CacheKeyEnum.USER_TOKEN_INFO + admin.getWxOpenId();
        }
        UserCacheQo cacheQo = new UserCacheQo();
        cacheQo.setUserId(admin.getUserId());
        cacheQo.setRoleType(ConstantEnum.USER_XM);
        redisUtil.set(cacheKeyUser, JSON.toJSONString(cacheQo),userCacheTime);

        String userKey = CacheKeyEnum.USER_INFO + admin.getUserId();

        UserInfo user = new UserInfo();
        user.setMobileNumber(admin.getPhone());
        user.setNickName(admin.getUserName());

        user.setOpenId(openId);
        if(StringUtils.isNotEmpty(admin.getWxOpenId())) {
            user.setOpenId(admin.getWxOpenId());
        }
        user.setRealName(admin.getNickName());
        if(checkAdmin(admin.getUserId())){
            user.setRoleType(ConstantEnum.USER_ADMIN);
        }else if(checkService(admin.getUserId())){
            user.setRoleType(ConstantEnum.USER_SERVICE);
        }else {
            user.setRoleType(ConstantEnum.USER_XM);
        }
        user.setUserId(admin.getUserId().longValue());
        user.setAvatar(admin.getAvatar());
        user.setDataSource(dataSource);
        user.setJoinTime(admin.getJoinTime());
        redisUtil.set(userKey, JSON.toJSONString(user),userCacheTime);
        return user;
    }

    @ApiOperation(value = "公司内部员工登录",notes = "公司内部员工密码登录")
    @PostMapping("/login/user")
    public Response<UserInfo> userLogin(@RequestBody @Validated LoginQo requestModel){
        // 注意这里的Response<UserInfo>  没加<UserInfo>的时候会报错
        Response<UserInfo> response = xmAdminService.login(requestModel);
        return Results.newResponse(response.getData(),response.getCode(),response.getMsg());
    }
}
