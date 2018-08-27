package com.runxsports.provider.cs.cms.model.bo;

import lombok.Data;

@Data
public class AttachementBO extends BaseBO{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 上传路径
	 */
	private String attachementPath;
	
	/**
	 * 上传文件名称
	 */
	private String attachementName;
}
