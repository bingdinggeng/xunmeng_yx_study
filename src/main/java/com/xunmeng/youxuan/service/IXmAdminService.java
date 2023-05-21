package com.xunmeng.youxuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.domain.XmAdmin;
import com.xunmeng.youxuan.requestqo.LoginQo;

/**
 * ClassName: IXmAdminService
 * Package: com.xunmeng.service
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/14 14:37
 * @Version 1.0
 */
public interface IXmAdminService extends IService<XmAdmin> {


    /**
     * description: 公司内部员工密码登录
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Response<com.xunmeng.youxuan.domain.UserInfo>
     * @author LTM
     * @date: 2023/5/21 10:12
     */
    Response<UserInfo> loginIn(LoginQo requestModel);

    /**
     * description: 登出
     * @param:
     * @return: com.xunmeng.youxuan.base.Response
     * @author LTM
     * @date: 2023/5/21 10:13
     */
    Response loginOut();
}
