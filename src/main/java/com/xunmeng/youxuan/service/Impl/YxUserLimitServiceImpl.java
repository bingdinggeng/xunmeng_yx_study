package com.xunmeng.youxuan.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.domain.YxUserLimit;
import com.xunmeng.youxuan.mapper.YxUserLimitMapper;
import com.xunmeng.youxuan.responsedto.UserLimitDto;
import com.xunmeng.youxuan.service.IYxUserLimitService;
import com.xunmeng.youxuan.utils.BaseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ClassName: YxUserLimitServiceImpl
 * Package: com.xunmeng.youxuan.service.Impl
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/21 13:46
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class YxUserLimitServiceImpl extends ServiceImpl<YxUserLimitMapper, YxUserLimit> implements IYxUserLimitService {

    private final BaseUtil baseUtil;


    @Override
    public Response<UserLimitDto> limitUserInfo() {

        UserInfo userInfo = baseUtil.getCurrentUserInfo();

        return null;
    }
}
