package com.tonggu.provider.cs.cms.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LoginBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户类型
	 */
	private String userType;
	
	/**
	 * 登陆密码
	 */
	private String password;
	
	/**
	 * 微信code
	 */
	private String code;
}
