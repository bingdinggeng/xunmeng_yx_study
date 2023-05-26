package com.xunmeng.youxuan.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.requestqo.CartNumQo;
import com.xunmeng.youxuan.requestqo.CommonIdQo;
import com.xunmeng.youxuan.requestqo.ShoppingCartAddQo;
import com.xunmeng.youxuan.responsedto.CartShopDto;
import com.xunmeng.youxuan.responsedto.CartUserDto;
import com.xunmeng.youxuan.responsedto.ShoppingCartSumDto;
import com.xunmeng.youxuan.service.IYxShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: YxShoppingCartController
 * Package: com.xunmeng.youxuan.controller
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/26 9:53
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(tags = "6.0 购物车管理接口", value = " 购物车管理接口")
@ApiSort(value = 6)
@RequestMapping("/cart")
@RequiredArgsConstructor
public class YxShoppingCartController {
    private final IYxShoppingCartService yxShoppingCartService;

    @ApiOperation(value = "加入购物车", notes = "加入购物车")
    @PostMapping("/add")
    public Response addToCart(@RequestBody @Validated ShoppingCartAddQo requestModel) {
        return yxShoppingCartService.addToCart(requestModel);
    }

    @ApiOperation(value = "清空购物车,ID:shopId", notes = "清空购物车,ID:shopId")
    @PostMapping("/clear")
    public Response clearCart(@RequestBody @Validated CommonIdQo requestModel){
        return yxShoppingCartService.clearCart(requestModel);
    }

    @ApiOperation(value = "购物车商品加减", notes = "购物车商品加减")
    @PostMapping("/number")
    public Response cartNumberChange(@RequestBody @Validated CartNumQo requestModel) {
        return yxShoppingCartService.cartNumberChanger(requestModel);
    }

    @ApiOperation(value = "查看购物车商品列表,ID:shopId", notes = "查看购物车商品列表,ID:shopId")
    @PostMapping("/list")
    public Response<ShoppingCartSumDto> cartList(@RequestBody @Validated CommonIdQo requestModel){
        return yxShoppingCartService.cartList(requestModel);
    }

    @ApiOperation(value = "查看购物车中商铺及选中商品数量", notes = "查看购物车中商铺及选中商品数量")
    @PostMapping("/shop/list")
    public Response<List<CartShopDto>> cartShopList(){
        return yxShoppingCartService.cartShopList();
    }

    @ApiOperation(value = "用户购物车所有信息", notes = "用户购物车所有信息")
    @PostMapping("/user/list")
    public Response<List<CartUserDto>> userCartList(){
        return yxShoppingCartService.userCartList();
    }
}
