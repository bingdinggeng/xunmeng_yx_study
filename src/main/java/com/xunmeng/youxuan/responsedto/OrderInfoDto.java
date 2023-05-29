package com.xunmeng.youxuan.responsedto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xunmeng.youxuan.enums.ConstantEnum;
import com.xunmeng.youxuan.utils.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: OrderInfoDto
 * Package: com.xunmeng.youxuan.responsedto
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/29 14:57
 * @Version 1.0
 */
@Data
@ApiModel("订单信息类")
public class OrderInfoDto {
    @ApiModelProperty(value = "订单ID",example = "12")
    private Long orderId;

    @ApiModelProperty(value = "用户ID",example = "123")
    private Long userId;

    @ApiModelProperty(value = "商户ID",example = "233")
    private Long shopId;

    @ApiModelProperty(value = "店名",example = "三只松鼠")
    private String shopName;

    @ApiModelProperty(value = "店铺头像")
    private String avatar;

    @ApiModelProperty(value = "订单号",example = "20121253123")
    private String orderNo;

    @ApiModelProperty(value = "订单付款选项：0：寻梦账户 1：微信支付",example = "0")
    private Integer payType;

    @ApiModelProperty(value = "商家电话",example = "0563-2115693")
    private String shopTel;

    @ApiModelProperty(value = "收件人电话",example = "1234567895")
    private String userPhone;

    @ApiModelProperty(value = "收件人姓名",example = "波波")
    private String userName;

    @ApiModelProperty(value = "收件人地址",example = "2F130")
    private String address;

    @ApiModelProperty(value = "取单号",example = "12")
    private Integer orderNum;

    @ApiModelProperty(value = "购买数量",example = "6")
    private Integer buyCount;

    @ApiModelProperty(value = "总价，如：18.1,优惠前",example = "23.5")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "状态；0：待付款，默认；1：已付款，2：已接单，3：已发货，4：已送达 5：已收货，-1：取消，-2：用户删除，-100：删除")
    private Integer dataStatus;

    @ApiModelProperty(value = "微信支付状态 0：默认，未支付 1：已支付，待确认 2：确认支付 3：支付失败 4:极速退款中 5：极速退款成功 6：极速退款失败")
    private Integer wxPayStatus;

    @ApiModelProperty(value = "状态名称",example = "已付款")
    private String orderStatusName;

    public String getOrderStatusName() {
        if (dataStatus != null) {
            switch (dataStatus) {
                case 0:
                    orderStatusName = "待付款";
                    break;
                case 1:
                    orderStatusName = "已付款";
                    break;
                case 2:
                    orderStatusName = "已接单";
                    break;
                case 3:
                    orderStatusName = "已发货";
                    break;
                case 4:
                    orderStatusName = "已送达";
                    break;
                case 5:
                    orderStatusName = "已收货";
                    break;
                case -1:
                    orderStatusName = "已取消";
                    break;
                default:
            }
        }
        return orderStatusName;
    }

    @ApiModelProperty(value = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "预约时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime advanceTime;

    @ApiModelProperty(value = "支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "发货时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "收货时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime receivingTime;

    @ApiModelProperty(value = "最后修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备注",example = "备注内容")
    private String remark;

    @ApiModelProperty(value = "是否是预约订单 0：否,1：是",example = "0")
    private Integer advanceFlag;

    @ApiModelProperty(value = "订单商品列表")
    private List<OrderItemDto> items;

    @ApiModelProperty(value = "最后售后时间，每次售后都要改变这个时间，可提现金额按照这个")
    private LocalDateTime afterSaleTime;

    @ApiModelProperty(value = "可发起售后截止时间,能否发起售后依据这个字段,超过此时间不可发起售后",example = "2020-12-19 00:00:00")
    private String afterSaleTimeStr;

    public String getAfterSaleTimeStr() {
        if(afterSaleStatus == null || afterSaleStatus == 0 || afterSaleStatus == 3){
            //可以发起售后的条件下
            if(afterSaleTime != null){
                afterSaleTimeStr = DateTimeUtil.getTimeYMDByLocalDateTime(afterSaleTime.plusDays(ConstantEnum.CAN_AFTER_SALE_TIME));
            }else{
                if(payTime != null){
                    afterSaleTimeStr = DateTimeUtil.getTimeYMDByLocalDateTime(payTime.plusDays(ConstantEnum.CAN_AFTER_SALE_TIME));
                }
            }
        }
        return afterSaleTimeStr;
    }

    @ApiModelProperty(value = "售后金额",example = "125.21")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal afterSaleAmount;

    @ApiModelProperty(value = "售后状态：0：无售后 1：第一次售后，2：第一次商家同意，3：第一次商家拒绝；5：第二次售后：6：第二次商家同意，7：第二次商家拒绝，10：客服介入，11：同意，12：拒绝",example = "1")
    private Integer afterSaleStatus;
}
