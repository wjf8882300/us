package com.tonggu.provider.us.model.vo;

import java.io.Serializable;
import java.util.Date;

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
	
	/**
	 * 辅导员工号
	 */
	private String teacherNo;
	
	/**
	 * 党支部书记
	 */
	private String teamLeader;
	
	/**
	 * 题号
	 */
	private Integer questionSort;
	
	/**
	 * 新增時間
	 */
	private Date createDate;

}
