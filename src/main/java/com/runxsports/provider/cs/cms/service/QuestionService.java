package com.runxsports.provider.cs.cms.service;


import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.runxsports.provider.cs.cms.entity.Question;
import com.runxsports.provider.cs.cms.model.bo.QuestionBO;
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
	void importQuestion(MultipartFile file, String questionGroup);
	
	
	/**
	 * 查询所有问题
	 * 
	 * @param questionType
	 * @return
	 */
	PageInfo<Question> queryAllQuestion(QuestionBO questionBO);
}
