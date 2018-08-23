package com.runxsports.provider.cs.cms.model.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用戶輸入對象
 * @author wangjf
 * 
 */
@Data
public class UserBO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用戶ID
	 */
	private Long userId;
}
