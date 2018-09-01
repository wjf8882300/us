package com.tonggu.provider.cs.cms.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用戶輸入對象
 * @author wangjf
 * 
 */
@Data
@EqualsAndHashCode(callSuper=false)
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
	
	/**
	 * 支部书记/辅导员编号
	 */
	private String teamNo;
	
	/**
	 * 支部名称
	 */
	private String teamName;
	
	/**
	 * 班级名称
	 */
	private String className;
	
	/**
	 * 学号
	 */
	private String userNo;
}
