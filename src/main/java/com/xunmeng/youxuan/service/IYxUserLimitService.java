package com.xunmeng.youxuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.domain.YxUserLimit;
import com.xunmeng.youxuan.responsedto.UserLimitDto;

/**
 * ClassName: IYxUserLimitService
 * Package: com.xunmeng.youxuan.service
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/21 13:45
 * @Version 1.0
 */
public interface IYxUserLimitService extends IService<YxUserLimit> {
    /**
     * description: 限额信息：用户查看
     * @param:
     * @return: com.xunmeng.youxuan.base.Response<com.xunmeng.youxuan.responsedto.UserLimitDto>
     * @author LTM
     * @date: 2023/5/21 13:49
     */
    Response<UserLimitDto> limitUserInfo();
}
