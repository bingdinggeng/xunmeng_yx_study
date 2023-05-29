package com.xunmeng.youxuan.service.Impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.youxuan.base.Result;
import com.xunmeng.youxuan.domain.UserInfo;
import com.xunmeng.youxuan.domain.YxShopInfo;
import com.xunmeng.youxuan.enums.CacheKeyEnum;
import com.xunmeng.youxuan.enums.ConstantEnum;
import com.xunmeng.youxuan.enums.ErrorCodeEnum;
import com.xunmeng.youxuan.mapper.YxShopInfoMapper;
import com.xunmeng.youxuan.requestqo.*;
import com.xunmeng.youxuan.responsedto.ShopDto;
import com.xunmeng.youxuan.service.IYxShopInfoService;
import com.xunmeng.youxuan.utils.DataUtil;
import com.xunmeng.youxuan.utils.PageUtil;
import com.xunmeng.youxuan.utils.RedisStringUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.xunmeng.youxuan.utils.UserLoginUtil.USER_CACHE_TIME;

/**
 * ClassName: YxShopInfoServiceImpl
 * Package: com.xunmeng.service.Impl
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/20 8:59
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class YxShopInfoServiceImpl extends ServiceImpl<YxShopInfoMapper, YxShopInfo> implements IYxShopInfoService {

    private final RedisStringUtil redisUtil;


    @Override
    public Result<UserInfo> loginIn(LoginQo requestModel) {
        String nickName = requestModel.getNickName();
        List<YxShopInfo> listShop = getListShop(nickName);


        if (listShop == null || listShop.isEmpty()) {
            return Result.newFailedResponse(ErrorCodeEnum.INFO_NOT_EXIST);
        } else if (listShop.size() != 1) {
            return Result.newFailedResponse(ErrorCodeEnum.INFO_NOT_ONLY);
        }

        String password = requestModel.getPass() + listShop.get(0).getEncrypt();
        BCrypt.Result res = BCrypt.verifyer().verify(password.toCharArray(), listShop.get(0).getPassword());
        if (!res.verified) {
            return Result.newFailedResponse(ErrorCodeEnum.PASSWORD_ERROR);
        }

        // 不清楚极光推送标识是啥，先不动原代码逻辑
        if (StringUtils.isNotBlank(requestModel.getRegistrationId()) && !requestModel.getRegistrationId()
                .equals(listShop.get(0).getRegistrationId())) {
            YxShopInfo updateInfo = new YxShopInfo()
                    .setShopId(listShop.get(0).getShopId())
                    .setRegistrationId(requestModel.getRegistrationId());
            this.updateById(updateInfo);
        }

        String openId = DataUtil.getUUID();
        UserInfo userInfo = cacheUserInfoForShop(openId, null, listShop.get(0), 2);
        return Result.newSuccessResponse(userInfo);
    }


    @Override
    public Result<T> passwordChange(ShopPasswordQo requestModel) {
        String nickName = requestModel.getNickName();
        List<YxShopInfo> listShop = getListShop(nickName);

        if (listShop == null || listShop.isEmpty()) {
            return Result.newFailedResponse(ErrorCodeEnum.INFO_NOT_EXIST);
        } else if (listShop.size() != 1) {
            return Result.newFailedResponse(ErrorCodeEnum.INFO_NOT_ONLY);
        }

        String password = requestModel.getPass() + listShop.get(0).getEncrypt();
        BCrypt.Result res = BCrypt.verifyer().verify(password.toCharArray(), listShop.get(0).getPassword());
        if (!res.verified) {
            return Result.newFailedResponse(ErrorCodeEnum.PASSWORD_ERROR);
        }

        String passwordNew = requestModel.getPassNew() + listShop.get(0).getEncrypt();
        listShop.get(0).setPassword(BCrypt.withDefaults().hashToString(10, passwordNew.toCharArray()));
        if (this.updateById(listShop.get(0))) {
            return Result.newSuccessResponse(ErrorCodeEnum.SUCCESS);
        }
        return Result.newFailedResponse(ErrorCodeEnum.FAIL);
    }

    @Override
    public Result<IPage<ShopDto>> getShopList(ShopListQo requestModel) {
        PageUtil.initRequestPage(requestModel);

        LambdaQueryWrapper<YxShopInfo> queryWrapper = new LambdaQueryWrapper<YxShopInfo>()
                .like(StringUtils.isNotBlank(requestModel.getShopName()), YxShopInfo::getShopName,
                        requestModel.getShopName())
                .like(requestModel.getShopCategoryId() != null && requestModel.getShopCategoryId() > 0,
                        YxShopInfo::getShopCategoryId, "," + requestModel.getShopCategoryId() + ",")
                .eq(YxShopInfo::getDataStatus, ConstantEnum.SHOP_STATUS_PASSED)
                .orderByAsc(YxShopInfo::getMonthSells);

        IPage<YxShopInfo> dataList = this.page(new Page<>(requestModel.getPageIndex(), requestModel.getPageSize())
                , queryWrapper);

        if (dataList == null || dataList.getRecords() == null || dataList.getRecords().isEmpty()) {
            return Result.newFailedResponse(ErrorCodeEnum.FAIL);
        }

        IPage<ShopDto> result = new Page<ShopDto>()
                .setTotal(dataList.getTotal())
                .setCurrent(dataList.getCurrent())
                .setPages(dataList.getPages())
                .setSize(dataList.getSize())
                .setRecords(JSONArray.parseArray(JSONArray.toJSONString(dataList.getRecords()), ShopDto.class));

        return Result.newSuccessResponse(result);
    }

    @Override
    public Result<UserInfo> registerForShop(WXShopInfoQo requestModel) {
        return null;
    }


    /**
     * description: 商家登录缓存
     *
     * @param openId
     * @param sessionKey
     * @param shop
     * @param dataSource
     * @param:
     * @return: com.xunmeng.youxuan.domain.UserInfo
     * @author LTM
     * @date: 2023/5/20 16:08
     */
    private UserInfo cacheUserInfoForShop(String openId, String sessionKey, YxShopInfo shop, int dataSource) {

        String cacheKeyShop;
        if (StringUtils.isNotEmpty(shop.getWxOpenId())) {
            cacheKeyShop = CacheKeyEnum.USER_TOKEN_INFO + shop.getWxOpenId();
        } else {
            cacheKeyShop = CacheKeyEnum.USER_TOKEN_INFO + openId;
        }

        UserCacheQo cacheQo = new UserCacheQo()
                .setUserId(shop.getShopId().intValue())
                .setRoleType(ConstantEnum.USER_SHOP);

        redisUtil.set(cacheKeyShop, JSON.toJSONString(cacheQo), USER_CACHE_TIME);

        String userKey = CacheKeyEnum.SHOP_INFO + shop.getShopId();

        UserInfo user = new UserInfo()
                .setMobileNumber(shop.getShopPhone())
                .setNickName(shop.getShopName())
                .setOpenId(StringUtils.isNotEmpty(shop.getWxOpenId()) ? shop.getWxOpenId() : openId)
                .setRealName(shop.getRealName())
                .setRoleType(ConstantEnum.USER_SHOP)
                .setUserId(shop.getShopId())
                .setAvatar(shop.getAvatar())
                .setDataSource(dataSource)
                .setDataSource(shop.getDataStatus());

        redisUtil.set(userKey, JSON.toJSONString(user), USER_CACHE_TIME);
        return user;
    }

    /**
     * 查询商家名单
     *
     * @param nickName
     * @return
     */
    private List<YxShopInfo> getListShop(String nickName) {
        return this.list(new QueryWrapper<YxShopInfo>().eq("shop_name", nickName));
    }
}
