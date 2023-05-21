package com.xunmeng.youxuan.requestqo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ClassName: FinanceShopQo
 * Package: com.xunmeng.youxuan.requestqo
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/21 9:46
 * @Version 1.0
 */
@Data
@ApiModel("商家财务查询")
public class FinanceShopQo extends PageRequestQo implements Serializable {

    @ApiModelProperty(value = "订单开始时间",example = "2020-12-08 00:00:00",position = 1)
    @NotNull(message = "开始时间缺失")
    private String startTime;

    @ApiModelProperty(value = "订单开始时间",example = "2020-12-08 00:00:00",position = 2)
    private String endTime;
}
