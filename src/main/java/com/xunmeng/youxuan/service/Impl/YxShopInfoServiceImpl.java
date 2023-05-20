package com.xunmeng.youxuan.service.Impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.base.Results;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.domain.YxShopInfo;
import com.xunmeng.youxuan.enums.CacheKeyEnum;
import com.xunmeng.youxuan.enums.ConstantEnum;
import com.xunmeng.youxuan.enums.ErrorCodeEnum;
import com.xunmeng.youxuan.mapper.YxShopInfoMapper;
import com.xunmeng.youxuan.requestqo.LoginQo;
import com.xunmeng.youxuan.requestqo.UserCacheQo;
import com.xunmeng.youxuan.service.IYxShopInfoService;
import com.xunmeng.youxuan.utils.DataUtil;
import com.xunmeng.youxuan.utils.RedisStringUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.xunmeng.youxuan.utils.UserLoginUtil.USER_CACHE_TIME;

/**
 * ClassName: YxShopInfoServiceImpl
 * Package: com.xunmeng.service.Impl
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/20 8:59
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class YxShopInfoServiceImpl extends ServiceImpl<YxShopInfoMapper, YxShopInfo>  implements IYxShopInfoService {

    private final RedisStringUtil redisUtil;
    @Override
    public Response<UserInfo> loginIn(LoginQo requestModel) {
        List<YxShopInfo> listShop = this.list(new QueryWrapper<YxShopInfo>()
                .eq("shop_name",requestModel.getNickName()));

        if(listShop == null || listShop.isEmpty()){
            return Results.newFailedResponse(ErrorCodeEnum.INFO_NOT_EXIST);
        }else if(listShop.size() !=1){
            return Results.newFailedResponse(ErrorCodeEnum.INFO_NOT_ONLY);
        }

        String password = requestModel.getPass() + listShop.get(0).getEncrypt();
        BCrypt.Result res = BCrypt.verifyer().verify(password.toCharArray(), listShop.get(0).getPassword());
        if( !res.verified){
            return Results.newFailedResponse(ErrorCodeEnum.PASSWORD_ERROR);
        }

        // 不清楚极光推送标识是啥，先不动原代码逻辑
        if(StringUtils.isNotBlank(requestModel.getRegistrationId()) && !requestModel.getRegistrationId()
                .equals(listShop.get(0).getRegistrationId())){
            YxShopInfo updateInfo = new YxShopInfo();
            updateInfo.setShopId(listShop.get(0).getShopId());
            updateInfo.setRegistrationId(requestModel.getRegistrationId());
            this.updateById(updateInfo);
        }

        String openId = DataUtil.getUUID();
        UserInfo userInfo = cacheUserInfoForshop(openId, null, listShop.get(0), 2);
        return Results.newSuccessResponse(userInfo);
    }

    private UserInfo cacheUserInfoForshop(String openId, String sessionKey, YxShopInfo shop, int dataSource) {

        String cacheKeyShop;
        if(StringUtils.isNotEmpty(shop.getWxOpenId())){
            cacheKeyShop = CacheKeyEnum.USER_TOKEN_INFO + shop.getWxOpenId();
        }else{
            cacheKeyShop = CacheKeyEnum.USER_TOKEN_INFO + openId;
        }

        UserCacheQo cacheQo = new UserCacheQo();
        cacheQo.setUserId(shop.getShopId().intValue());
        cacheQo.setRoleType(ConstantEnum.USER_SHOP);

        redisUtil.set(cacheKeyShop, JSON.toJSONString(cacheQo), USER_CACHE_TIME);

        String userKey = CacheKeyEnum.SHOP_INFO + shop.getShopId();

        UserInfo user = new UserInfo();
        user.setMobileNumber(shop.getShopPhone());
        user.setNickName(shop.getShopName());
        user.setOpenId(StringUtils.isNotEmpty(shop.getWxOpenId()) ? shop.getWxOpenId() : openId);

        user.setRealName(shop.getRealName());
        user.setRoleType(ConstantEnum.USER_SHOP);
        user.setUserId(shop.getShopId());
        user.setAvatar(shop.getAvatar());
        user.setDataSource(dataSource);
        user.setDataSource(shop.getDataStatus());

        redisUtil.set(userKey, JSON.toJSONString(user), USER_CACHE_TIME);
        return user;
    }
}
