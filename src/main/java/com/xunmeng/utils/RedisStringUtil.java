package com.xunmeng.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: RedisStringUtil
 * Package: com.xunmeng.utils
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/15 10:36
 * @Version 1.0
 */
@Component
public class RedisStringUtil {

    private final StringRedisTemplate redisTemplate;

    public RedisStringUtil(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    /**
     * description: 普通缓存放入
     * @param:
     * @param key
     * @param value
     * @return: boolean
     * @author LTM
     * @date: 2023/5/15 10:43
     */
    public boolean set(String key,String value){
        try{
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * description: 普通缓存放入并设置时间
     * @param:
     * @param key
     * @param value
     * @param time
     * @return: boolean
     * @author LTM
     * @date: 2023/5/15 10:49
     */
    public boolean set(String key, String value, long time){
        try {
            if(time > 0){
                redisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
            }else{
                set(key,value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Object get(String key){

        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * description: 删除指定的缓存
     * @param:
     * @param cacheKey
     * @return: boolean
     * @author LTM
     * @date: 2023/5/19 15:40
     */
    public boolean deleteRedis(String cacheKey) {
        try{
            return redisTemplate.delete(cacheKey);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
