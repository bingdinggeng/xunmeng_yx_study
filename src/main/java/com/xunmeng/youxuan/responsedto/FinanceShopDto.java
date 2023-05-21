package com.xunmeng.youxuan.responsedto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ClassName: FinanceShopDto
 * Package: com.xunmeng.youxuan.responsedto
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/21 9:38
 * @Version 1.0
 */
@Data
@ApiModel("财务商家信息类")
public class FinanceShopDto implements Serializable {
    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "收件人姓名")
    private String userName;

    @ApiModelProperty(value = "取单号")
    private Integer orderNum;

    @ApiModelProperty(value = "门店ID")
    private Long shopId;

    @ApiModelProperty(value = "店名")
    private String shopName;

    @ApiModelProperty(value = "门头照")
    private String doorPhoto;

    @ApiModelProperty(value = "总价，如：18.1,优惠前")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "付款时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "送达时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "最后售后时间，每次售后都要改变这个时间，可提现金额按照这个")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime afterSaleTime;

    @ApiModelProperty(value = "售后金额")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal afterSaleAmount;

    @ApiModelProperty(value = "售后状态：1：第一次售后，2：第一次商家同意，3：第一次商家拒绝；5：第二次售后：6：第二次商家同意，7：第二次商家拒绝，10：客服介入，11：同意，12：拒绝")
    private Integer afterSaleStatus;

}