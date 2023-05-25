package com.xunmeng.youxuan.logic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.domain.XmAdmin;
import com.xunmeng.youxuan.domain.YxAdminInfo;
import com.xunmeng.youxuan.domain.YxShopInfo;
import com.xunmeng.youxuan.enums.CacheKeyEnum;
import com.xunmeng.youxuan.enums.ConstantEnum;
import com.xunmeng.youxuan.requestqo.UserCacheQo;
import com.xunmeng.youxuan.service.IXmAdminService;
import com.xunmeng.youxuan.service.IYxAdminInfoService;
import com.xunmeng.youxuan.service.IYxShopInfoService;
import com.xunmeng.youxuan.utils.CookiesUtils;
import com.xunmeng.youxuan.utils.RedisStringUtil;
import com.xunmeng.youxuan.utils.UserLoginUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.xunmeng.youxuan.utils.UserLoginUtil.MODEL_TYPE;
import static com.xunmeng.youxuan.utils.UserLoginUtil.USER_CACHE_TIME;

/**
 * ClassName: BaseLogic
 * Package: com.xunmeng.youxuan.service.Impl
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/21 10:54
 * @Version 1.0
 */

@Service
@RequiredArgsConstructor
public class BaseLogic {
    private  final RedisStringUtil redisUtil;
    private  final IYxAdminInfoService yxAdminInfoService;
    private  final IXmAdminService xmAdminService;
    private  final IYxShopInfoService yxShopInfoService;
    private String defaultOpenId =  "sdfsdfsdfqw41231231wer121";
    private Integer defaultId = 23896;
    private int roleType = ConstantEnum.USER_SERVICE;


    /**
     * TODO 可能有多线程安全问题
     */
    private int dataSource = 1;


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

        if(StringUtils.isEmpty(openId) && "test".equals(MODEL_TYPE)){
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
     * @date: 2023/5/23 8:31
     */
    public UserInfo getCurrentUserInfo(){
        dataSource = 1;
        String openId = getOpenId();

        if(StringUtils.isEmpty(openId)){
            return null;
        }

        UserCacheQo cacheQo = getUserCache(openId);
        if(cacheQo == null){
            return null;
        }

        return getUserInfoByRoleType(cacheQo);
    }


    /**
     * 拆分原方法中的获取OpenId
     * @return
     */
    private String getOpenId(){
        String openId = getCurrentUserOpenIdForHtml();
        if(StringUtils.isEmpty(openId)){
            openId = getCurrentUserOpenIdWx();
            dataSource = 2;
        }

        if(StringUtils.isEmpty(openId) && "test".equals(MODEL_TYPE)){
            setDefaultUserCache();;
            openId = defaultOpenId;
        }
        return openId;
    }

    /**
     * 拆分原getCurrentUserInfo方法中的设置默认缓存
     */
    private void setDefaultUserCache(){
        String userKey = CacheKeyEnum.USER_TOKEN_INFO + defaultOpenId;
        UserCacheQo cacheQo = new UserCacheQo();
        cacheQo.setUserId(defaultId);
        cacheQo.setRoleType(roleType);
        redisUtil.set(userKey, JSON.toJSONString(cacheQo), USER_CACHE_TIME);
    }


    /**
     *  拆分原getCurrentUserInfo方法中的获取用户缓存
     * @param openId
     * @return
     */
    private UserCacheQo getUserCache(String openId){
        String userKey = CacheKeyEnum.USER_TOKEN_INFO + openId;
        Object userCacheObject = redisUtil.get(userKey);

        return userCacheObject != null
                ? JSONObject.parseObject(userCacheObject.toString(), UserCacheQo.class)
                : null;
    }


    /**
     *  返回user
     * @param cacheQo
     * @return
     */
    private UserInfo getUserInfoByRoleType(UserCacheQo cacheQo){
        String cacheKey;
        Object userObject;
        if (cacheQo.getRoleType().equals(ConstantEnum.USER_XM)  || cacheQo.getRoleType().equals(ConstantEnum.USER_ADMIN)
                || cacheQo.getRoleType().equals(ConstantEnum.USER_SERVICE)){
            cacheKey = CacheKeyEnum.USER_TOKEN_INFO + cacheQo.getUserId();
            userObject = redisUtil.get(cacheKey);

            if(userObject != null){
                return JSONObject.parseObject(userObject.toString(), UserInfo.class);
            }

            XmAdmin userInfo = xmAdminService.getById(cacheQo.getUserId());
            if (userInfo != null && userInfo.getStatus() != null && userInfo.getStatus().equals(ConstantEnum.NORMAL_XM)){
                UserInfo user = extractUserInfo(userInfo);

                if (checkAdmin(userInfo.getUserId())) {
                    user.setRoleType(ConstantEnum.USER_ADMIN);
                }else if(checkService(userInfo.getUserId())){
                    user.setRoleType(ConstantEnum.USER_SERVICE);
                } else {
                    user.setRoleType(ConstantEnum.USER_XM);
                }

                redisUtil.set(cacheKey, JSON.toJSONString(user), USER_CACHE_TIME);
                return user;
            }
        }else if (cacheQo.getRoleType().equals(ConstantEnum.USER_SHOP)){
            cacheKey = CacheKeyEnum.SHOP_INFO + cacheQo.getUserId();
            userObject = redisUtil.get(cacheKey);

            if(userObject != null){
                return JSONObject.parseObject(userObject.toString(), UserInfo.class);
            }

            YxShopInfo shopInfo = yxShopInfoService.getById(cacheQo.getUserId());
            if (shopInfo != null && shopInfo.getDataStatus() != null && !shopInfo.getDataStatus()
                    .equals(ConstantEnum.SHOP_STATUS_CLOSE)){
                UserInfo user = extractUserInfo(shopInfo);
                redisUtil.set(cacheKey, JSON.toJSONString(user), USER_CACHE_TIME);
                return user;
            }
        }
        return null;
    }

    private <T> UserInfo extractUserInfo(T info) {
        UserInfo user = new UserInfo();
        if (info instanceof XmAdmin) {
            XmAdmin admin = (XmAdmin) info;
            user.setMobileNumber(admin.getPhone())
                    .setNickName(admin.getUserName())
                    .setOpenId(admin.getWxOpenId())
                    .setRealName(admin.getNickName())
                    .setUserId(admin.getUserId().longValue())
                    .setAvatar(admin.getAvatar());
        } else if (info instanceof YxShopInfo) {
            YxShopInfo shopInfo = (YxShopInfo) info;
            user.setMobileNumber(shopInfo.getShopPhone())
                    .setNickName(shopInfo.getShopName())
                    .setOpenId(shopInfo.getWxOpenId())
                    .setRealName(shopInfo.getRealName())
                    .setRoleType(ConstantEnum.USER_SHOP)
                    .setUserId(shopInfo.getShopId())
                    .setAvatar(shopInfo.getAvatar());
        }
        user.setDataSource(dataSource);
        return user;
    }
}
