package com.xunmeng.youxuan.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ClassName: YxFinancialSettlement
 * Package: com.xunmeng.domain
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/13 16:28
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YxFinancialSettlement对象", description="")
public class YxFinancialSettlement implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自动增长ID")
    @TableId(value = "financial_id", type = IdType.AUTO)
    private Long financialId;

    @ApiModelProperty(value = "提交结算金额")
    private BigDecimal shouldMoney;

    @ApiModelProperty(value = "售后金额")
    private BigDecimal afterSaleMoney;

    @ApiModelProperty(value = "本月处罚金额")
    private BigDecimal punishMoney;

    @ApiModelProperty(value = "实付金额，财务打款金额")
    private BigDecimal actualMoney;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态；0：申请；1：审核通过；2：已打款；-1:拒绝；-100：删除")
    private Integer dataStatus;

    @ApiModelProperty(value = "订单id列表，订单状态修改使用，必须前后端验证")
    private String orderIds;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "商家id")
    private Long shopId;

    @ApiModelProperty(value = "商家名称")
    private String shopName;

    @ApiModelProperty(value = "审核人id")
    private Long operatorId;

    @ApiModelProperty(value = "审核人名称")
    private String operatorName;

    @ApiModelProperty(value = "打款人id")
    private Long moneyOperatorId;

    @ApiModelProperty(value = "打款人名称")
    private String moneyOperatorName;

    @ApiModelProperty(value = "收款账号类型：1：支付宝，2：银行卡号")
    private Integer accountType;

    @ApiModelProperty(value = "收款账号")
    private String accountNo;

    @ApiModelProperty(value = "收款账号对应的姓名")
    private String accountName;

    @ApiModelProperty(value = "收款账号银行，如中国工商银行")
    private String accountBankName;

    @ApiModelProperty(value = "收款账号分行")
    private String accountBankAdress;

    @ApiModelProperty(value = "结算月")
    private String moneyMonth;

    @ApiModelProperty(value = "备注图片")
    private String remarkImage;
}
