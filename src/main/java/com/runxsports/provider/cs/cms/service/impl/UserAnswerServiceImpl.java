package com.runxsports.provider.cs.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runxsports.provider.cs.cms.common.exception.CmsErrorCodeEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsException;
import com.runxsports.provider.cs.cms.common.util.CacheUtil;
import com.runxsports.provider.cs.cms.entity.User;
import com.runxsports.provider.cs.cms.entity.UserAnswer;
import com.runxsports.provider.cs.cms.mapper.UserAnswerMapper;
import com.runxsports.provider.cs.cms.service.UserAnswerService;

/**
 * 用户答题service实现类
 * @author duanfen
 *
 */
@Service
public class UserAnswerServiceImpl implements UserAnswerService{
	
	@Autowired
	private UserAnswerMapper mapper;

	@Override
	public void save(UserAnswer answer) {
		//查询改人员是否答过该题目
		int count = this.mapper.findByQuestion(answer);
		//答过删除之前的答题
		if(count > 0) {
			this.mapper.delete(answer);
		}
		//判断是否新增成功，失败抛异常
	    if(this.mapper.save(answer) == 0) {
	    	throw new CmsException(CmsErrorCodeEnum.CMS9083004);
	    } 
	}


}
