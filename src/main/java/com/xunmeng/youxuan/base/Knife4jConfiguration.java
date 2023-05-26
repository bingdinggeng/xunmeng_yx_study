package com.xunmeng.youxuan.base;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ClassName: Knife4jConfiguration
 * Package: com.xunmeng.youxuan.base
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/26 15:38
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
public class Knife4jConfiguration {
    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public Knife4jConfiguration(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("test优选接口文档")
                        .description("# 寻梦优选商城后台 RESTful APIs ")
                        .termsOfServiceUrl("https://www.xunmengvip.com/")
                        .contact(new Contact("bingdinggeng 原代码由 howsun, JP, jinduoxia 编写","https://www.xunmengvip.com","bingdingeng@gmail.com"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("2.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.xunmeng.youxuan.controller"))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions("2.X版本"));

        return docket;
    }
}
