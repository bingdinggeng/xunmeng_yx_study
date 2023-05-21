package com.xunmeng.youxuan.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.requestqo.FinanceShopQo;
import com.xunmeng.youxuan.responsedto.SumFinanceShopDto;
import com.xunmeng.youxuan.responsedto.UserLimitDto;
import com.xunmeng.youxuan.service.IYxFinancialSettlementService;
import com.xunmeng.youxuan.service.IYxUserLimitService;
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
 * ClassName: YxFinancialSettlementController
 * Package: com.xunmeng.youxuan.controller
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/21 9:22
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(tags = "10.0 财务管理系统", value = "财务管理系统")
@ApiSort(value = 13)
@RequestMapping("/finance")
@RequiredArgsConstructor
public class YxFinancialSettlementController {

    private final IYxFinancialSettlementService yxFinancialSettlementService;
    private final IYxUserLimitService yxUserLimitService;

    @ApiOperation(value = "财务列表，用户查看", notes = "财务列表，用户查看")
    @PostMapping("/list/user")
    public Response<SumFinanceShopDto> listForUser(@RequestBody @Validated FinanceShopQo requestModel){

        return yxFinancialSettlementService.listForUser(requestModel);
    }

    @ApiOperation(value = "限额信息：用户查看", notes = "限额信息：用户查看")
    @PostMapping("/limit/user")
    public Response<UserLimitDto> limitUserInfo(){

        return yxUserLimitService.limitUserInfo();
    }
}
