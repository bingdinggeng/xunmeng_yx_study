package com.xunmeng.youxuan.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunmeng.youxuan.domain.YxAdminInfo;
import com.xunmeng.youxuan.enums.CacheKeyEnum;
import com.xunmeng.youxuan.enums.ConstantEnum;
import com.xunmeng.youxuan.service.IBaseService;
import com.xunmeng.youxuan.service.IYxAdminInfoService;
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

/**
 * ClassName: BaseServiceImpl
 * Package: com.xunmeng.youxuan.service.Impl
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/21 10:54
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class BaseServiceImpl implements IBaseService {
    private  final RedisStringUtil redisUtil;
    private  final IYxAdminInfoService yxAdminInfoService;
    private String defaultOpenId =  "sdfsdfsdfqw41231231wer121";


    /**
     * description: 判断当前登陆人员是否为优选管理员
     * @param:
     * @param userId
     * @return: boolean
     * @author LTM
     * @date: 2023/5/17 15:13
     */
    @Override
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
    @Override
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
    @Override
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
    @Override
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
}
