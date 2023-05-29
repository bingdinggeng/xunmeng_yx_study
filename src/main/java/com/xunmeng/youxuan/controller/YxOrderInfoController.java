package com.xunmeng.youxuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.requestqo.OrderQueryQo;
import com.xunmeng.youxuan.responsedto.OrderInfoDto;
import com.xunmeng.youxuan.service.IYxOrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: YxOrderInfoController
 * Package: com.xunmeng.youxuan.controller
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/29 14:55
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(tags = "7.0 订单管理接口", value = "订单管理接口")
@ApiSort(value = 7)
@RequestMapping("/order")
@RequiredArgsConstructor
public class YxOrderInfoController {
    private final IYxOrderInfoService yxOrderInfoService;

    @ApiOperation(value = "用户订单列表", notes = "用户订单列表")
    @PostMapping("/list")
    public Result<IPage<OrderInfoDto>> orderList(@RequestBody @Validated OrderQueryQo requestModel) {
        return yxOrderInfoService.orderList(requestModel);
    }
}
