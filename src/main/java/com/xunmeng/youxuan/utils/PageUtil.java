package com.xunmeng.youxuan.utils;

import com.xunmeng.youxuan.config.PageConfig;
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

    public static void initRequestPage(PageRequestQo requestModel){
        if(requestModel.getPageSize() == null || requestModel.getPageSize() < 1){
            requestModel.setPageSize(PageConfig.PAGE_SIZE);
        }
        if(requestModel.getPageSize() == null || requestModel.getPageIndex() < 1){
            requestModel.setPageIndex(1);
        }
    }
}
