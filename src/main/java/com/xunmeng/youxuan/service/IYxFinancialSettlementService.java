package com.xunmeng.youxuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.domain.YxFinancialSettlement;
import com.xunmeng.youxuan.requestqo.FinanceShopQo;
import com.xunmeng.youxuan.responsedto.SumFinanceShopDto;

/**
 * ClassName: IYxFinancialSettlementService
 * Package: com.xunmeng.youxuan.service
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/21 9:55
 * @Version 1.0
 */
public interface IYxFinancialSettlementService extends IService<YxFinancialSettlement> {
    /**
     * description: 财务列表，用户查看
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Response<com.xunmeng.youxuan.responsedto.SumFinanceShopDto>
     * @author LTM
     * @date: 2023/5/21 10:14
     */
    Response<SumFinanceShopDto> listForUser(FinanceShopQo requestModel);
}
