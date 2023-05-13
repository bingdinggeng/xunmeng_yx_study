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
 * ClassName: YxProductInfo
 * Package: com.xunmeng.domain
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/13 16:34
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YxProductInfo对象", description="")
public class YxProductInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自动增长ID")
    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;

    @ApiModelProperty(value = "商品名")
    private String productName;

    @ApiModelProperty(value = "分类id")
    private Long categoryId;

    @ApiModelProperty(value = "商品价格，如：18.1")
    private BigDecimal price;

    @ApiModelProperty(value = "商品描述，如：香脆可口，外焦里嫩")
    private String description;

    @ApiModelProperty(value = "主图，图片path")
    private String imagePath;

    @ApiModelProperty(value = "其他属性，,分隔")
    private String otherSku;

    @ApiModelProperty(value = "总的评价个数")
    private Integer totalComment;

    @ApiModelProperty(value = "好的评价分数和")
    private Integer goodComment;

    @ApiModelProperty(value = "库存量，如：9000")
    private Integer stock;

    @ApiModelProperty(value = "销售量")
    private Long sellCount;

    @ApiModelProperty(value = "显示顺序，正序")
    private Integer sortNum;

    @ApiModelProperty(value = "门店ID")
    private Long shopId;

    @ApiModelProperty(value = "状态；0：下架；1：上架，默认；-100：删除")
    private Integer dataStatus;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "操作用户id")
    private Long operatorId;

    @ApiModelProperty(value = "最后操作的用户id")
    private Long lastOperatorId;

    @ApiModelProperty(value = "订单来源；1：网页，2：微信申请")
    private Integer dataSource;

    @ApiModelProperty(value = "备注")
    private String remark;
}
