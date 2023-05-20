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
 * ClassName: YxOrderInfo
 * Package: com.xunmeng.domain
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/13 16:31
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YxOrderInfo对象", description="")
public class YxOrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自动增长ID")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "商户ID")
    private Long shopId;

    @ApiModelProperty(value = "店名")
    private String shopName;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "订单付款选项：0：寻梦账户 1：微信支付")
    private Integer payType;

    @ApiModelProperty(value = "收件人电话")
    private String userPhone;

    @ApiModelProperty(value = "收件人姓名")
    private String userName;

    @ApiModelProperty(value = "收件人地址")
    private String address;

    @ApiModelProperty(value = "取单号")
    private Integer orderNum;

    @ApiModelProperty(value = "购买数量")
    private Integer buyCount;

    @ApiModelProperty(value = "总价，如：18.1,优惠前")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "微信支付状态 0：默认，未支付 1：已支付，待确认 2：确认支付 3：支付失败 4:极速退款中 5：极速退款成功 6：极速退款失败")
    private Integer wxPayStatus;

    @ApiModelProperty(value = "微信退款单号")
    private String wxRefundId;

    @ApiModelProperty(value = "状态；0：待付款，默认；1：已付款，2：已接单，3：已发货，4：已送达，5：已收货，-1：取消，-2：用户删除，-100：删除")
    private Integer dataStatus;

    @ApiModelProperty(value = "订单来源；1：网页，2：微信申请")
    private Integer dataSource;

    @ApiModelProperty(value = "微信formId，发送消息时使用")
    private String wxFormId;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "发货时间")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "收货时间")
    private LocalDateTime receivingTime;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "操作用户id，如发货等")
    private Long operatorId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "预约时间")
    private LocalDateTime advanceTime;

    @ApiModelProperty(value = "是否是预约订单 0：否,1：是")
    private Integer advanceFlag;

    @ApiModelProperty(value = "店铺头像")
    private String avatar;

    @ApiModelProperty(value = "最后售后时间，每次售后都要改变这个时间，可提现金额按照这个")
    private LocalDateTime afterSaleTime;

    @ApiModelProperty(value = "售后金额")
    private BigDecimal afterSaleAmount;

    @ApiModelProperty(value = "售后状态：1：第一次售后，2：第一次商家同意，3：第一次商家拒绝；5：第二次售后：6：第二次商家同意，7：第二次商家拒绝，10：客服介入，11：同意，12：拒绝")
    private Integer afterSaleStatus;

    @ApiModelProperty(value = "微信付款时间")
    private LocalDateTime wxPayTime;

    @ApiModelProperty(value = "微信付款编号")
    private String wxPayId;
}
