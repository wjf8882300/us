package com.runxsports.provider.cs.cms.model.vo;

import java.io.Serializable;
import java.util.List;

import com.runxsports.provider.cs.cms.entity.Question;

import lombok.Data;

@Data
public class QuestionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Question> questionList;
	
	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 题目内容
	 */
	private String questionContent;
	
	/**
	 * 题目内容
	 */
	private String questionGroup;
	
	/**
	 * 排序
	 */
	private Integer questionSort;
	
	/**
	 * 评分
	 */
	private Integer questionScore;
	
	/**
	 * 说明
	 */
	private String questionDesc;
	
	public QuestionVO(List<Question> questionList) {
		this.questionList = questionList;
	}
	
	public QuestionVO(){

	}
}
