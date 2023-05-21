package com.xunmeng.youxuan.responsedto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ClassName: SumFinanceShopDto
 * Package: com.xunmeng.youxuan.responsedto
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/21 9:27
 * @Version 1.0
 */
@Data
@ApiModel("商户财务信息外部信息类")
public class SumFinanceShopDto implements Serializable {
    @ApiModelProperty(value = "总金额",example = "96456465.01")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal totalMoney;

    @ApiModelProperty(value = "总退款金额",example = "123.01")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal totalAfterSaleMoney;

    @ApiModelProperty(value = "总可结算金额",example = "321.01")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal totalGetMoney;


    @ApiModelProperty(value = "当前页金额'",example = "4216.99")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal pageMoney;

    @ApiModelProperty(value = "当前页退款金额",example = "123.01")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal pageAfterSaleMoney;


    @ApiModelProperty(value = "当前页请求数据'")
    private IPage<FinanceShopDto> data;
}
