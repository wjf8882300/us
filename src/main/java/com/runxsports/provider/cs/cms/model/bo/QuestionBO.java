package com.runxsports.provider.cs.cms.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class QuestionBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号
	 */
	private String userNo;
	
	/**
	 * 问题类型
	 */
	private String questionGroup;
	
	/**
	 * 用户类型
	 */
	private String userType;
	
}
