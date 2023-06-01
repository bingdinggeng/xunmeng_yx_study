package com.xunmeng.youxuan.utils;

import com.xunmeng.youxuan.requestqo.PageRequestQo;

/**
 * ClassName: PageUtil
 * Package: com.xunmeng.youxuan.utils
 * Description:  分页处理工具类
 *
 * @Author LTM
 * @Create 2023/5/24 14:59
 * @Version 1.0
 */
public class PageUtil {
    /**
     * 默认每页大小
     */
    public final static Integer PAGE_SIZE = 10;
    public static void initRequestPage(PageRequestQo requestModel){
        if(requestModel.getPageSize() == null || requestModel.getPageSize() < 1){
            requestModel.setPageSize(PAGE_SIZE);
        }
        if(requestModel.getPageIndex() == null || requestModel.getPageIndex() < 1){
            requestModel.setPageIndex(1);
        }
    }
}
