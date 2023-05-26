package com.xunmeng.youxuan.responsedto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * ClassName: ShoppingCartSumDto
 * Package: com.xunmeng.youxuan.responsedto
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/26 16:43
 * @Version 1.0
 */
@Data
@ApiModel("购物车总价信息类")
@Accessors(chain = true)
public class ShoppingCartSumDto {
    @ApiModelProperty(value = "总金额",example = "23.5")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal sumPrice;

    @ApiModelProperty(value = "购物车列表信息")
    private List<ShoppingCartDto> cartList;
}
