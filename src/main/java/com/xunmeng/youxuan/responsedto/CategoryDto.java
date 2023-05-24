package com.xunmeng.youxuan.responsedto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * ClassName: CategoryDto
 * Package: com.xunmeng.youxuan.responsedto
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/24 17:40
 * @Version 1.0
 */
@Data
@ApiModel("商品分类信息类")
public class CategoryDto implements Serializable {
    @ApiModelProperty(value = "分类ID",example = "123")
    private Long categoryId;

    @ApiModelProperty(value = "分类名",example = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "门店ID",example = "233")
    private Long shopId;

    @ApiModelProperty(value = "分类描述，如：香脆可口，外焦里嫩",example = "香脆可口，外焦里嫩")
    private String description;

    @ApiModelProperty(value = "主图，图片path",example = "图片地址")
    private String imagePath;

    @ApiModelProperty(value = "商品数量")
    private Integer productCount;

    @ApiModelProperty(value = "显示顺序，正序",example = "12")
    private Integer sortNum;

    @ApiModelProperty(value = "状态；0：正常；-100：删除",example = "0")
    private Integer dataStatus;

    @ApiModelProperty(value = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "最后修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备注",example = "备注信息")
    private String remark;
}
