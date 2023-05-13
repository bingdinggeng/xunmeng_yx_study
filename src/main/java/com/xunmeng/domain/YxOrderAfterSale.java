package com.xunmeng.domain;

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
 * ClassName: YxOrderAfterSale
 * Package: com.xunmeng.domain
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/13 16:30
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YxOrderAfterSale对象", description="")
public class YxOrderAfterSale implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自动增长ID")
    @TableId(value = "after_sale_id", type = IdType.AUTO)
    private Long afterSaleId;

    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "用户理由")
    private String userReason;

    @ApiModelProperty(value = "用户申请图片")
    private String userImages;

    @ApiModelProperty(value = "商家理由")
    private String shopReason;

    @ApiModelProperty(value = "客服回复图片")
    private String shopImages;

    @ApiModelProperty(value = "客服理由")
    private String customerReason;

    @ApiModelProperty(value = "客服回复图片")
    private String customerImages;

    @ApiModelProperty(value = "客服最终裁决金额")
    private BigDecimal customerAmount;

    @ApiModelProperty(value = "订单金额，用于某些条例的原订单赔付金额")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "申请金额")
    private BigDecimal applyAmount;

    @ApiModelProperty(value = "售后状态：1：第一次售后，2：第一次商家同意，3：第一次商家拒绝；5：第二次售后：6：第二次商家同意，7：第二次商家拒绝，10：客服介入，11：同意，12：拒绝，-1：取消，-100：删除")
    private Integer dataStatus;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "申请用户id")
    private Long applyOperatorId;

    @ApiModelProperty(value = "申请人名称")
    private String applyOperatorName;

    @ApiModelProperty(value = "商家id")
    private Long shopId;

    @ApiModelProperty(value = "商家名称")
    private String shopName;

    @ApiModelProperty(value = "客服id")
    private Long customerOperatorId;

    @ApiModelProperty(value = "客服名称")
    private String customerOperatorName;
}
