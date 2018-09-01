package com.runxsports.provider.cs.cms.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.runxsports.provider.cs.cms.entity.Question;
import com.runxsports.provider.cs.cms.model.bo.QuestionBO;
import com.runxsports.provider.cs.cms.model.vo.QuestionVO;

import tk.mybatis.mapper.common.Mapper;

/**
 * 问卷题目mapper
 * @author duanfen
 *
 */
public interface QuestionMapper extends Mapper<Question> {
	
	/**
	 * 批量导入新增的问题
	 * @param list
	 * @return
	 */
	int batchSave(@Param("list") List<Question> list);
	
	/**
	 * 删除之前的所有问题
	 * @return
	 */
	int updateIsDel(@Param("questionGroup") String questionGroup);
	
	/**
	 * 通过类型查下问题
	 * @param questionGroup
	 * @return
	 */
	List<QuestionVO> queryByQuestionGroup(@Param("questionGroup") String questionGroup);
	
	/**
	 * 查询题目信息
	 * @param bo
	 * @return
	 */
	List<Question> queryALl(@Param("bo") QuestionBO bo);
	
}