package com.xunmeng.youxuan.requestqo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ClassName: OrderSaveQo
 * Package: com.xunmeng.youxuan.requestqo
 * Description:
 *
 * @Author LTM
 * @Create 2023/6/1 15:16
 * @Version 1.0
 */
@Data
@ApiModel("生成订单请求类")
public class OrderSaveQo {
    @ApiModelProperty(value = "购物车ID集合",example = "[1,2,3,4]")
    @NotNull(message = "购物车ID集合缺失")
    private List<Long> cartIds;

    @ApiModelProperty(value = "门店ID",example = "233")
    @NotNull(message = "门店ID缺失")
    @Min(value = 1,message = "门店ID格式不正确")
    private Long shopId;
}
