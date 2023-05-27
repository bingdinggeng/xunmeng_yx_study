package com.xunmeng.youxuan.responsedto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * ClassName: CartUserDto
 * Package: com.xunmeng.youxuan.responsedto
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/26 18:01
 * @Version 1.0
 */
@Data
@ApiModel("用户购物车列表信息")
public class CartUserDto {
    @ApiModelProperty(value = "门店ID，如：1", position = 1)
    private Long shopId;

    @ApiModelProperty(value = "门店名称",example = "奶茶店", position = 2)
    private String shopName;

    @ApiModelProperty(value = "购物车商品列表", position = 3)
    private List<ShoppingCartDto> products;
}
