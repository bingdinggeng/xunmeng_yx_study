package com.xunmeng.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * ClassName: XmAdmin
 * Package: com.xunmeng.domain
 * Description: 管理员表
 *
 * @Author LTM
 * @Create 2023/5/13 15:11
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "XmAdmin对象",description = "管理员表")
public class XmAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "userid", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "管理账号")
    @TableField(value = "username")
    private String userName;

    @ApiModelProperty(value = "管理密码")
    private String password;

    @ApiModelProperty(value = "工资密码")
    @TableField(value = "wagepass")
    private String wagePass;

    @ApiModelProperty(value = "加密因子")
    private String encrypt;

    @ApiModelProperty(value = "角色ID")
    @TableField(value = "roleid")
    private Integer roleId;

    @ApiModelProperty(value = "姓名")
    @TableField(value = "nickname")
    private String nickName;

    @ApiModelProperty(value = "1=>男  2=>女")
    private Integer sex;

    @ApiModelProperty(value = "身份证号")
    private String identity;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "联系QQ")
    private String qq;

    @ApiModelProperty(value = "推送id")
    @TableField(value = "regid")
    private String regId;

    @ApiModelProperty(value = "手机端token")
    private String token;

    @ApiModelProperty(value = "token失效时间")
    private Integer timeToken;

    @ApiModelProperty(value = "token失效后使用")
    private String refreshToken;

    @ApiModelProperty(value = "失效时间")
    private Integer refreshTime;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "年份入职照片")
    private String photo;

    @ApiModelProperty(value = "身份证正面")
    private String faceCard;

    @ApiModelProperty(value = "身份证反面")
    private String backCard;

    @ApiModelProperty(value = "手持身份证正面")
    private String holdFaceCard;

    @ApiModelProperty(value = "手持身份证反面")
    private String holdBackCard;

    @ApiModelProperty(value = "座右铭")
    private String motto;

    @ApiModelProperty(value = "最后登录时间")
    private Integer lastLoginTime;

    @ApiModelProperty(value = "最后登录IP")
    private Long lastLoginIp;

    @ApiModelProperty(value = "0=>总管理员1=>注册的管理账号   2=>子账户")
    private Integer isAdmin;

    @ApiModelProperty(value = "入职时间")
    @TableField(value = "jointime")
    private Integer joinTime;

    @ApiModelProperty(value = "生日时间")
    @TableField(value = "birtime")
    private String birTime;

    @ApiModelProperty(value = "公司ID")
    @TableField(value = "comid")
    private Integer comId;

    @ApiModelProperty(value = "分组ID")
    @TableField(value = "teamid")
    private String teamId;

    @ApiModelProperty(value = "职位ID")
    @TableField(value = "positionid")
    private Integer positionId;

    @ApiModelProperty(value = "会员状态")
    private Integer status;

    @ApiModelProperty(value = "101=>主管201=>人事部,301=>下单审核,401=>财务")
    private Integer sysRole;

    @ApiModelProperty(value = "1=>组长权限 ")
    private Integer sysLeader;

    @ApiModelProperty(value = "0=>兼职账号 1=>试用期员工  2=>正式员工")
    private Integer partStatus;

    @ApiModelProperty(value = "1=>已核查，核查后隐藏敏感数据")
    private Integer checkStatus;

    private Integer createTime;

    private Integer updateTime;

    @ApiModelProperty(value = "微信openId")
    private String wxOpenId;

    @ApiModelProperty(value = "微信code,用于获取微信的openId，不能重复获取")
    private String wxCode;
}
