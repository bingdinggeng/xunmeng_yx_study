package com.xunmeng.youxuan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.domain.YxOrderInfo;
import com.xunmeng.youxuan.requestqo.OrderQueryQo;
import com.xunmeng.youxuan.responsedto.OrderInfoDto;

/**
 * ClassName: IYxOrderInfoService
 * Package: com.xunmeng.youxuan.service
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/29 15:11
 * @Version 1.0
 */
public interface IYxOrderInfoService extends IService<YxOrderInfo> {
    /**
     * description: /order/list 用户订单列表
     * @param:
     * @param requestModel
     * @return: com.xunmeng.youxuan.base.Result<com.baomidou.mybatisplus.core.metadata.IPage<com.xunmeng.youxuan.responsedto.OrderInfoDto>>
     * @author LTM
     * @date: 2023/5/29 15:14
     */
    Result<IPage<OrderInfoDto>> orderList(OrderQueryQo requestModel);
}
