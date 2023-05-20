package com.xunmeng.youxuan.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ClassName: YxShopPunish
 * Package: com.xunmeng.domain
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/13 16:38
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YxShopPunish对象", description="")
public class YxShopPunish implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "超市的id")
    private Integer shopId;

    @ApiModelProperty(value = "处罚的金额")
    private BigDecimal price;

    @ApiModelProperty(value = "处理的理由")
    private String reason;

    @ApiModelProperty(value = "创建的时间")
    private Integer createTime;

    @ApiModelProperty(value = "图片的id")
    private String imageId;

    @ApiModelProperty(value = "状态")
    private Boolean status;

    @ApiModelProperty(value = "用户名")
    @TableField(value = "userid")
    private Integer userId;
}
