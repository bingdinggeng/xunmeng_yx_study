package com.xunmeng.youxuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.domain.YxShoppingCart;
import com.xunmeng.youxuan.requestqo.CartNumQo;
import com.xunmeng.youxuan.requestqo.CommonIdQo;
import com.xunmeng.youxuan.requestqo.ShoppingCartAddQo;
import com.xunmeng.youxuan.responsedto.CartShopDto;
import com.xunmeng.youxuan.responsedto.CartUserDto;
import com.xunmeng.youxuan.responsedto.ShoppingCartSumDto;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

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
     * @return: com.xunmeng.youxuan.base.Result<org.apache.poi.ss.formula.functions.T>
     * @author LTM
     * @date: 2023/5/26 10:08
     */
    Result<T> addToCart(ShoppingCartAddQo requestModel);

    /**
     * description: /cart/clear 清空购物车  根据商店id shopId 进行清空
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Result<org.apache.poi.ss.formula.functions.T>
     * @author LTM
     * @date: 2023/5/26 15:02
     */
    Result<T> clearCart(CommonIdQo requestModel);

    /**
     * description: /cart/number  购物车商品增减
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Result<org.apache.poi.ss.formula.functions.T>
     * @author LTM
     * @date: 2023/5/26 15:54
     */
    Result<T> cartNumberChanger(CartNumQo requestModel);

    /**
     * description: /cart/list 查看购物车商品列表,ID:shopId
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Result<com.xunmeng.youxuan.responsedto.ShoppingCartSumDto>
     * @author LTM
     * @date: 2023/5/26 16:45
     */
    Result<ShoppingCartSumDto> cartList(CommonIdQo requestModel);

    /**
     * description: /cart/shop/list 查看购物车中商铺及选中商品数量
     * @param:
     * @return: com.xunmeng.youxuan.base.Result<java.util.List<CartShopDto>>
     * @author LTM
     * @date: 2023/5/26 18:00
     */
    Result<List<CartShopDto>> cartShopList();

    /**
     * description: /cart/user/list 用户购物车所有信息
     * @param:
     * @return: com.xunmeng.youxuan.base.Result<java.util.List<com.xunmeng.youxuan.responsedto.CartUserDto>>
     * @author LTM
     * @date: 2023/5/26 18:01
     */
    Result<List<CartUserDto>> userCartList();
}
