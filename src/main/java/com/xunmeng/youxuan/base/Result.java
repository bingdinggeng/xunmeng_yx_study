package com.xunmeng.youxuan.base;

import com.xunmeng.youxuan.enums.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: Result
 * Package: com.xunmeng.youxuan.base
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/29 8:38
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("返回数据类型Model 2.0")
public class Result<T> implements Serializable {
    @ApiModelProperty(value = "返回代码，0为成功，其他为错误", position = 1)
    private Integer code;
    @ApiModelProperty(value = "返回信息", position = 2)
    private String msg;
    @ApiModelProperty(value = "返回数据", position = 3)
    private T data;

    public static <T> Result<T> newResponse(Integer code, String msg, T data){
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> newFailedResponse(ErrorCodeEnum errorCodeEnum) {
        return newResponse(errorCodeEnum.getCode(), errorCodeEnum.getDesc(), null);
    }

    public static <T> Result<T> newSuccessResponse(T data) {
        return newResponse(0, "success", data);
    }

    public static <T> Result<T> newSuccessResponse(ErrorCodeEnum errorCodeEnum) {
        return newResponse(errorCodeEnum.getCode(), errorCodeEnum.getDesc(), null);
    }

    public static <T> Result<T> newFailedResponse(ErrorCodeEnum errorCodeEnum, String msg) {
        return newResponse(errorCodeEnum.getCode(), msg, null);
    }
}
