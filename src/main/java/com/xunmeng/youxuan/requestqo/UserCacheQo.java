package com.xunmeng.youxuan.requestqo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * ClassName: UserCacheQo
 * Package: com.xunmeng.requestqo
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/14 16:26
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class UserCacheQo implements Serializable {
    private Integer userId;

    @ApiModelProperty(value = "系统角色；1：寻梦公司用户，2：商家，50：客服，100：管理员")
    private Integer roleType;
}
