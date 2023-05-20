package com.xunmeng.youxuan.enums;

/**
 * ClassName: ErrorCodeEnum
 * Package: com.xunmeng.enums
 * Description:错误信息代码
 *
 * @Author LTM
 * @Create 2023/5/14 13:50
 * @Version 1.0
 */
public enum ErrorCodeEnum {
    /*
    * 成功
    * */
    SUCESSS(0,"操作失败"),
    FAIL(1, "失败"),
    PARAM_ERROR(2, "参数错误"),

    PERMISSION_DENIED(3, "权限不足"),
    INFO_NOT_EXIST(4, "信息不存在"),
    INFO_EXIST(5, "信息已存在"),
    EXPORT_INFO_NOT_EXIST(6, "未有导出信息"),
    INFO_NOT_ONLY(7, "信息不唯一"),
    HANDLER_NOT_FOUND_ERROR(10, ""),
    HANDLER_CONFIG_ERROR(11, ""),
    DONT_DELETE(13, "信息已被使用，不能删除"),
    PASSWORD_ERROR(14, "密码错误"),
    CHECK_EXIST(15, "已审核"),
    WX_PERMISSION_DENIED(16,"请先进行微信授权！"),
    SESSION_TIMEOUT(50, "登录超时，请重新登录"),

    TOKEN_NOT_EXIST(51, "TOKEN不存在,请联系管理员"),
    FILE_EMPTY(52, "文件为空，请确认后重试"),
    IMAGE_MAX_SIZE(54, "图片超出最大限制，"+(ConstantEnum.MAX_IMAGE_SIZE/10224)+"M"),
    FILE_TYPE_ERROR(55, "文件类型错误"),
    IMAGE_TYPE_ERROR(56, "请上传图片类型"),
    IMAGE_MISS(57, "请上传图片"),

    SHOP_INFO_SHOPNAME_EXIST(101, "门店名称已经存在"),
    WX_IMAGE_CODE_ERROR(102, "图形验证码错误"),
    IMAGE_UPLOAD_ERROR(103, "图片不能为空"),
    IMAGE_UPLOAD_FAIL(104, "图片上传失败"),
    WX_SWEEP_CODE_EXPIRE(105, "二维码已过期"),


    SHOP_STATUS_ERROR(301, "商户状态不正确"),

    CATEGORY_NAME_NOT_EXIST(401,"分类名称不存在"),
    CATEGORY_PRODUCT_EXIST(402,"分类下还有商品未删除，请先删除商品！"),

    SHOPPING_CART_NOT_EXIST(601,"购物车信息不存在"),
    SHOPPING_CART_LIST_ERROR(602,"购物车信息错误"),
    SHOPPING_DELETE_ERROR(603,"购物车商品删除失败"),

    ORDER_INFO_ERROR(701,"订单信息错误！"),
    ORDER_OVER_DAY_LIMIT(702,"您已超过日限额！"),
    ORDER_OVER_MONTH_LIMIT(703,"您已超过月限额！"),
    ORDER_CAN_NOT_CANCEL(704,"订单不可取消！"),
    ORDER_STATUS_ERROR(705,"订单状态错误！"),
    ORDER_ITEMS_ERROR(706,"订单商品错误！"),
    ORDER_STOCK_OVER(707,"下单失败，超过商品库存！"),
    ORDER_ITEM_PRICE_ERROR(708,"订单中商品价格不正确，请重新选购！"),
    ORDER_TIME_ERROR(709,"订单指定时间不正确！"),
    ORDER_PAY_TIME_ERROR(710,"订单付款时间不正确！"),
    ORDER_SHOP_CLOSE(711,"店铺已经打烊！"),
    ORDER_PRODUCT_SHELF_OUT(712,"已下架！"),


    AFTER_SALE_TIME_ERROR(801,"订单售后时间错误"),
    AFTER_SALE_STATUS_ERROR(802,"订单售后状态错误"),
    AFTER_SALE_NOT_EXIST(803,"订单售后不存在"),

    REPEAT_COMMIT_ERROR(800,"请勿重复提交！"),

    WX_REFUND_SUCCESS(80,"SUCCESS"),
    WX_REFUND_PROCESSING(81,"PROCESSING"),

    SYSTEM_ERROR(999, "系统忙，请稍后重试或者联系技术部！")
    ;

    private int code;
    private String desc;

    private ErrorCodeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
