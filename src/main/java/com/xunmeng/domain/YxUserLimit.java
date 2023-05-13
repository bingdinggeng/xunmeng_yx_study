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
 * ClassName: YxUserLimit
 * Package: com.xunmeng.domain
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/13 16:41
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YxUserLimit对象", description="")
public class YxUserLimit implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自动增长ID")
    @TableId(value = "limit_id", type = IdType.AUTO)
    private Long limitId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "月：如2020-01")
    private String monthStr;

    @ApiModelProperty(value = "对应month_str的消费金额")
    private BigDecimal monthMoney;

    @ApiModelProperty(value = "每月最大金额")
    private BigDecimal monthMax;

    @ApiModelProperty(value = "日：如2020-01-01")
    private String dayStr;

    @ApiModelProperty(value = "对应day_str的消费金额")
    private BigDecimal dayMoney;

    @ApiModelProperty(value = "每天最大金额")
    private BigDecimal dayMax;

    @ApiModelProperty(value = "状态；0：正常，-1：取消，-100：删除")
    private Integer dataStatus;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "操作用户id")
    private Long operatorId;
}
