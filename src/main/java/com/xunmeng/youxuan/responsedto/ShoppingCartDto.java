package com.xunmeng.youxuan.responsedto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * ClassName: ShoppingCartDto
 * Package: com.xunmeng.youxuan.responsedto
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/26 16:44
 * @Version 1.0
 */
@Data
@ApiModel("购物车商品列表展示类")
public class ShoppingCartDto {
    @ApiModelProperty(value = "购物车ID",example = "123")
    private Long cartId;

    @ApiModelProperty(value = "商品ID",example = "111")
    private Long productId;

    @ApiModelProperty(value = "用户ID",example = "11")
    private Long userId;

    @ApiModelProperty(value = "商品名",example = "商品名")
    private String productName;

    @ApiModelProperty(value = "商品图片",example = "https://fuss10.elemecdn.com/c/47/d4de651cd6b074204c7daf0c07b74jpeg.jpeg")
    private String productImage;

    @ApiModelProperty(value = "门店ID，如：1",example = "1")
    private Long shopId;

    @ApiModelProperty(value = "其他sku，如：大甜",example = "sku")
    private String otherSku;

    @ApiModelProperty(value = "购买数量，如：1",example = "1")
    private Integer buyCount;

    @ApiModelProperty(value = "单价",example = "23.5")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;

    @ApiModelProperty(value = "其他单独金额，之后sku使用",example = "21")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal priceOther;
}
