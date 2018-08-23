package com.runxsports.provider.cs.cms.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runxsports.provider.cs.cms.common.constant.RedisConstant;
import com.runxsports.provider.cs.cms.common.constant.enumerate.DeleteStatusEnum;
import com.runxsports.provider.cs.cms.common.constant.enumerate.QuestionEnum;
import com.runxsports.provider.cs.cms.common.util.RedisUtil;
import com.runxsports.provider.cs.cms.entity.Question;
import com.runxsports.provider.cs.cms.mapper.QuestionMapper;
import com.runxsports.provider.cs.cms.model.vo.QuestionVO;
import com.runxsports.provider.cs.cms.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private RedisUtil redis;
	
	@Autowired
	private QuestionMapper questionMapper;
	
	@PostConstruct
	public void init() {
		
		// 启动时缓存题目
		Question record = new Question();
		record.setIsDelete(DeleteStatusEnum.ENABLED.getString());
		
		record.setQuestionGroup(QuestionEnum.Group.STUDENT.toString());
		List<Question> studentQuestionList = questionMapper.select(record);
		if(studentQuestionList != null && studentQuestionList.size() > 0) {
			redis.setObject(RedisConstant.PRFIX_QUESTION + record.getQuestionGroup(), new QuestionVO(studentQuestionList));
		}

		record.setQuestionGroup(QuestionEnum.Group.LEADER.toString());
		List<Question> leaderQuestionList = questionMapper.select(record);
		if(leaderQuestionList != null && leaderQuestionList.size() > 0) {
			redis.setObject(RedisConstant.PRFIX_QUESTION + record.getQuestionGroup(), new QuestionVO(leaderQuestionList));
		}
		
		record.setQuestionGroup(QuestionEnum.Group.TEACHER.toString());
		List<Question> teacherQuestionList = questionMapper.select(record);
		if(teacherQuestionList != null && teacherQuestionList.size() > 0) {
			redis.setObject(RedisConstant.PRFIX_QUESTION + record.getQuestionGroup(), new QuestionVO(teacherQuestionList));
		}
	}

	@Override
	public QuestionVO queryQuestion(String questionGroup) {
		
		return redis.getObject(RedisConstant.PRFIX_QUESTION + questionGroup, QuestionVO.class);
		
 	}
}
