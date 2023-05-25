package com.xunmeng.youxuan.responsedto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * ClassName: UserLimitDto
 * Package: com.xunmeng.youxuan.responsedto
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/21 13:41
 * @Version 1.0
 */
@Data
@ApiModel("用户限额信息类")
@Accessors(chain = true)
public class UserLimitDto {
    @ApiModelProperty(value = "月：如2020-01",example = "2020-01")
    private String monthStr;

    @ApiModelProperty(value = "月消费金额",example = "233.25")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal monthMoney;

    @ApiModelProperty(value = "每月最大金额",example = "1500")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal monthMax;

    @ApiModelProperty(value = "日：如2020-01-01",example = "2020-12-21")
    private String dayStr;

    @ApiModelProperty(value = "日消费金额",example = "155.23")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal dayMoney;

    @ApiModelProperty(value = "每天最大金额",example = "200")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal dayMax;
}
