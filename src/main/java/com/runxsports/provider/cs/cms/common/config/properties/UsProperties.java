package com.runxsports.provider.cs.cms.common.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 路径配置
 * @author wangjf
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "us.file")
public class UsProperties {


	/**
     * 模板上传路径
     */
    private String uploadPath;
    
    /**
     * 文件下载路径
     */
    private String downloadPath;
}
