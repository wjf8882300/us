package com.tonggu.provider.cs.cms.service;


import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageInfo;
import com.tonggu.provider.cs.cms.entity.Question;
import com.tonggu.provider.cs.cms.model.bo.QuestionBO;
import com.tonggu.provider.cs.cms.model.vo.QuestionVO;

public interface QuestionService {

	/**
	 * 查询题目
	 * @param questionGroup
	 * @return
	 */
	PageInfo<QuestionVO> queryQuestion(QuestionBO questionBO);
	
	
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
