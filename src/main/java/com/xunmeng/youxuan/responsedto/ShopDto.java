package com.xunmeng.youxuan.responsedto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * ClassName: ShopDto
 * Package: com.xunmeng.youxuan.responsedto
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/24 14:53
 * @Version 1.0
 */
@Data
@ApiModel("商家信息类")
public class ShopDto {
    @ApiModelProperty(value = "门店ID",example = "123")
    private Long shopId;

    @ApiModelProperty(value = "门店名称",example = "寻梦001")
    private String shopName;

    @ApiModelProperty(value = "头像",example = "头像照片地址")
    private String avatar;

    @ApiModelProperty(value = "门头照",example = "https:\\www.baidu.com")
    private String doorPhoto;

    @ApiModelProperty(value = "联系电话",example = "18655952385")
    private String shopPhone;

    @ApiModelProperty(value = "店铺地址",example = "安徽省宣城市宣州区中山西路201号")
    private String address;

    @ApiModelProperty(value = "真实名称",example = "jp")
    private String realName;

    @ApiModelProperty(value = "公告",example = "本店...")
    private String notice;

    @ApiModelProperty(value = "商家描述",example = "商家描述")
    private String shopDescribe;

    @ApiModelProperty(value = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "最后修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备注",example = "商家备注")
    private String remark;

    @ApiModelProperty(value = "状态；0：申请，1,审核通过 10：营业中，-1：休息，-10：处罚中，-100：关店")
    private Integer dataStatus;

    @ApiModelProperty(value = "月销，近30天的单子数量",example = "10")
    private Integer monthSells;
}
