package com.xunmeng.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunmeng.domain.YxAdminInfo;
import com.xunmeng.enums.CacheKeyEnum;
import com.xunmeng.enums.ConstantEnum;
import com.xunmeng.service.IYxAdminInfoService;
import com.xunmeng.utils.RedisStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.List;


/**
 * ClassName: BaseController
 * Package: com.xunmeng.controller
 * Description: 基础控制器
 *
 * @Author LTM
 * @Create 2023/5/17 14:48
 * @Version 1.0
 */
@Controller
public class BaseController {
    @Autowired
    private RedisStringUtil redisUtil;
    @Autowired
    private IYxAdminInfoService yxAdminInfoService;

    @Value("${user.cache.time}")
    private static Long userCacheTime;

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
                redisUtil.set(cacheAdminKey, JSON.toJSONString(adminList), userCacheTime);
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
                redisUtil.set(cacheServiceKey, JSON.toJSONString(adminList), userCacheTime);
            }
        }
        return adminList;
    }
}
