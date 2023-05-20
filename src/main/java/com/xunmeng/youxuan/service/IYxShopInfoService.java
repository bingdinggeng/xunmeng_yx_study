package com.xunmeng.youxuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.domain.YxShopInfo;
import com.xunmeng.youxuan.requestqo.LoginQo;

/**
 * ClassName: IYxShopInfoService
 * Package: com.xunmeng.service
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/20 8:58
 * @Version 1.0
 */
public interface IYxShopInfoService extends IService<YxShopInfo> {

    Response<UserInfo> loginIn(LoginQo requestModel);
}
