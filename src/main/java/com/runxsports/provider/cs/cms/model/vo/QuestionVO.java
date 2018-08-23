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
	
	public QuestionVO(List<Question> questionList) {
		this.questionList = questionList;
	}
}
