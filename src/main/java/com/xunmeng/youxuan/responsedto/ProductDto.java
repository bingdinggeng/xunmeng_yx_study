package com.xunmeng.youxuan.responsedto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ClassName: ProductDto
 * Package: com.xunmeng.youxuan.responsedto
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/25 9:55
 * @Version 1.0
 */
@Data
@ApiModel("商品信息类")
public class ProductDto {
    @ApiModelProperty(value = "产品ID",example = "1")
    private Long productId;

    @ApiModelProperty(value = "商品名",example = "蛋糕")
    private String productName;

    @ApiModelProperty(value = "分类id",example = "")
    private Long categoryId;

    @ApiModelProperty(value = "商品价格，如：18.1",example = "10.1")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;

    @ApiModelProperty(value = "商品描述，如：香脆可口，外焦里嫩",example = "香脆可口，外焦里嫩")
    private String description;

    @ApiModelProperty(value = "主图，图片path",example = "http://1.jpg")
    private String imagePath;

    @ApiModelProperty(value = "其他属性，,分隔",example = "[\"中甜\",\"大甜\"]")
    private String otherSku;

    @ApiModelProperty(value = "总的评价个数",example = "10")
    private Integer totalComment;

    @ApiModelProperty(value = "好的评价分数和",example = "10")
    private Integer goodComment;

    @ApiModelProperty(value = "库存量，如：9000",example = "99")
    private Integer stock;

    @ApiModelProperty(value = "销售量",example = "12")
    private Long sellCount;

    @ApiModelProperty(value = "星级",example = "400.23")
    private BigDecimal startCount;

    @ApiModelProperty(value = "显示顺序，正序",example = "1")
    private Integer sortNum;

    @ApiModelProperty(value = "状态；0：下架；1：上架，默认；-1：删除",example = "1")
    private Integer dataStatus;

    @ApiModelProperty(value = "添加时间", example = "2020-02-01 00:01:01")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "最后修改时间", example = "2020-02-01 00:01:01")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    //    @ApiModelProperty(value = "促销价格")
//    private BigDecimal activityPrice;
//
//    @ApiModelProperty(value = "促销开始时间")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private LocalDateTime activityStartTime;
//
//    @ApiModelProperty(value = "促销结束时间")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private LocalDateTime activityEndTime;
//
//    @ApiModelProperty(value = "每日活动开始结束时间，最多三个时间段24小时制度，json格式 [{\"start\":\"08:00\",\"end\":\"12:00\"},{\"start\":\"14:00\",\"end\":\"18:00\"}]")
//    private String dayTime;
//
//    @ApiModelProperty(value = "活动限购数量")
//    private Integer activityPerLimit;
//
//    @ApiModelProperty(value = "促销类型：0->无活动 1->折扣活动")
//    private Integer activityType;
//
//    @ApiModelProperty(value = "促销力度：如5折")
//    private String activityDiscount;
//
//    @ApiModelProperty(value = "是否优选 0-> 非优选  1-> 优选")
//    private Integer isYx;
//
//    @ApiModelProperty(value = "商品规格列表")
//    private String skuList;
//
//    @ApiModelProperty(value = "活动是否进行,依据此字段判断是否展示活动，0->不展示 1->展示活动")
//    private Integer activityFlag;
//
//    public Integer getActivityFlag() {
//        activityFlag = 0;
//        if(activityType == null || activityType == 0){
//            return activityFlag;
//        }
//        //判断时间段
//        boolean result1 = DateTimeUtil.isTiming(activityStartTime,activityEndTime);
//        if(!result1){
//            return activityFlag;
//        }
//        if(StringUtils.isBlank(dayTime)){
//            activityFlag = 1;
//            return activityFlag;
//        }
//        try {
//            List<ActivityDayTime> timeList = JSONArray.parseArray(dayTime,ActivityDayTime.class);
//            //在任意时间段内都是进行中状态
//            for (ActivityDayTime activityDayTime : timeList) {
//                boolean isTime = DateTimeUtil.isBelongPeriodTime(activityDayTime.getStart(),activityDayTime.getEnd());
//                if(isTime){
//                    activityFlag = 1;
//                    return activityFlag;
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return activityFlag;
//    }
}
