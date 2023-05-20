package com.xunmeng.youxuan.enums;

import java.math.BigDecimal;

/**
 * ClassName: ConstantEnum
 * Package: com.xunmeng.enums
 * Description:常量信息表
 *
 * @Author LTM
 * @Create 2023/5/14 14:09
 * @Version 1.0
 */
public class ConstantEnum {
    public static final int COM_XUNMENG=100001;
    /**
     * 签到类型
     */
    public static final int SIGN_TYPE = 1;
    /**
     * 签退类型
     */
    public static final int SIGN_OUT_TYPE = 2;
    /**
     * 正序关键字
     */
    public static final String SORT_ASC = "ascending";
    /**
     * 降序关键字
     */
    public static final String SORT_DESC = "descending";
    /**
     * 编码 utf-8
     */
    public static final String ENCODING_UTF8 = "UTF-8";
    /**
     * 平台管理员类型值
     */
    public static final int PLATFORM_TYPE = 1;
    /**
     * 审核未通过
     */
    public static final int CHECK_NO_PASS = -2;
    /**
     * 等待审核
     */
    public static final int CHECK_WAIT = 0;
    /**
     * 审核通过
     */
    public static final int CHECK_PASS = 1;
    /**
     * 删除
     */
    public static final int DELETE = -100;
    /**
     * 删除
     */
    public static final int DELETE_XM = 0;
    /**
     * 正常数据
     */
    public static final int NORMAL = 0;
    /**
     * 正常数据
     */
    public static final int NORMAL_XM = 1;
    /**
     * 寻梦员工
     */
    public static final int USER_XM = 1;

    /**
     * 打印机删除
     */
    public static final int PRINTER_DELETE_XM = -1;
    /**
     * 外卖老板
     */
    public static final int USER_SHOP = 2;
    /**
     * 客服
     */
    public static final int USER_SERVICE = 50;
    /**
     * 管理员
     */
    public static final int USER_ADMIN = 100;

    /**
     * 商户状态：申请中
     */
    public static final Integer SHOP_STATUS_APPLYING = 0;

    /**
     * 商户状态：审核通过
     */
    public static final Integer SHOP_STATUS_APPLIED = 1;

    /**
     * 商户状态：开店中
     */
    public static final Integer SHOP_STATUS_PASSED = 10;

    /**
     * 商户状态：休息中
     */
    public static final Integer SHOP_STATUS_REST = -1;

    /**
     * 商户状态：处罚中
     */
    public static final Integer SHOP_STATUS_PUNISH = -10;

    /**
     * 商户状态：关店
     */
    public static final Integer SHOP_STATUS_CLOSE = -100;

    /**
     * 商品状态：上架
     */
    public static final Integer PRODUCT_ON_SHELF = 1;

    /**
     * 商品状态：下架
     */
    public static final Integer PRODUCT_OFF_SHELF = 0;

    /**
     * 商品状态：已删除
     */
    public static final Integer PRODUCT_DELETE = -100;

    /**
     * 购物车状态：已提交
     */
    public static final Integer SHOPPING_STATUS_COMMIT = -1;

    /**
     * 订单状态：待接单和待送达
     */
    public static final Integer ORDER_STATUS_DOING = 99;

    /**
     * 订单状态：全部
     */
    public static final Integer ORDER_STATUS_ALL = 100;

    /**
     * 订单状态：待付款
     */
    public static final Integer ORDER_STATUS_WAIT = 0;

    /**
     * 订单状态：已付款
     */
    public static final Integer ORDER_STATUS_PAID = 1;

    /**
     * 订单状态：已接单
     */
    public static final Integer ORDER_STATUS_ACCEPTED = 2;

    /**
     * 订单状态：已发货
     */
    public static final Integer ORDER_STATUS_DELIVERED = 3;

    /**
     * 订单状态：已送达
     */
    public static final Integer ORDER_STATUS_ARRIVED= 4;

    /**
     * 订单状态：已收货
     */
    public static final Integer ORDER_STATUS_DONE= 5;

    /**
     * 订单状态：已取消
     */
    public static final Integer ORDER_STATUS_CANCEL = -1;

    /**
     * 订单状态：已取消
     */
    public static final Integer PASS_ENCRYPT_LENGTH = 6;

    /**
     * 正式员工：每人每日默认最大金额
     */
    public static final BigDecimal MAX_USER_DAY = new BigDecimal(100);

    /**
     * 试用期：每人每日默认最大金额
     */
    public static final BigDecimal MAX_TEST_USER_DAY = new BigDecimal(30);

    /**
     * 每人每月默认最大金额
     */
    public static final BigDecimal MAX_USER_MONTH = new BigDecimal(1500);

    /**
     * 可发起售后时间：一天
     */
    public static final int CAN_AFTER_SALE_TIME = 1;

    /**
     * 售后状态:未发起售后
     */
    public static final int AFTER_STATUS_STAND = 0;

    /**
     * 售后状态:第一次售后
     */
    public static final int AFTER_STATUS_FIRST = 1;

    /**
     * 售后状态:第一次商家同意
     */
    public static final int AFTER_STATUS_FIRST_AGREE = 2;

    /**
     * 售后状态:第一次商家拒绝
     */
    public static final int AFTER_STATUS_FIRST_REFUSE = 3;

    /**
     * 售后状态:第二次售后
     */
    public static final int AFTER_STATUS_SECOND= 5;

    /**
     * 售后状态:第二次商家同意
     */
    public static final int AFTER_STATUS_SECOND_AGREE = 6;

    /**
     * 售后状态:第二次商家拒绝
     */
    public static final int AFTER_STATUS_SECOND_REFUSE = 7;

    /**
     * 售后状态:客服介入
     */
    public static final int AFTER_STATUS_ADMIN = 10;

    /**
     * 售后状态:同意
     */
    public static final int AFTER_STATUS_AGREE = 11;

    /**
     * 售后状态:拒绝
     */
    public static final int AFTER_STATUS_REFUSE = 12;

    /**
     * 售后状态:取消
     */
    public static final int AFTER_STATUS_CANCEL = -1;

    /**
     * 售后状态:删除
     */
    public static final int AFTER_STATUS_DELETED = -100;

    /**
     * 王主管用户ID
     */
    public static final int USER_ID_MASTER_WANG = 17472;

    /**
     * 最大图片大小  5M
     */
    public static final int MAX_IMAGE_SIZE = 5242880;

    /**
     * 文件上传类型
     */
    public static final String IMAGE_TYPE = "image";

    /**
     * 文件上传类型
     */
    public static final String FILE_NAME_TAG = ".";

    /**
     * 文件上传类型
     */
    public static final String FILE_UPLOAD_TYPE = "S3SignerType";

    /**
     * 文件上传临时保存路径，需定时删除
     */
    public static final String TEMP_PATH = "temp_can_delete/";

    /**
     * 网络请求状态
     */
    public static final int WEB_STATUS = 200;

    /**
     * 网络请求超时时间
     */
    public static final int TIME_OUT = 2000;

    /**
     * 商品类别：售卖中
     */
    public static final int ON_SALE = 1;

    /**
     * 商品类别：已下架
     */
    public static final int UN_SALE = 2;

    /**
     * 商品类别：折扣
     */
    public static final int DISCOUNT = 3;

    /**
     * 商品类别：优选
     */
    public static final int YX = 4;

}
