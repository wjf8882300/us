package com.runxsports.provider.cs.cms.model.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class QuestionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 题目内容
	 */
	private String questionContent;
	
	/**
	 * 评分
	 */
	private Integer questionScore;
}
