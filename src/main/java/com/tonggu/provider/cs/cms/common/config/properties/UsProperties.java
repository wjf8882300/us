package com.tonggu.provider.cs.cms.common.config.properties;

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
@ConfigurationProperties(prefix = "us")
public class UsProperties {

	/**
	 * 上传下载路径
	 */
	private UsFile file = new UsFile();
	
	/**
	 * 微信
	 */
	private WeChat wechat = new WeChat();
	
	@Data
	public class UsFile {
		/**
	     * 基础路径
	     */
		private String basePath;
		
		/**
	     * 模版上传路径
	     */
		private String templatePath;
		
		/**
	     * 文件上传路径
	     */
	    private String uploadPath;
	    
	    /**
	     * 文件下载路径
	     */
	    private String downloadPath;
	}

	@Data
	public class WeChat {
		/**
	     * 模板上传路径
	     */
	    private String appId;
	    
	    /**
	     * 文件下载路径
	     */
	    private String appSecret;
	}
}
