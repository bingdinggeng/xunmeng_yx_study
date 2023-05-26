package com.xunmeng.youxuan.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ClassName: YxShoppingCart
 * Package: com.xunmeng.domain
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/13 16:35
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YxShippingCart对象", description="")
@TableName("yx_shipping_cart")
public class YxShoppingCart implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自动增长ID")
    @TableId(value = "cart_id", type = IdType.AUTO)
    private Long cartId;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "商品名")
    private String productName;

    @ApiModelProperty(value = "商品图片")
    private String productImage;

    @ApiModelProperty(value = "门店ID，如：1")
    private Long shopId;

    @ApiModelProperty(value = "其他sku，如：大甜")
    private String otherSku;

    @ApiModelProperty(value = "购买数量，如：1")
    private Integer buyCount;

    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    @ApiModelProperty(value = "其他单独金额，之后sku使用")
    private BigDecimal priceOther;

    @ApiModelProperty(value = "状态；0：默认；-1：已提交，-100：删除")
    private Integer dataStatus;

    @ApiModelProperty(value = "来源；1：网页，2：微信申请，3：管理员")
    private Integer dataSource;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;
}
