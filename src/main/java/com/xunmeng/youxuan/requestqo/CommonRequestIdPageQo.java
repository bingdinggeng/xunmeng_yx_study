package com.xunmeng.youxuan.requestqo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * ClassName: CommonRequestIdPageQo
 * Package: com.xunmeng.youxuan.requestqo
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/24 17:42
 * @Version 1.0
 */
@Data
@ApiModel("公用根据ID分页请求参数")
public class CommonRequestIdPageQo extends PageRequestQo{
    @ApiModelProperty(value = "id",example = "1")
    @Min(value = 1, message = "id错误")
    public Long id;
}
