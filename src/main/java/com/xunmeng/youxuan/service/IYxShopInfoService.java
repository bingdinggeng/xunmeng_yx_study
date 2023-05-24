package com.xunmeng.youxuan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.domain.YxShopInfo;
import com.xunmeng.youxuan.requestqo.LoginQo;
import com.xunmeng.youxuan.requestqo.ShopListQo;
import com.xunmeng.youxuan.requestqo.ShopPasswordQo;
import com.xunmeng.youxuan.responsedto.ShopDto;

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

    /**
     * description: 商家密码登录
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Response<com.xunmeng.youxuan.domain.UserInfo>
     * @author LTM
     * @date: 2023/5/21 10:14
     */
    Response<UserInfo> loginIn(LoginQo requestModel);


    /**
     * description: 商家修改登录密码
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Response
     * @author LTM
     * @date: 2023/5/21 10:15
     */
    Response passwordChange(ShopPasswordQo requestModel);

    /**
     * description: 商家列表：用户
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Response<com.baomidou.mybatisplus.core.metadata.IPage<com.xunmeng.youxuan.responsedto.ShopDto>>
     * @author LTM
     * @date: 2023/5/24 14:54
     */
    Response<IPage<ShopDto>> getShopList(ShopListQo requestModel);
}
