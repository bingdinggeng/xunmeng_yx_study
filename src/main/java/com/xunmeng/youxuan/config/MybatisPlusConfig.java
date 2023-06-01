package com.xunmeng.youxuan.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: MybatisPlusConfig
 * Package: com.xunmeng.youxuan.config
 * Description:
 *
 * @Author LTM
 * @Create 2023/6/1 13:44
 * @Version 1.0
 */
@Configuration
public class MybatisPlusConfig {
    /**
     *  分页插件 ，没有这个时，我的返回值结果中page和total值都为0
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
