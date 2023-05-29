package com.xunmeng.youxuan.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.domain.YxUserLimit;
import com.xunmeng.youxuan.enums.ConstantEnum;
import com.xunmeng.youxuan.enums.ErrorCodeEnum;
import com.xunmeng.youxuan.logic.BaseLogic;
import com.xunmeng.youxuan.mapper.YxUserLimitMapper;
import com.xunmeng.youxuan.responsedto.UserLimitDto;
import com.xunmeng.youxuan.service.IYxUserLimitService;
import com.xunmeng.youxuan.utils.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private final BaseLogic baseUtil;


    @Override
    public Result<UserLimitDto> limitUserInfo() {

        UserInfo userInfo = baseUtil.getCurrentUserInfo();
        if (userInfo == null) {
            return Result.newFailedResponse(ErrorCodeEnum.SESSION_TIMEOUT);
        }
        if (userInfo.getRoleType() == ConstantEnum.USER_SHOP) {
            return Result.newFailedResponse(ErrorCodeEnum.PERMISSION_DENIED);
        }

        UserLimitDto userLimitDto = null;
        List<YxUserLimit> userLimits = this.list(new QueryWrapper<YxUserLimit>()
                .eq("user_id", userInfo.getUserId()));
        if (userLimits != null && userLimits.size() > 0) {
            userLimitDto = new UserLimitDto();
            // 获取当前月和日
            String yearMonth = DateTimeUtil.getYearMonth();
            String yearMonthDay = DateTimeUtil.getYearMonthDay();
            YxUserLimit limit = userLimits.get(0);
            if (yearMonth.equals(limit.getMonthStr())) {
                // 判断是本月
                userLimitDto.setMonthMoney(limit.getMonthMoney());
            }
            if (yearMonthDay.equals(limit.getDayStr())) {
                // 是本日
                userLimitDto.setDayMoney(limit.getDayMoney());
            }
            userLimitDto.setDayMax(limit.getDayMax())
                    .setMonthMax(limit.getMonthMax())
                    .setMonthStr(yearMonth)
                    .setDayStr(yearMonthDay);
        }
        return Result.newSuccessResponse(userLimitDto);
    }
}
