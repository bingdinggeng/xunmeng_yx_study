package com.xunmeng.youxuan.base;

import com.xunmeng.youxuan.enums.ErrorCodeEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName: GlobalExceptionHandler
 * Package: com.xunmeng.youxuan.base
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/26 15:28
 * @Version 1.0
 */
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    /**
     * 自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public Response businessException(BusinessException e) {
        Response response = new Response();
        response.setCode(e.getErrorCodeEnum().getCode());
        response.setMsg(e.getMsg());
        return response;
    }
    /**
     * 自定义异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response businessException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        Response response = new Response();
        response.setCode(ErrorCodeEnum.PARAM_ERROR.getCode());
        response.setMsg(e.getBindingResult().getFieldError().getDefaultMessage());
        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Response handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        Response response = new Response();
        response.setCode(ErrorCodeEnum.SYSTEM_ERROR.getCode());
        response.setMsg(e.getMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        log.error(e.getMessage(), e);
        Response response = new Response();
        response.setCode(ErrorCodeEnum.SYSTEM_ERROR.getCode());
        response.setMsg(e.getMessage());
        return response;
    }
}
