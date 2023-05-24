package com.xunmeng.youxuan.requestqo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: ShopListQo
 * Package: com.xunmeng.youxuan.requestqo
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/24 14:54
 * @Version 1.0
 */
@Data
@ApiModel("商家列表请求类")
public class ShopListQo extends PageRequestQo implements Serializable {
    @ApiModelProperty(value = "商家名称",example = "商家名称")
    private String shopName;

    @ApiModelProperty(value = "商家类别ID",example = "商家类别ID")
    private Long shopCategoryId;
}
