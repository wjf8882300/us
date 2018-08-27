package com.runxsports.provider.cs.cms.model.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userType;

	/**
	 * 唯一识别号
	 */
	private String token;
}
