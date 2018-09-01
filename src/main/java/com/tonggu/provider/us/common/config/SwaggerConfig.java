package com.tonggu.provider.us.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wangjf
 * @date: 2018年07月26日
 */
@Profile({"default", "dev", "test"})
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket appConfig() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(appInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.tonggu.provider.us.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo appInfo() {
        return new ApiInfoBuilder().title("内容管理").version("1.0").build();
    }

}