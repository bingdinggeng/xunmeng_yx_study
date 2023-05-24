package com.xunmeng.youxuan.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunmeng.youxuan.domain.YxProductInfo;
import com.xunmeng.youxuan.mapper.YxProductInfoMapper;
import com.xunmeng.youxuan.service.IYxProductInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ClassName: YxProductInfoServiceImpl
 * Package: com.xunmeng.youxuan.service.Impl
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/24 17:33
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class YxProductInfoServiceImpl extends ServiceImpl<YxProductInfoMapper, YxProductInfo>
        implements IYxProductInfoService {

}
