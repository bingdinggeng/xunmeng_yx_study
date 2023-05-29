package com.xunmeng.youxuan.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.domain.YxFinancialSettlement;
import com.xunmeng.youxuan.logic.BaseLogic;
import com.xunmeng.youxuan.mapper.YxFinancialSettlementMapper;
import com.xunmeng.youxuan.requestqo.FinanceShopQo;
import com.xunmeng.youxuan.responsedto.SumFinanceShopDto;
import com.xunmeng.youxuan.service.IYxFinancialSettlementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * ClassName: YxFinancialSettlementServiceImpl
 * Package: com.xunmeng.youxuan.service.Impl
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/21 9:56
 * @Version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class YxFinancialSettlementServiceImpl extends ServiceImpl<YxFinancialSettlementMapper, YxFinancialSettlement>
        implements IYxFinancialSettlementService {

    private final BaseLogic baseUtil;

    @Override
    public Result<SumFinanceShopDto> listForUser(FinanceShopQo requestModel) {
        UserInfo userInfo = baseUtil.getCurrentUserInfo();


        return null;
    }

}
