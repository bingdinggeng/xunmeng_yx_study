package com.xunmeng.youxuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.domain.XmAdmin;
import com.xunmeng.youxuan.requestqo.LoginQo;
import org.apache.poi.ss.formula.functions.T;

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
     * description: /user/login/user 公司内部员工登录
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Result<UserInfo>
     * @author LTM
     * @date: 2023/5/21 10:12
     */
    Result<UserInfo> loginIn(LoginQo requestModel);

    /**
     * description: /user/login/out 登出
     * @param:
     * @return: com.xunmeng.youxuan.base.Result<org.apache.poi.ss.formula.functions.T>
     * @author LTM
     * @date: 2023/5/21 10:13
     */
    Result<T> loginOut();
}
