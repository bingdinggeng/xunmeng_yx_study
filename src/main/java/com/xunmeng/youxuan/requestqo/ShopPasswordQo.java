package com.xunmeng.youxuan.requestqo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * ClassName: ShopPasswordQo
 * Package: com.xunmeng.youxuan.requestqo
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/20 16:05
 * @Version 1.0
 */
@Data
@ApiModel("商户修改登录密码请求类")
public class ShopPasswordQo {
    @ApiModelProperty(value = "登录昵称",example = "mengxin")
    @NotNull(message = "登录名缺失")
    private String nickName;

    @ApiModelProperty(value = "旧密码",example = "张三")
    @NotNull(message = "旧登录密码缺失")
    private String pass;

    @ApiModelProperty(value = "新密码",example = "新密码")
    @NotNull(message = "新密码未填")
    private String passNew;
}
