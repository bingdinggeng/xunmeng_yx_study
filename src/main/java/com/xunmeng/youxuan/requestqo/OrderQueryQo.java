package com.xunmeng.youxuan.requestqo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * ClassName: OrderQueryQo
 * Package: com.xunmeng.youxuan.requestqo
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/29 15:08
 * @Version 1.0
 */
@Data
@ApiModel("订单查询请求类")
public class OrderQueryQo extends PageRequestQo{
    @ApiModelProperty(value = "订单状态；0：待付款，默认；1：已付款，2：已接单，3：已发货（暂时不用），4：已送达 ，-1：已取消（退款）" +
            " 99:查看待接单和已接单的数据 100：全部  10：售后订单",example = "0")
    private String dataStatus;

    @ApiModelProperty(value = "开始时间",example = "2020-12-11")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @ApiModelProperty(value = "结束时间",example = "2020-12-11")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @ApiModelProperty(value = "订单姓名")
    private String userName;

    @ApiModelProperty(value = "订单电话")
    private String userTel;
}
