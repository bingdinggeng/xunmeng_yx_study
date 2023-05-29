package com.xunmeng.youxuan.service.Impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.domain.XmAdmin;
import com.xunmeng.youxuan.enums.CacheKeyEnum;
import com.xunmeng.youxuan.enums.ConstantEnum;
import com.xunmeng.youxuan.enums.ErrorCodeEnum;
import com.xunmeng.youxuan.logic.BaseLogic;
import com.xunmeng.youxuan.mapper.XmAdminMapper;
import com.xunmeng.youxuan.requestqo.LoginQo;
import com.xunmeng.youxuan.requestqo.UserCacheQo;
import com.xunmeng.youxuan.service.IXmAdminService;
import com.xunmeng.youxuan.utils.DataUtil;
import com.xunmeng.youxuan.utils.RedisStringUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.xunmeng.youxuan.utils.UserLoginUtil.USER_CACHE_TIME;


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
@RequiredArgsConstructor
public class XmAdminServiceImpl extends ServiceImpl<XmAdminMapper, XmAdmin> implements IXmAdminService {
    private final RedisStringUtil redisUtil;
    @Resource
    private  BaseLogic baseLogic;


    @Override
    public Result<UserInfo> loginIn(LoginQo requestModel) {
        List<XmAdmin> listUser = this.list(new QueryWrapper<XmAdmin>()
                .eq("username", requestModel.getNickName())
                .eq("status", ConstantEnum.NORMAL_XM)
                .eq("comid", ConstantEnum.COM_XUNMENG));

        if (listUser == null || listUser.isEmpty()) {
            return Result.newFailedResponse(ErrorCodeEnum.INFO_NOT_EXIST);
        } else if (listUser.size() != 1) {
            return Result.newFailedResponse(ErrorCodeEnum.INFO_NOT_ONLY);
        }

        String password = requestModel.getPass() + listUser.get(0).getEncrypt();
        BCrypt.Result res = BCrypt.verifyer().verify(password.toCharArray(), listUser.get(0).getPassword());
        if (!res.verified) {
            return Result.newFailedResponse(ErrorCodeEnum.PASSWORD_ERROR);
        }

        String openId = DataUtil.getUUID();
        UserInfo userInfo = cacheUserInfoForAdmin(openId, null, listUser.get(0), 1);
        return Result.newSuccessResponse(userInfo);
    }


    @Override
    public Result<T> loginOut() {
        String cacheKeyUser = CacheKeyEnum.USER_TOKEN_INFO + baseLogic.getCurrentUserOpenId();
        UserCacheQo cacheQo = null;
        Object userType = redisUtil.get(cacheKeyUser);

        if (userType != null) {
            cacheQo = JSONObject.parseObject(userType.toString(), UserCacheQo.class);
        }
        if (cacheQo != null) {
            if (cacheQo.getRoleType().equals(ConstantEnum.USER_SHOP)) {
                String cacheKey = CacheKeyEnum.SHOP_INFO + cacheQo.getUserId();
                redisUtil.deleteRedis(cacheKey);
            } else {
                String cacheKey = CacheKeyEnum.USER_INFO + cacheQo.getUserId();
                redisUtil.deleteRedis(cacheKey);
            }
        }
        redisUtil.deleteRedis(cacheKeyUser);
        return Result.newSuccessResponse(ErrorCodeEnum.SUCCESS);
    }


    private UserInfo cacheUserInfoForAdmin(String openId, String sessionKey, XmAdmin admin, Integer dataSource) {

        String cacheKeyUser;
        if (StringUtils.isNotEmpty(admin.getWxOpenId())) {
            cacheKeyUser = CacheKeyEnum.USER_TOKEN_INFO + admin.getWxOpenId();
        } else {
            cacheKeyUser = CacheKeyEnum.USER_TOKEN_INFO + openId;
        }

        UserCacheQo cacheQo = new UserCacheQo()
                .setUserId(admin.getUserId())
                .setRoleType(admin.getRoleId());

        redisUtil.set(cacheKeyUser, JSON.toJSONString(cacheQo), USER_CACHE_TIME);

        String userKey = CacheKeyEnum.USER_INFO + admin.getUserId();

        UserInfo user = new UserInfo()
                .setMobileNumber(admin.getPhone())
                .setNickName(admin.getUserName())
                .setOpenId(StringUtils.isNotEmpty(admin.getWxOpenId()) ? admin.getWxOpenId() : openId);

        user.setRealName(admin.getNickName());
        if (baseLogic.checkAdmin(admin.getUserId())) {
            user.setRoleType(ConstantEnum.USER_ADMIN);
        } else if (baseLogic.checkService(admin.getUserId())) {
            user.setRoleType(ConstantEnum.USER_SERVICE);
        } else {
            user.setRoleType(ConstantEnum.USER_XM);
        }

        user.setUserId(admin.getUserId().longValue())
                .setAvatar(admin.getAvatar())
                .setDataSource(dataSource)
                .setJoinTime(admin.getJoinTime());
        redisUtil.set(userKey, JSON.toJSONString(user), USER_CACHE_TIME);
        return user;
    }
}
