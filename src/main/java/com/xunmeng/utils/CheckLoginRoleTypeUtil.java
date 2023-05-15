package com.xunmeng.utils;

import com.xunmeng.enums.CacheKeyEnum;


/**
 * ClassName: CheckLoginRoleTypeUtil
 * Package: com.xunmeng.utils
 * Description: 判断寻梦员工登录的类型
 *
 * @Author LTM
 * @Create 2023/5/15 11:41
 * @Version 1.0
 */
public class CheckLoginRoleTypeUtil {

    public static boolean checkAdmin(Integer userId,RedisStringUtil redisStringUtil){
        String cacheAdmin = CacheKeyEnum.USER_ADMIN_LIST;
        Object adminObject = redisStringUtil.get(cacheAdmin);
        return true;
    }

    public static boolean checkService(Integer userId,RedisStringUtil redisStringUtil) {
        return true;
    }
}
