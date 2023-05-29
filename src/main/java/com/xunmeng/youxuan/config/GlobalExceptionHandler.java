package com.xunmeng.youxuan.config;

import com.xunmeng.youxuan.base.BusinessException;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.enums.ErrorCodeEnum;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName: GlobalExceptionHandler
 * Package: com.xunmeng.youxuan.config
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/29 13:48
 * @Version 1.0
 */
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    /**
     * 自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result<ErrorCodeEnum> businessException(BusinessException e) {
        return Result.newFailedResponse(e.getErrorCodeEnum());
    }
    /**
     * 自定义异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<T> businessException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return Result.newFailedResponse(ErrorCodeEnum.PARAM_ERROR,
                e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<T> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return Result.newFailedResponse(ErrorCodeEnum.SYSTEM_ERROR, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<T> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.newFailedResponse(ErrorCodeEnum.SYSTEM_ERROR, e.getMessage());
    }
}
