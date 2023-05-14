package com.xunmeng.service.Impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.base.Response;
import com.xunmeng.domain.UserInfo;
import com.xunmeng.domain.XmAdmin;
import com.xunmeng.enums.ConstantEnum;
import com.xunmeng.enums.ErrorCodeEnum;
import com.xunmeng.mapper.XmAdminMapper;
import com.xunmeng.requestqo.LoginQo;
import com.xunmeng.requestqo.Results;
import com.xunmeng.service.IXmAdminService;

import javax.annotation.Resource;
import java.util.List;




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

    @Resource
    private IXmAdminService xmAdminService;
    @Override
    public Response<UserInfo> login(LoginQo requestModel) {
        List<XmAdmin> listUser = xmAdminService.list(new QueryWrapper<XmAdmin>()
                .eq("username",requestModel.getNickName())
                .eq("status", ConstantEnum.NORMAL_XM)
                .eq("comid",ConstantEnum.COM_XUNMENG));
        if(listUser == null || listUser.size() < 1){
            return Results.newFailedResponse(ErrorCodeEnum.INFO_NOT_EXIST);
        }else if(listUser.size() > 1){
            return Results.newFailedResponse(ErrorCodeEnum.INFO_NOT_ONLY);
        }
        String password = requestModel.getPassword() + listUser.get(0).getEncrypt();
        BCrypt.Result res = BCrypt.verifyer().verify(password.toCharArray(),listUser.get(0).getPassword());
        if(!res.verified){
            return Results.newFailedResponse(ErrorCodeEnum.PASSWORD_ERROR);
        }
        return Results.newSuccessResponse(cacheUserInfoForAdmin(DataUtil.getUUID(), null, listUser.get(0),1));
    }
}
