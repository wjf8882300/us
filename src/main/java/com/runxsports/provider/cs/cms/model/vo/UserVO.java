package com.runxsports.provider.cs.cms.model.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 用户信息
 * @author wangjf
 *
 */
@Data
public class UserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户类型
	 */
	private String userType;
	
	/**
	 * 用户姓名
	 */
	private String userName;
	

}
