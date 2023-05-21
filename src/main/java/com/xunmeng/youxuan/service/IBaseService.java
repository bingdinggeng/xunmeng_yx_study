package com.xunmeng.youxuan.service;

import com.xunmeng.youxuan.domain.YxAdminInfo;

import java.util.List;

/**
 * ClassName: IBaseService
 * Package: com.xunmeng.youxuan.service
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/21 10:53
 * @Version 1.0
 */
public interface IBaseService {

    /**
     * description: 判断当前登陆人员是否为优选管理员
     * @param:
     * @param userId
     * @return: boolean
     * @author LTM
     * @date: 2023/5/21 10:56
     */
    boolean checkAdmin(Integer userId);

    /**
     * description: 判断当前登陆人员是否为优选客服
     * @param:
     * @param userId
     * @return: boolean
     * @author LTM
     * @date: 2023/5/21 10:57
     */
    boolean checkService(Integer userId);

    /**
     * description: 获取客服列表
     * @param:
     * @return: java.util.List<com.xunmeng.youxuan.domain.YxAdminInfo>
     * @author LTM
     * @date: 2023/5/21 10:58
     */
    List<YxAdminInfo> getServiceList();


    /**
     * description: 获取当前请求人的ID
     * @param:
     * @return: java.lang.String
     * @author LTM
     * @date: 2023/5/21 10:58
     */
    String getCurrentUserOpenId();


}
