package com.runxsports.provider.cs.cms.service;


import org.springframework.web.multipart.MultipartFile;

import com.runxsports.provider.cs.cms.model.vo.QuestionVO;

public interface QuestionService {

	/**
	 * 查询题目
	 * @param questionGroup
	 * @return
	 */
	QuestionVO queryQuestion(String questionGroup);
	
	
	/**
	 * 导入题目
	 * 
	 * @param file
	 */
	void importQuestion(MultipartFile file);
	
}
