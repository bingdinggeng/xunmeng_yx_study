package com.xunmeng.youxuan.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.domain.YxAdminInfo;
import com.xunmeng.youxuan.enums.CacheKeyEnum;
import com.xunmeng.youxuan.enums.ConstantEnum;
import com.xunmeng.youxuan.requestqo.UserCacheQo;
import com.xunmeng.youxuan.service.IYxAdminInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ClassName: BaseUtil
 * Package: com.xunmeng.youxuan.service.Impl
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/21 10:54
 * @Version 1.0
 */
@Component
@RequiredArgsConstructor
public class BaseUtil{
    private  final RedisStringUtil redisUtil;
    private  final IYxAdminInfoService yxAdminInfoService;
    private String defaultOpenId =  "sdfsdfsdfqw41231231wer121";
    private Integer defaultId = 17;
    private int roleType = ConstantEnum.USER_SHOP;


    /**
     * description: 判断当前登陆人员是否为优选管理员
     * @param:
     * @param userId
     * @return: boolean
     * @author LTM
     * @date: 2023/5/17 15:13
     */
    public  boolean checkAdmin(Integer userId){
        String cacheAdminKey = CacheKeyEnum.USER_ADMIN_LIST;
        Object adminObject = redisUtil.get(cacheAdminKey);

        List<YxAdminInfo> adminList;
        if(adminObject != null){
            // 将adminObject对象转换成json字符串再解析成一个YxAdminInfo类型的对象，赋值给adminList
            adminList = JSON.parseArray(adminObject.toString(), YxAdminInfo.class);
        }else{
            adminList = yxAdminInfoService.list(new QueryWrapper<YxAdminInfo>()
                    .eq("data_status", ConstantEnum.NORMAL));
            if (adminList != null && !adminList.isEmpty()) {
                redisUtil.set(cacheAdminKey, JSON.toJSONString(adminList), UserLoginUtil.USER_CACHE_TIME);
            }
        }
        if (adminList != null && !adminList.isEmpty()) {
            return adminList.stream().anyMatch(a -> a.getUserId().equals(userId));
        }

        return false;
    }

    /**
     * description: 判断当前登陆人员是否为优选客服
     * @param:
     * @param userId
     * @return: boolean
     * @author LTM
     * @date: 2023/5/17 15:37
     */
    public boolean checkService(Integer userId) {
        List<YxAdminInfo> adminList = getServiceList();
        return adminList != null && adminList.stream()
                .anyMatch(a -> a.getUserId().equals(userId));
    }

    /**
     * description: 获取客服列表
     * @param:
     * @return: java.util.List<com.xunmeng.domain.YxAdminInfo>
     * @author LTM
     * @date: 2023/5/17 15:27
     */
    public List<YxAdminInfo> getServiceList(){
        String cacheServiceKey = CacheKeyEnum.USER_SERVICE_LIST;
        Object adminObject = redisUtil.get(cacheServiceKey);

        List<YxAdminInfo> adminList;
        if(adminObject != null){
            adminList = JSON.parseArray(adminObject.toString(), YxAdminInfo.class);
        }else {
            adminList = yxAdminInfoService.list(new QueryWrapper<YxAdminInfo>()
                    .eq("data_status", ConstantEnum.NORMAL));
            if (adminList != null && !adminList.isEmpty()) {
                redisUtil.set(cacheServiceKey, JSON.toJSONString(adminList), UserLoginUtil.USER_CACHE_TIME);
            }
        }
        return adminList;
    }

    /**
     * description:  获取当前请求人的ID
     * @param:
     * @return: java.lang.String
     * @author LTM
     * @date: 2023/5/19 15:28
     */
    public String getCurrentUserOpenId(){

        String openId = getCurrentUserOpenIdForHtml();
        if(StringUtils.isNotEmpty(openId)){
            return openId;
        }

        openId = getCurrentUserOpenIdWx();
        if(StringUtils.isNotEmpty(openId)){
            return openId;
        }

        if(StringUtils.isEmpty(openId) && "test".equals(UserLoginUtil.MODEL_TYPE)){
            openId = defaultOpenId;
        }
        return openId;
    }


    /**
     * description: 获取当前请求人的openId
     * @param:
     * @return: java.lang.String
     * @author LTM
     * @date: 2023/5/19 15:17
     */
    private String getCurrentUserOpenIdForHtml() {
        // 注意这里有一些修改，在于return cookie改成了null
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String cookie = CookiesUtils.getCookieValue(request,"usertoken");
        if(StringUtils.isBlank(cookie)){
            // 这里return的是openId
            return request.getHeader("usertoken_yx");
        }
        return null;
    }

    /**
     * description:  获取当前请求人的ID 微信
     * @param:
     * @return: java.lang.String
     * @author LTM
     * @date: 2023/5/19 15:22
     */
    private String getCurrentUserOpenIdWx() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader("usertoken_wx");
    }

    /**
     * description: 获取当前用户的信息
     * @param:
     * @return: com.xunmeng.youxuan.domain.UserInfo
     * @author LTM
     * @date: 2023/5/21 11:32
     */
    //public UserInfo getCurrentUserInfo(){
    //    UserInfo user = null;
    //
    //    int dataSource = 1;
    //    String openId = getCurrentUserOpenIdForHtml();
    //    if (StringUtils.isEmpty(openId)) {
    //        openId = getCurrentUserOpenIdWx();
    //        if (StringUtils.isEmpty(openId)) {
    //            if (StringUtils.isEmpty(openId) && "test".equals(UserLoginUtil.MODEL_TYPE)) {
    //                openId = defaultOpenId;
    //                String userKey = CacheKeyEnum.USER_TOKEN_INFO + openId;
    //                UserCacheQo cacheQo = new UserCacheQo();
    //                cacheQo.setUserId(defaultId);
    //                cacheQo.setRoleType(roleType);
    //                redisUtil.set(userKey, JSON.toJSONString(cacheQo), UserLoginUtil.USER_CACHE_TIME);
    //            }
    //        } else {
    //            dataSource = 2;
    //        }
    //    }
    //
    //    return null;
    //}
    public UserInfo getCurrentUserInfo() {
        String openId = getOpenId();
        if (StringUtils.isEmpty(openId)) {
            return null;
        }

        UserCacheQo cacheQo = getUserCache(openId);
        if (cacheQo == null) {
            return null;
        }

        return getUserInfoByRoleType(cacheQo);
    }

    private String getOpenId() {
        String openId = getCurrentUserOpenIdForHtml();
        if (StringUtils.isEmpty(openId)) {
            openId = getCurrentUserOpenIdWx();
        }

        if (StringUtils.isEmpty(openId) && "test".equals(modelType)) {
            setDefaultUserCache();
            openId = defaultOpenId;
        }

        return openId;
    }

    private void setDefaultUserCache() {
        String userKey = CacheKeyEnum.USER_TOKEN_INFO + defaultOpenId;
        UserCacheQo cacheQo = new UserCacheQo();
        cacheQo.setUserId(defaultId);
        cacheQo.setRoleType(roleType);
        redisUtil.set(userKey, JSON.toJSONString(cacheQo), userCacheTime);
    }

    private UserCacheQo getUserCache(String openId) {
        String userKey = CacheKeyEnum.USER_TOKEN_INFO + openId;
        Object userCacheObject = redisUtil.get(userKey);

        return userCacheObject != null
                ? JSONObject.parseObject(userCacheObject.toString(), UserCacheQo.class)
                : null;
    }

    private UserInfo getUserInfoByRoleType(UserCacheQo cacheQo) {
        switch (cacheQo.getRoleType()) {
            case ConstantEnum.USER_XM:
            case ConstantEnum.USER_ADMIN:
            case ConstantEnum.USER_SERVICE:
                return getUserInfo(cacheQo, CacheKeyEnum.USER_INFO, iXmAdminService);
            case ConstantEnum.USER_SHOP:
                return getUserInfo(cacheQo, CacheKeyEnum.SHOP_INFO, iYxShopInfoService);
            default:
                return null;
        }
    }

    private <T> UserInfo getUserInfo(UserCacheQo cacheQo, CacheKeyEnum cacheKeyEnum, IService<T> service) {
        String cacheKey = cacheKeyEnum + cacheQo.getUserId().toString();
        Object userObject = redisUtil.get(cacheKey);

        if (userObject != null) {
            return JSONObject.parseObject(userObject.toString(), UserInfo.class);
        }

        T info = service.getById(cacheQo.getUserId());
        UserInfo user = extractUserInfo(info);

        if (user != null) {
            redisUtil.set(cacheKey, JSON.toJSONString(user), userCacheTime);
        }

        return user;
    }

    private <T> UserInfo extractUserInfo(T info) {
        // Implement extraction of UserInfo from info
        // The method should be dependent on the type of info
        // You will likely need to implement two versions of this method:
        // one for XmAdmin and one for YxShopInfo
        // Depending on how you organize your code, you may want to make this an interface method
    }
}
