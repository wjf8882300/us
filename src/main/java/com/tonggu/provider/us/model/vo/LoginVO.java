package com.tonggu.provider.us.model.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一识别号
	 */
	private String token;
	
	/**
	 * 加密算法，1-不启用/0-启用
	 */
	private Integer algorithm;
}
