package com.xunmeng.youxuan.utils;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.checkerframework.checker.units.qual.C;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * ClassName: CookiesUtils
 * Package: com.xunmeng.utils
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/19 14:23
 * @Version 1.0
 */
public class CookiesUtils {

    /**
     * description: 获取cookie的值，不编码
     * @param:
     * @param request
     * @param cookieName
     * @return: java.lang.String
     * @author LTM
     * @date: 2023/5/19 14:26
     */
    public static String getCookieValue(HttpServletRequest request,String cookieName){
        return getCookieValue(request,cookieName, false);
    }

    /**
     * description:  得到cookie的值
     * @param:
     * @param request
     * @param cookieName
     * @param
     * @return: java.lang.String
     * @author LTM
     * @date: 2023/5/19 14:26
     */
    private static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
        Cookie[] cookieList = request.getCookies();
        if(cookieList == null || cookieName == null){
            return null;
        }

        try{
            for (Cookie cookie : cookieList) {
                if(cookie.getName().equals(cookieName)){
                    String value = cookie.getValue();
                    return isDecoder? URLDecoder.decode(value,"UTF-8") : value;
                }
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        return null;
    }

}
