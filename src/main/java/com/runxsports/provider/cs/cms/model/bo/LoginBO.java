package com.runxsports.provider.cs.cms.model.bo;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 登陆密码
	 */
	private String password;
	
	/**
	 * 微信code
	 */
	private String code;
}
