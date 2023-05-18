package com.xunmeng.requestqo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: LoginQo
 * Package: com.xunmeng.requestqo
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/14 10:06
 * @Version 1.0
 */
@Data
@ApiModel("登录请求类")
public class LoginQo implements Serializable {
    @ApiModelProperty(value = "登录昵称",example = "bingdinggeng")
    private String nickName;

    @ApiModelProperty(value = "密码",example = "abc123")
    private String pass;

    @ApiModelProperty(value = "极光推送设备标识")
    private String registrationId;
}
