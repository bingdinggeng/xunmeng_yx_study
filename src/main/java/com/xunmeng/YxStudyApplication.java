package com.xunmeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableFeignClients
@EnableTransactionManagement
@EnableAsync
@SpringBootApplication
@MapperScan("com.xunmeng.youxuan.mapper")
@EnableScheduling
@ServletComponentScan
public class YxStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(YxStudyApplication.class, args);
    }

}
