package com.runxsports.provider.cs.cms.common.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate默认只支持json格式的解析，
 * 如果是其他格式，需要添加转换器
 * @author renshaw
 *
 */
@Configuration
public class RestTemplateConfig {
	@Bean  
    public RestTemplate restTemplate(RestTemplateBuilder builder) {  
		 RestTemplate restTemplate = builder.build();
		 restTemplate.getMessageConverters().add(new TencentHttpMessageConverter());
		 return restTemplate;
    }  
  
}
