package com.xunmeng.youxuan.requestqo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: PageRequestQo
 * Package: com.xunmeng.youxuan.requestqo
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/21 9:47
 * @Version 1.0
 */
@Data
@ApiModel("分页请求参数")
public class PageRequestQo implements Serializable {
    @ApiModelProperty("请求页码")
    private Integer pageIndex;
    @ApiModelProperty("请求页大小")
    private Integer pageSize;

    @ApiModelProperty("排序名称")
    private String sortName;
    @ApiModelProperty(value = "排序顺序,ascending:升序，descending：降序",example = "descending")
    private String sortType;
}
