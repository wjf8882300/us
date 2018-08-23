package com.runxsports.provider.cs.cms.service;

import com.runxsports.provider.cs.cms.model.vo.QuestionVO;

public interface QuestionService {

	/**
	 * 查询题目
	 * @param questionGroup
	 * @return
	 */
	QuestionVO queryQuestion(String questionGroup);
}
