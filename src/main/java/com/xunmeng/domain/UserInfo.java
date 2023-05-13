package com.xunmeng.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: UserInfo
 * Package: com.xunmeng.domain
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/13 14:45
 * @Version 1.0
 */

@Data
@ApiModel(value = "UserInfo对象",description = "")
public class UserInfo implements Serializable {
    @ApiModelProperty(value = "自动增长ID")
    @TableId(value = "user_id",type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty(value = "微信openId")
    private String openId;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "用户真实名称")
    private String realName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "手机号")
    private String mobileNumber;

    @ApiModelProperty(value = "状态； 1：寻梦员工，2：商户，50：客服，100：超级管理员")
    private Integer roleType;

    @ApiModelProperty(value = "状态； 1：网页，2：微信")
    private Integer dataSource;

    @ApiModelProperty(value = "入职时间")
    private Integer joinTime;

    @ApiModelProperty(value = "状态，用户端暂时不要用，只给商户端")
    private Integer dataStatus;
}
