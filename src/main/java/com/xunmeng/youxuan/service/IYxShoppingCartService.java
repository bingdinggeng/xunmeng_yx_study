package com.xunmeng.youxuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.domain.YxShoppingCart;
import com.xunmeng.youxuan.requestqo.CartNumQo;
import com.xunmeng.youxuan.requestqo.CommonIdQo;
import com.xunmeng.youxuan.requestqo.ShoppingCartAddQo;
import com.xunmeng.youxuan.responsedto.ShoppingCartSumDto;

/**
 * ClassName: IYxShoppingCartService
 * Package: com.xunmeng.youxuan.service
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/26 10:06
 * @Version 1.0
 */
public interface IYxShoppingCartService extends IService<YxShoppingCart> {
    /**
     * description: /cart/add 加入购物车
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Response
     * @author LTM
     * @date: 2023/5/26 10:08
     */
    Response addToCart(ShoppingCartAddQo requestModel);

    /**
     * description: /cart/clear 清空购物车  根据商店id shopId 进行清空
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Response
     * @author LTM
     * @date: 2023/5/26 15:02
     */
    Response clearCart(CommonIdQo requestModel);

    /**
     * description: /cart/number  购物车商品增减
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Response
     * @author LTM
     * @date: 2023/5/26 15:54
     */
    Response cartNumberChanger(CartNumQo requestModel);

    /**
     * description:  /cart/list 查看购物车商品列表,ID:shopId
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Response<com.xunmeng.youxuan.responsedto.ShoppingCartSumDto>
     * @author LTM
     * @date: 2023/5/26 16:45
     */
    Response<ShoppingCartSumDto> cartList(CommonIdQo requestModel);
}
