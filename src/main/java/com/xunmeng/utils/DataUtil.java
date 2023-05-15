package com.xunmeng.utils;

import java.util.UUID;

/**
 * ClassName: DataUtil
 * Package: com.xunmeng.utils
 * Description: 数据处理工具类
 *
 * @Author LTM
 * @Create 2023/5/15 10:13
 * @Version 1.0
 */
public class DataUtil {
    /**
     * description:获取UUID
     * @param:
     * @return: java.lang.String
     * @author LTM
     * @date: 2023/5/15 10:15
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
