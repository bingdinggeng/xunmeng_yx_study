package com.xunmeng.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: Response
 * Package: com.xunmeng.base
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/14 9:47
 * @Version 1.0
 */
@Data
@ApiModel("返回数据Model")
public class Response<T>{

    @ApiModelProperty("返回代码，0为成功，其他为错误")
    private int code = 0;

    @ApiModelProperty("返回信息")
    private String msg;

    @ApiModelProperty("返回数据")
    private T data;
}
