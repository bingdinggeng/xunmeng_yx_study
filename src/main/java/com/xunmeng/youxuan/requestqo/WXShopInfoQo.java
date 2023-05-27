package com.xunmeng.youxuan.requestqo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: WXShopInfoQo
 * Package: com.xunmeng.youxuan.requestqo
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/27 14:48
 * @Version 1.0
 */
@Data
@ApiModel("商家注册申请请求Model")
public class WXShopInfoQo extends WXCodeQo implements Serializable {
    @ApiModelProperty(value = "门店名称",example = "奶茶店")
    private String shopName;

    @ApiModelProperty(value = "真实姓名",example = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "门头照",example = "url")
    private String doorPhoto;

    @ApiModelProperty(value = "身份证ID",example = "身份证号")
    private String cardId;

    @ApiModelProperty(value = "身份证正面照",example = "身份证正面照")
    private String cardPhoto;

    @ApiModelProperty(value = "身份证反面照",example = "身份证反面照")
    private String cardPhoto2;

    @ApiModelProperty(value = "联系电话",example = "联系电话")
    private String shopPhone;
}
