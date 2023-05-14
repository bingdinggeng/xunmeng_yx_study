package com.xunmeng.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.base.Response;
import com.xunmeng.domain.UserInfo;
import com.xunmeng.domain.XmAdmin;
import com.xunmeng.mapper.XmAdminMapper;
import com.xunmeng.requestqo.LoginQo;
import com.xunmeng.service.IXmAdminService;

/**
 * ClassName: XmAdminServiceImpl
 * Package: com.xunmeng.service.Impl
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/14 14:40
 * @Version 1.0
 */
public class XmAdminServiceImpl extends ServiceImpl<XmAdminMapper, XmAdmin> implements IXmAdminService {
    @Override
    public Response<UserInfo> login(LoginQo requestModel) {
        return null;
    }
}
