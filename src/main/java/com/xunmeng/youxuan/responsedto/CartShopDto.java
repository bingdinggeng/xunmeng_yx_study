package com.xunmeng.youxuan.responsedto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: CartShopDto
 * Package: com.xunmeng.youxuan.responsedto
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/26 17:58
 * @Version 1.0
 */
@Data
@ApiModel("购物车商铺商品信息类")
public class CartShopDto {
    @ApiModelProperty(value = "门店ID，如：1")
    private Long shopId;

    @ApiModelProperty(value = "门店名称",example = "奶茶店")
    private String shopName;

    @ApiModelProperty(value = "已选数量",example = "12")
    private Integer buyCount;
}
