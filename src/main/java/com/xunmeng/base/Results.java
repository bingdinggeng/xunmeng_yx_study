package com.xunmeng.base;



import com.xunmeng.enums.ErrorCodeEnum;

/**
 * ClassName: Results
 * Package: com.xunmeng.requestqo
 * Description:返回结果类
 *
 * @Author LTM
 * @Create 2023/5/14 13:34
 * @Version 1.0
 */
public class Results {

    public static <T> Response<T> newSuccessResponse(T data){
        return newResponse(data,0,"success");
    }

    public static <T> Response<T> newFailedResponse(Integer code, String message) {
        return newResponse(null, code, message);
    }

    public static <T> Response<T> newFailedResponse(ErrorCodeEnum errorCodeEnum) {
        return newResponse(null, errorCodeEnum.getCode(), errorCodeEnum.getDesc());
    }

    public static <T> Response<T> newFailedResponse(ErrorCodeEnum errorCodeEnum, String message) {
        return newResponse(null, errorCodeEnum.getCode(), message);
    }

    public static <T> Response<T> newFailedResponse(T data,ErrorCodeEnum errorCodeEnum, String message) {
        return newResponse(data, errorCodeEnum.getCode(), message);
    }

    public static <T>Response<T> newResponse(T data, Integer code,String message){
        Response response = new Response();
        response.setData(data);
        response.setCode(code);
        response.setMsg(message);
        return response;
    }
}
