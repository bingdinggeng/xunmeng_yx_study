package com.xunmeng.youxuan.responsedto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: WXInfoDto
 * Package: com.xunmeng.youxuan.responsedto
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/20 11:31
 * @Version 1.0
 */
@Data
@ApiModel("微信登陆返回信息")
public class WXInfoDto{
    @ApiModelProperty(value = "openId",example = "openId")
    private String openId;
    @ApiModelProperty(value = "sessionKey",example = "sessionKey")
    private String sessionKey;
    @ApiModelProperty(value = "增加后的UserId，全局唯一",example = "1")
    private Integer userId;

    @ApiModelProperty(value = "系统角色；1：寻梦公司用户，2：商家，100：平台管理员")
    private Integer roleType;

    @ApiModelProperty(value = "登录人名称",example = "张三")
    private String userName;

    @ApiModelProperty(value = "公司ID",example = "123")
    private Integer comId;

    @ApiModelProperty(value = "teamID",example = "123")
    private Integer teamId;
}
