package com.runxsports.provider.cs.cms.model.vo;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserAnswerVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 学号/工号
	 */
	private String userNo;
	
	/**
	 * 名称
	 */
	private String userName;

	/**
	 * 党支部
	 */
	private String teamName;
	
	/**
	 * 班级
	 */
	private String className;
	
	/**
	 * 成绩
	 */
	private String  answer;
	
	/**
	 * 辅导员
	 */
	private String teacher;
	

}
