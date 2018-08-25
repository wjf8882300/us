package com.runxsports.provider.cs.cms.mapper;

import org.apache.ibatis.annotations.Param;

import com.runxsports.provider.cs.cms.entity.UserAnswer;
import tk.mybatis.mapper.common.Mapper;

public interface UserAnswerMapper extends Mapper<UserAnswer> {
	
	/**
	 * 新增用户答题答案
	 * @param answer
	 * @return
	 */
	int save(@Param("t") UserAnswer answer);
	
	/**
	 * 新增用户答题答案
	 * @param answer
	 * @return
	 */
	int deleteByUserId(@Param("t") UserAnswer answer);
	
	/**
	 * 查询当前人是否答过当前的题目
	 * @param answer
	 * @return
	 */
	int findByQuestion(@Param("t") UserAnswer answer);
	
}