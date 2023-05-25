package com.xunmeng.youxuan.requestqo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ClassName: ProductSearchPageQo
 * Package: com.xunmeng.youxuan.requestqo
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/25 10:01
 * @Version 1.0
 */
@Data
@ApiModel("商品搜索并分页查询请求")
public class ProductSearchPageQo extends PageRequestQo implements Serializable {
    @ApiModelProperty(value = "门店ID",example = "1")
    @NotNull(message = "门店ID不能为空")
    @Min(value = 0,message = "门店ID信息错误")
    private Long shopId;

    @ApiModelProperty(value = "类别ID",example = "1")
    private Long categoryId;

    @ApiModelProperty(value = "产品名称",example = "煎饺")
    private String productName;

    @ApiModelProperty(value = "不传默认全部，售卖中->1 已下架->2 折扣->3 优选->4")
    private Integer listFlag;
}
