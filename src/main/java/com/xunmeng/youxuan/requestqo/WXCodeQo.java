package com.xunmeng.youxuan.requestqo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ClassName: WXCodeQo
 * Package: com.xunmeng.youxuan.requestqo
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/27 14:48
 * @Version 1.0
 */
@Data
@ApiModel("微信Code请求Model")
public class WXCodeQo implements Serializable {
    @ApiModelProperty(value = "微信Code",example = "1234a")
    @NotNull(message = "code不能为空")
    private String code;
}