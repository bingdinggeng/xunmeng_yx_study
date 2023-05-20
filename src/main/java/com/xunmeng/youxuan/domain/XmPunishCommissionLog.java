package com.xunmeng.youxuan.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * ClassName: XmPunishCommissionLog
 * Package: com.xunmeng.domain
 * Description:返现 撤销编辑记录表
 *
 * @Author LTM
 * @Create 2023/5/13 16:21
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XmPunishCommissionLog对象", description="返现 撤销编辑记录表")
public class XmPunishCommissionLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "操作人id")
    private Integer userid;

    @ApiModelProperty(value = "当日时间")
    private Integer dayTime;

    @ApiModelProperty(value = "操作内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    private Integer createTime;

    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;

    @ApiModelProperty(value = "绑定的id")
    private String punishCommissionId;

    @ApiModelProperty(value = "创建用户")
    private Integer addUserid;
}
