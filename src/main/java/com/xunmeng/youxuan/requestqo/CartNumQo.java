package com.xunmeng.youxuan.requestqo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * ClassName: CartNumQo
 * Package: com.xunmeng.youxuan.requestqo
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/26 15:53
 * @Version 1.0
 */
@Data
@ApiModel("购物车数量加减请求类")
public class CartNumQo {
    @ApiModelProperty(value = "购物车ID",example = "123")
    @NotNull(message = "购物车ID缺失")
    @Min(value = 1,message = "购物车ID格式不正确")
    private Long cartId;

    @ApiModelProperty(value = "数量 正数为加  负数为减",example = "-1")
    @NotNull(message = "数量缺失")
    private Integer number;
}
