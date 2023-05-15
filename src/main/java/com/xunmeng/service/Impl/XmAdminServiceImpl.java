package com.xunmeng.service.Impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.base.Response;
import com.xunmeng.domain.UserInfo;
import com.xunmeng.domain.XmAdmin;
import com.xunmeng.enums.CacheKeyEnum;
import com.xunmeng.enums.ConstantEnum;
import com.xunmeng.enums.ErrorCodeEnum;
import com.xunmeng.mapper.XmAdminMapper;
import com.xunmeng.requestqo.LoginQo;
import com.xunmeng.requestqo.Results;
import com.xunmeng.requestqo.UserCacheQo;
import com.xunmeng.service.IXmAdminService;
import com.xunmeng.utils.CheckLoginRoleTypeUtil;
import com.xunmeng.utils.DataUtil;
import com.xunmeng.utils.RedisStringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;




/**
 * ClassName: XmAdminServiceImpl
 * Package: com.xunmeng.service.Impl
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/14 14:40
 * @Version 1.0
 */
@Service
public class XmAdminServiceImpl extends ServiceImpl<XmAdminMapper, XmAdmin> implements IXmAdminService {
    @Value("${user.cache.time}")
    private Long userCacheTime;
    @Resource
    private IXmAdminService xmAdminService;
    @Resource
    private RedisStringUtil redisStringUtil;
    
    /** 
     * description:
     * @param: 
     * @param requestModel 
     * @return: com.xunmeng.base.Response<com.xunmeng.domain.UserInfo> 
     * @author LTM
     * @date: 2023/5/15 10:02
     */ 
    @Override
    public Response<UserInfo> login(LoginQo requestModel) {
        List<XmAdmin> listUser = xmAdminService.list(new QueryWrapper<XmAdmin>()
                .eq("username",requestModel.getNickName())
                .eq("status", ConstantEnum.NORMAL_XM)
                .eq("comid",ConstantEnum.COM_XUNMENG));
        if(listUser == null || listUser.size() < 1){
            return Results.newFailedResponse(ErrorCodeEnum.INFO_NOT_EXIST);
        }else if(listUser.size() > 1){
            return Results.newFailedResponse(ErrorCodeEnum.INFO_NOT_ONLY);
        }
        String password = requestModel.getPassword() + listUser.get(0).getEncrypt();
        BCrypt.Result res = BCrypt.verifyer().verify(password.toCharArray(),listUser.get(0).getPassword());
        if(!res.verified){
            return Results.newFailedResponse(ErrorCodeEnum.PASSWORD_ERROR);
        }
        return Results.newSuccessResponse(cacheUserInfoForAdmin(DataUtil.getUUID(), null, listUser.get(0),1));
    }

    /**
     * description: 寻梦员工登录缓存
     * @param:
     * @param openId
     * @param sessionKey
     * @param admin
     * @param dataSource
     * @return: com.xunmeng.domain.UserInfo
     * @author LTM
     * @date: 2023/5/15 11:53
     */
    private UserInfo cacheUserInfoForAdmin(String openId, String sessionKey, XmAdmin admin, Integer dataSource) {
        String cacheKeyUser = CacheKeyEnum.USER_TOKEN_INFO + openId;
        if(StringUtils.isNotEmpty(admin.getWxOpenId())){
            cacheKeyUser = CacheKeyEnum.USER_TOKEN_INFO + admin.getWxOpenId();
        }
        UserCacheQo cacheQo = new UserCacheQo();
        cacheQo.setUserId(admin.getUserId());
        cacheQo.setRoleType(admin.getRoleId());
        redisStringUtil.set(cacheKeyUser, JSON.toJSONString(cacheQo),userCacheTime);

        String userKey = CacheKeyEnum.USER_INFO + admin.getUserId();

        UserInfo user = new UserInfo();
        user.setMobileNumber(admin.getPhone());
        user.setNickName(admin.getUserName());

        user.setOpenId(openId);
        if(StringUtils.isNotEmpty(admin.getWxOpenId())){
            user.setOpenId(admin.getWxOpenId());
        }
        user.setRealName(admin.getNickName());
        if(CheckLoginRoleTypeUtil.checkAdmin(admin.getUserId(), redisStringUtil)){
            user.setRoleType(ConstantEnum.USER_ADMIN);
        }else if(CheckLoginRoleTypeUtil.checkService(admin.getUserId(), redisStringUtil)){
            user.setRoleType(ConstantEnum.USER_SERVICE);
        }else {
            user.setRoleType(ConstantEnum.USER_XM);
        }

        return null;
    }
}
