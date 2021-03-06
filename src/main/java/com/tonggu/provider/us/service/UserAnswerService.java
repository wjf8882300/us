package com.tonggu.provider.us.service;



import java.util.List;

import javax.servlet.http.HttpServletResponse;
import com.github.pagehelper.PageInfo;
import com.tonggu.provider.us.entity.UserAnswer;
import com.tonggu.provider.us.model.bo.UserAnswerBO;
import com.tonggu.provider.us.model.bo.UserBO;
import com.tonggu.provider.us.model.vo.UserAnswerVO;

/**
 * 用户答题service
 * @author duanfen
 *
 */
public interface UserAnswerService {
	
	/**
	 * 新增用户答题答案
	 * @param answer
	 * @return
	 */
	void save(List<UserAnswer> resultList, Long userId);
	
	
	/**
	 * 根据类型导出数据信息
	 * @param group
	 */
	void export(UserBO userBO,HttpServletResponse response);

	/**
	 * 查询所有数据
	 * @param userAnswerBO
	 * @return
	 */
	PageInfo<UserAnswerVO> queryAll(UserAnswerBO userAnswerBO);
	
	
	/**
	 * 查询未评分数的学生信息
	 * @param userType
	 * @return
	 */
	PageInfo<UserAnswerVO> queryNotScore(UserAnswerBO userAnswerBO);
	
	/**
	 * 
	 * @param userAnswerBO
	 * @return
	 */
	void saveAnswer(List<UserAnswer> resultList, Long userId);
}
