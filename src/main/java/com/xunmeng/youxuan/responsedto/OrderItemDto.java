package com.xunmeng.youxuan.responsedto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ClassName: OrderItemDto
 * Package: com.xunmeng.youxuan.responsedto
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/29 14:58
 * @Version 1.0
 */
@Data
@ApiModel("订单商品信息类")
public class OrderItemDto {
    @ApiModelProperty(value = "订单商品ID",example = "1223")
    private Long orderItemId;

    @ApiModelProperty(value = "订单ID",example = "11")
    private Long orderId;

    @ApiModelProperty(value = "订单号",example = "11")
    private String orderNo;

    @ApiModelProperty(value = "用户ID",example = "11")
    private Long userId;

    @ApiModelProperty(value = "商户ID",example = "11")
    private Long shopId;

    @ApiModelProperty(value = "商品ID",example = "11")
    private Long productId;

    @ApiModelProperty(value = "商品名",example = "商品")
    private String productName;

    @ApiModelProperty(value = "商品图片",example = "https://fuss10.elemecdn.com/c/47/d4de651cd6b074204c7daf0c07b74jpeg.jpeg")
    private String productImage;

    @ApiModelProperty(value = "其他sku名称",example = "...")
    private String otherSku;

    @ApiModelProperty(value = "sku对应的价格，如：18.1",example = "21.2")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;

    @ApiModelProperty(value = "购买数量",example = "1")
    private Integer buyCount;

    @ApiModelProperty(value = "状态；0：正常，-1：取消，-100：删除",example = "")
    private Integer dataStatus;

    @ApiModelProperty(value = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "最后修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "评论ID 与商品评论表关联字段",example = "123")
    private Long commentId;
}
