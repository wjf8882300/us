package com.runxsports.provider.cs.cms.service;


import javax.servlet.http.HttpServletResponse;

import com.runxsports.provider.cs.cms.entity.UserAnswer;

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

}
