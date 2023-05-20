package com.xunmeng.youxuan.domain;

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
 * ClassName: YxAdminInfo
 * Package: com.xunmeng.domain
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/13 16:26
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YxAdminInfo对象", description="")
public class YxAdminInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自动增长ID")
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Long adminId;

    @ApiModelProperty(value = "xm_admin userId")
    private Integer userId;

    @ApiModelProperty(value = "xm_admin nickname")
    private String userName;

    @ApiModelProperty(value = "状态；0：正常，-100：删除")
    private Integer dataStatus;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "最后操作的寻梦用户id")
    private Long lastOperatorId;

    @ApiModelProperty(value = "备注")
    private String remark;
}
