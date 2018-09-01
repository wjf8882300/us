package com.tonggu.provider.us.model.bo;

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
	 * 问题类型
	 */
	private String questionGroup;

	
	private String keys;
	
}
