package com.xunmeng.youxuan.base;

import com.xunmeng.youxuan.enums.ErrorCodeEnum;

/**
 * ClassName: BusinessException
 * Package: com.xunmeng.youxuan.base
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/25 17:50
 * @Version 1.0
 */
public class BusinessException extends RuntimeException {
    private ErrorCodeEnum errorCodeEnum;
    private  String msg;
    public BusinessException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getDesc());
        this.errorCodeEnum = errorCodeEnum;
        this.msg = errorCodeEnum.getDesc();
    }
}
