package com.tonggu.provider.cs.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.tonggu.provider.cs.cms.common.constant.CommonConstant;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * cms
 *
 * @return
 * @author wangjf
 * @update
 * @since 2018/7/27 11:18
 **/
@SpringBootApplication
@MapperScan(basePackages = {"com.tonggu.provider.cs.cms.mapper"})
@ComponentScan(basePackages = {CommonConstant.BASE_PACKAGE,"com.alibaba.fastjson.support.spring"})
@EnableTransactionManagement
@Configuration
@EnableScheduling
public class CmsProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(CmsProviderApplication.class, args);
	}
}
