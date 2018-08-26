package com.runxsports.provider.cs.cms.service;


import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.runxsports.provider.cs.cms.entity.UserAnswer;
import com.runxsports.provider.cs.cms.model.bo.UserAnswerBO;
import com.runxsports.provider.cs.cms.model.vo.UserAnswerVO;

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
	void save(UserAnswer answer);
	
	
	/**
	 * 根据类型导出数据信息
	 * @param group
	 */
	void export(String group,HttpServletResponse response);

	/**
	 * 查询所有数据
	 * @param userAnswerBO
	 * @return
	 */
	PageInfo<UserAnswerVO> queryAll(UserAnswerBO userAnswerBO);
}
