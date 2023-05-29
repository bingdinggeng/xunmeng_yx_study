package com.xunmeng.youxuan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.domain.YxShopInfo;
import com.xunmeng.youxuan.requestqo.LoginQo;
import com.xunmeng.youxuan.requestqo.ShopListQo;
import com.xunmeng.youxuan.requestqo.ShopPasswordQo;
import com.xunmeng.youxuan.requestqo.WXShopInfoQo;
import com.xunmeng.youxuan.responsedto.ShopDto;
import org.apache.poi.ss.formula.functions.T;

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
     * description: /user/login/shop 商家密码登录
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Result<com.xunmeng.youxuan.domain.UserInfo>
     * @author LTM
     * @date: 2023/5/21 10:14
     */
    Result<UserInfo> loginIn(LoginQo requestModel);


    /**
     * description:  /user/password/shop/change 商家修改登录密码
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Result<org.apache.poi.ss.formula.functions.T>
     * @author LTM
     * @date: 2023/5/21 10:15
     */
    Result<T> passwordChange(ShopPasswordQo requestModel);

    /**
     * description: /shop/list 商家列表：用户
     * @param:
     * @param requestModel
     * @return: Result<IPage<ShopDto>>
     * @author LTM
     * @date: 2023/5/24 14:54
     */
    Result<IPage<ShopDto>> getShopList(ShopListQo requestModel);

    /**
     * description: /user/register/shop  店铺注册
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Result<com.xunmeng.youxuan.domain.UserInfo>
     * @author LTM
     * @date: 2023/5/27 14:50
     */
    Result<UserInfo> registerForShop(WXShopInfoQo requestModel);
}
