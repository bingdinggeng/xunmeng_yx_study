package com.xunmeng.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * ClassName: YxProductComment
 * Package: com.xunmeng.domain
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/13 16:33
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YxProductComment对象", description="")
public class YxProductComment implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自动增长ID")
    @TableId(value = "product_comment_id", type = IdType.AUTO)
    private Long productCommentId;

    @ApiModelProperty(value = "评论ID，即上面的product_comment_id")
    private Long parentCommentId;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "订单详情ID")
    private Long orderItemId;

    @ApiModelProperty(value = "评论内容")
    private String commentContent;

    @ApiModelProperty(value = "评论第1张图片")
    private String imageUrl1;

    @ApiModelProperty(value = "评论第2张图片")
    private String imageUrl2;

    @ApiModelProperty(value = "评论第3张图片")
    private String imageUrl3;

    @ApiModelProperty(value = "评论第4张图片")
    private String imageUrl4;

    @ApiModelProperty(value = "评论第5张图片")
    private String imageUrl5;

    @ApiModelProperty(value = "商品星数")
    private Integer startCount;

    @ApiModelProperty(value = "阅读次数")
    private Integer readCount;

    @ApiModelProperty(value = "状态；0：正常；-1:后台管理删除；-100：删除")
    private Integer dataStatus;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "操作用户id")
    private Long operatorId;

    @ApiModelProperty(value = "最后操作的用户id")
    private Long lastOperatorId;

    @ApiModelProperty(value = "匿名anonymous，1：匿名")
    private Integer messageType;
}
