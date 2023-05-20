package com.xunmeng.youxuan.enums;

/**
 * ClassName: CacheKeyEnum
 * Package: com.xunmeng.enums
 * Description:缓存信息Key
 *
 * @Author LTM
 * @Create 2023/5/14 16:25
 * @Version 1.0
 */
public class CacheKeyEnum {
    /**
     * user信息缓存key头  微信
     */
    public static final String USER_INFO = "youxuan_user_";
    /**
     * user信息缓存key头  微信
     */
    public static final String SHOP_INFO = "youxuan_shop_";
    /**
     * user信息缓存key头  微信
     */
    public static final String USER_TOKEN_INFO = "youxuan_token_user_";
    /**
     * 优选管理员列表
     */
    public static final String USER_ADMIN_LIST = "youxuan_admin_list";
    /**
     * 优选客服列表
     */
    public static final String USER_SERVICE_LIST = "youxuan_service_list";
    /**
     * 分类信息缓存key
     */
    public static final String CATEGORY_LIST = "youxuan_category_list";
    /**
     * 分类信息缓存key
     */
    public static final String SWEEP_CODE_YOUXUAN = "youxuan_sweep_code_";

    /**
     * 订单序号缓存Key头
     */
    public static final String ORDER_NO_SHOP = "yx_order_No_";

    /**
     * 门店分类列表 Category
     */
    public static final String SHOP_CATEGORY = "yx_category_list";

    /**
     * 门店分类商品列表 缓存头
     */
    public static final String SHOP_CATEGORY_PRODUCT = "yx_shop_category_product_";

    /**
     * 门店分类商品数量缓存头
     */
    public static final String SHOP_PRODUCT_LIST_SUM = "yx_product_list_sum_";
}
