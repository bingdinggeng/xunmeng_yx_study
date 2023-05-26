package com.xunmeng.youxuan.requestqo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * ClassName: CommonIdQo
 * Package: com.xunmeng.youxuan.requestqo
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/26 15:01
 * @Version 1.0
 */
@Data
@ApiModel("ID请求共通类")
public class CommonIdQo {
    @ApiModelProperty(value = "ID")
    @NotNull(message = "ID缺失")
    @Min(value = 1,message = "ID不规范")
    private Long id;
}
