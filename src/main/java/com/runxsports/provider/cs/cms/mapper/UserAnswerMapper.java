package com.runxsports.provider.cs.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.runxsports.provider.cs.cms.entity.UserAnswer;
import com.runxsports.provider.cs.cms.model.vo.UserAnswerVO;

import tk.mybatis.mapper.common.Mapper;

public interface UserAnswerMapper extends Mapper<UserAnswer> {
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
	
	/**
	 * 根据类型导出成绩
	 * @param group
	 * @return
	 */
	List<UserAnswerVO> queryByGroup(@Param("group") String group);
	
	/**
	 * 根据用户类型查询评分
	 * @param userType
	 * @return
	 */
	List<UserAnswerVO> queryStudentScore();
	
	/**
	 * 查询其他分数
	 * @param group
	 * @return
	 */
	List<UserAnswerVO> queryOtherScore(@Param("userType") String userType);
	
	/**
	 * 删除历史答案
	 * @param answer
	 * @return
	 */
	int deleteByUserIdAndDestUserId(@Param("userId") Long userId, @Param("destUserId") Long destUserId);
	
	/**
	 * 批量插入答案
	 * @param list
	 * @return
	 */
	int batchInsert(@Param("list")List<UserAnswer> list);
	
	
	
	/**
	 * 查询学生未自评分数的新信息
	 * @param userType
	 * @return
	 */
	List<UserAnswerVO> queryStudentNotScore();
	
	/**
	 * 查询党支部/辅导员未给学生打分的基本信息
	 * @param group
	 * @return
	 */
	List<UserAnswerVO> queryTeamNotScore(@Param("userType") String userType);
}