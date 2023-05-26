package com.xunmeng.youxuan.requestqo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * ClassName: ShoppingCartAddQo
 * Package: com.xunmeng.youxuan.requestqo
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/26 10:04
 * @Version 1.0
 */
@Data
@ApiModel("加入购物车请求类")
public class ShoppingCartAddQo {
    @ApiModelProperty(value = "商品ID",example = "123")
    @NotNull(message = "商品ID缺失")
    @Min(value = 1,message = "商品ID格式不正确")
    private Long productId;

    @ApiModelProperty(value = "门店ID",example = "233")
    @NotNull(message = "门店ID缺失")
    @Min(value = 1,message = "门店ID格式不正确")
    private Long shopId;

    @ApiModelProperty(value = "其他属性，,分隔",example = "123,256")
    private String otherSku;
}
