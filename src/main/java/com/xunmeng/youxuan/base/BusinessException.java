package com.xunmeng.youxuan.base;

import com.xunmeng.youxuan.enums.ErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ClassName: BusinessException
 * Package: com.xunmeng.youxuan.base
 * Description:  全局异常统一处理类
 *
 * @Author LTM
 * @Create 2023/5/25 17:50
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {
    private ErrorCodeEnum errorCodeEnum;
    private  String msg;
    public BusinessException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getDesc());
        this.errorCodeEnum = errorCodeEnum;
        this.msg = errorCodeEnum.getDesc();
    }

    public BusinessException(String msg) {
        super(msg);
        this.errorCodeEnum = ErrorCodeEnum.FAIL;
        this.msg = msg;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum, String msg) {
        super(errorCodeEnum.getDesc() + ":" + msg);
        this.errorCodeEnum = errorCodeEnum;
        this.msg = msg;
    }

    public BusinessException(Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getDesc(), cause);
        this.errorCodeEnum = errorCodeEnum;
        this.msg = errorCodeEnum.getDesc();
    }


    public BusinessException(Throwable cause, ErrorCodeEnum errorCodeEnum, String msg) {
        super(msg, cause);
        this.errorCodeEnum = errorCodeEnum;
        this.msg = msg;
    }
}
