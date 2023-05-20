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


    Response<UserInfo> loginIn(LoginQo requestModel);

    Response loginOut();
}
