package com.runxsports.provider.cs.cms.model.vo;

import java.io.Serializable;
import java.util.List;
import com.runxsports.provider.cs.cms.entity.UserAnswer;

import lombok.Data;

@Data
public class UserAnswerVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<UserAnswer> answerList;
	
	public UserAnswerVO(List<UserAnswer> answerList) {
		this.answerList = answerList;
	}
}
