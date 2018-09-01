package com.tonggu.provider.cs.cms.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
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
	
	/**
	 * 搜索
	 */
	private String username;
}
