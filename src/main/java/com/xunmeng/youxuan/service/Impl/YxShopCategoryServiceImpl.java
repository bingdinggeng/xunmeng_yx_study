package com.xunmeng.youxuan.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.youxuan.base.Response;
import com.xunmeng.youxuan.base.Results;
import com.xunmeng.youxuan.domain.YxShopCategory;
import com.xunmeng.youxuan.enums.CacheKeyEnum;
import com.xunmeng.youxuan.enums.ConstantEnum;
import com.xunmeng.youxuan.mapper.YxShopCategoryMapper;
import com.xunmeng.youxuan.responsedto.ShopCategoryDto;
import com.xunmeng.youxuan.service.IYxShopCategoryService;
import com.xunmeng.youxuan.utils.RedisStringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.xunmeng.youxuan.utils.UserLoginUtil.USER_CACHE_TIME;

/**
 * ClassName: YxShopCategoryServiceImpl
 * Package: com.xunmeng.youxuan.service.Impl
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/24 11:35
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class YxShopCategoryServiceImpl extends ServiceImpl<YxShopCategoryMapper, YxShopCategory>
        implements IYxShopCategoryService {

    private final RedisStringUtil redisUtil;

    @Override
    public Response<List<ShopCategoryDto>> getCategoryList() {
        String cacheKey = CacheKeyEnum.SHOP_CATEGORY;
        List<ShopCategoryDto> result = null;
        Object shopCategoryList = redisUtil.get(cacheKey);

        if (shopCategoryList != null) {
            result = JSONArray.parseArray(shopCategoryList.toString(), ShopCategoryDto.class);
        } else {
            List<YxShopCategory> shopList = this.list(new QueryWrapper<YxShopCategory>()
                    .eq("data_status", ConstantEnum.NORMAL).orderByAsc("sort_num"));

            if (shopList != null && !shopList.isEmpty()) {
                result = JSONArray.parseArray(JSONArray.toJSONString(shopList), ShopCategoryDto.class);
            }
            redisUtil.set(cacheKey, JSONArray.toJSONString(shopList), USER_CACHE_TIME);
        }
        return Results.newSuccessResponse(result);
    }
}
