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
 * ClassName: YxYlyPrinter
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
@ApiModel(value="YxYlyPrinter对象", description="优选易联云打印配置表")
public class YxYlyPrinter implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "打印机名称")
    private String name;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "打印机终端号")
    private String machineCode;

    @ApiModelProperty(value = "打印机特殊密钥")
    private String msign;

    @ApiModelProperty(value = "打印机token")
    private String accessToken;

    @ApiModelProperty(value = "打印机refresh_token")
    private String refreshToken;

    @ApiModelProperty(value = "令牌有效时间，单位s")
    private Integer expiresIn;

    @ApiModelProperty(value = "令牌申请时间，datetime")
    private Long applyTime;

    @ApiModelProperty(value = "令牌过期时间")
    private Long overTime;

    @ApiModelProperty(value = "状态， 1 => 正常 -1 => 删除")
    private Integer status;

    @ApiModelProperty(value = "是否使用， 0 => 否 1 => 是")
    private Integer isUsed;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
