package com.runxsports.provider.cs.cms.model.bo;

import lombok.Data;

/**
 * 用戶輸入對象
 * @author wangjf
 * 
 */
@Data
public class UserBO extends BaseBO {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用戶ID
	 */
	private Long userId;
	
	/**
	 * 用户类型
	 */
	private String userType;
	
	/**
	 * 用户姓名
	 */
	private String userName;
}
