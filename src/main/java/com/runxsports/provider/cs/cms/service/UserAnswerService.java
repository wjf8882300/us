package com.runxsports.provider.cs.cms.service;


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
	

}
