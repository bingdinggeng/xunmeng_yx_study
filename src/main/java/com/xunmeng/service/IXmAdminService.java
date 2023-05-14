package com.xunmeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.base.Response;
import com.xunmeng.domain.UserInfo;
import com.xunmeng.domain.XmAdmin;
import com.xunmeng.requestqo.LoginQo;

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


    Response<UserInfo> login(LoginQo requestModel);
}
