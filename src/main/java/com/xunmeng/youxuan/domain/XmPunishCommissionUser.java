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

/**
 * ClassName: XmPunishCommissionUser
 * Package: com.xunmeng.domain
 * Description:返现专用
 *
 * @Author LTM
 * @Create 2023/5/13 16:23
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XmPunishCommissionUser对象", description="返现专用")
public class XmPunishCommissionUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "小组")
    private Integer teamid;

    @ApiModelProperty(value = "用户")
    private Integer userid;

    @ApiModelProperty(value = "每日零点时间戳")
    private Integer dayTime;

    @ApiModelProperty(value = "小组互查加钱")
    private BigDecimal teamCheckProfit;

    @ApiModelProperty(value = "小组互查减钱")
    private BigDecimal teamCheckLoss;

    @ApiModelProperty(value = "制度处罚加钱")
    private BigDecimal rulePunishProfit;

    @ApiModelProperty(value = "制度处罚减钱")
    private BigDecimal rulePunishLoss;

    @ApiModelProperty(value = "售后问题加钱")
    private BigDecimal afterSaleProfit;

    @ApiModelProperty(value = "售后问题减钱")
    private BigDecimal afterSaleLoss;

    @ApiModelProperty(value = "撤单处罚加钱")
    private BigDecimal cancelOrderProfit;

    @ApiModelProperty(value = "撤单处罚减钱")
    private BigDecimal cancelOrderLoss;

    @ApiModelProperty(value = "任务处罚加钱")
    private BigDecimal taskPunishProfit;

    @ApiModelProperty(value = "任务处罚减钱")
    private BigDecimal taskPunishLoss;

    @ApiModelProperty(value = "发票处罚加钱")
    private BigDecimal invoicePunishProfit;

    @ApiModelProperty(value = "发票处罚减钱")
    private BigDecimal invoicePunishLoss;

    @ApiModelProperty(value = "购买产品加钱")
    private BigDecimal buyProductProfit;

    @ApiModelProperty(value = "购买产品减钱")
    private BigDecimal buyProductLoss;

    @ApiModelProperty(value = "早餐消费加钱")
    private BigDecimal breakfastProfit;

    @ApiModelProperty(value = "早餐消费扣钱")
    private BigDecimal breakfastLoss;

    @ApiModelProperty(value = "美团订单提成加钱")
    private BigDecimal mtOrderProfit;

    @ApiModelProperty(value = "美团订单提成减钱")
    private BigDecimal mtOrderLoss;

    @ApiModelProperty(value = "点名好评提成加钱")
    private BigDecimal namePraiseProfit;

    @ApiModelProperty(value = "点名好评提成减钱")
    private BigDecimal namePraiseLoss;

    @ApiModelProperty(value = "凌晨订单提成加钱")
    private BigDecimal morningOrderProfit;

    @ApiModelProperty(value = "凌晨订单提成减钱")
    private BigDecimal morningOrderLoss;

    @ApiModelProperty(value = "蛋糕拉新提成加钱")
    private BigDecimal cakeShopProfit;

    @ApiModelProperty(value = "蛋糕拉新提成减钱")
    private BigDecimal cakeShopLoss;

    @ApiModelProperty(value = "大额订单提成加钱")
    private BigDecimal bigOrderProfit;

    @ApiModelProperty(value = "大额订单提成减钱")
    private BigDecimal bigOrderLoss;

    @ApiModelProperty(value = "美团组长提成加钱")
    private BigDecimal mtLeaderProfit;

    @ApiModelProperty(value = "美团组长提成减钱")
    private BigDecimal mtLeaderLoss;

    @ApiModelProperty(value = "下单统计提成加钱")
    private BigDecimal placeOrdersProfit;

    @ApiModelProperty(value = "下单统计提成减钱")
    private BigDecimal placeOrdersLoss;

    private Integer status;

    private Integer createTime;

    private Integer updateTime;

    @ApiModelProperty(value = "外卖要店提成加钱")
    private BigDecimal waimaiShopProfit;

    @ApiModelProperty(value = "外卖要店提成减钱")
    private BigDecimal waimaiShopLoss;
}

