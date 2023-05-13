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
 * ClassName: YxCategory
 * Package: com.xunmeng.domain
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/13 16:27
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YxCategory对象", description="")
public class YxCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自动增长ID")
    @TableId(value = "category_id", type = IdType.AUTO)
    private Long categoryId;

    @ApiModelProperty(value = "分类名")
    private String categoryName;

    @ApiModelProperty(value = "门店ID")
    private Long shopId;

    @ApiModelProperty(value = "分类描述，如：香脆可口，外焦里嫩")
    private String description;

    @ApiModelProperty(value = "主图，图片path")
    private String imagePath;

    @ApiModelProperty(value = "显示顺序，正序")
    private Integer sortNum;

    @ApiModelProperty(value = "状态；0：正常；-100：删除")
    private Integer dataStatus;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "操作用户id")
    private Long operatorId;

    @ApiModelProperty(value = "最后操作的用户id")
    private Long lastOperatorId;

    @ApiModelProperty(value = "备注")
    private String remark;
}
