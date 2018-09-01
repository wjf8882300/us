package com.tonggu.provider.cs.cms.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AnswerBO extends BaseBO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8344409170062619033L;
	/**
	 * 答案
	 */
	private String result;
}
