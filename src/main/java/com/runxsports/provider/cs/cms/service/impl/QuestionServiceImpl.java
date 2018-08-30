package com.runxsports.provider.cs.cms.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.runxsports.provider.cs.cms.common.constant.RedisConstant;
import com.runxsports.provider.cs.cms.common.constant.enumerate.GlobalCallbackEnum;
import com.runxsports.provider.cs.cms.common.constant.enumerate.QuestionEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsErrorCodeEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsException;
import com.runxsports.provider.cs.cms.common.util.ExcelReadHelper;
import com.runxsports.provider.cs.cms.common.util.IDUtil;
import com.runxsports.provider.cs.cms.common.util.RedisUtil;
import com.runxsports.provider.cs.cms.common.util.ValidateUtils;
import com.runxsports.provider.cs.cms.entity.Question;
import com.runxsports.provider.cs.cms.mapper.QuestionMapper;
import com.runxsports.provider.cs.cms.model.ExcelQuestion;
import com.runxsports.provider.cs.cms.model.bo.QuestionBO;
import com.runxsports.provider.cs.cms.model.vo.QuestionVO;
import com.runxsports.provider.cs.cms.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

/**
 * 问题实现service类
 * @author duanfen
 *
 */
@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private RedisUtil redis;
	
	@Autowired
	private QuestionMapper questionMapper;
	
	@PostConstruct
	public void init() {
		
		// 启动时缓存题目
		List<Question> studentQuestionList = questionMapper.queryByQuestionGroup(QuestionEnum.Group.STUDENT.getString());
		if(studentQuestionList != null && studentQuestionList.size() > 0) {
			redis.setObject(RedisConstant.PRFIX_QUESTION + QuestionEnum.Group.STUDENT.getString(), studentQuestionList);
		}

		List<Question> leaderQuestionList = questionMapper.queryByQuestionGroup(QuestionEnum.Group.LEADER.getString());
		if(leaderQuestionList != null && leaderQuestionList.size() > 0) {
			redis.setObject(RedisConstant.PRFIX_QUESTION + QuestionEnum.Group.LEADER.getString(), leaderQuestionList);
		}

		List<Question> teacherQuestionList = questionMapper.queryByQuestionGroup(QuestionEnum.Group.TEACHER.getString());
		if(teacherQuestionList != null && teacherQuestionList.size() > 0) {
			redis.setObject(RedisConstant.PRFIX_QUESTION + QuestionEnum.Group.TEACHER.getString(), teacherQuestionList);
		}
	}

	@Override
	public List<Question> queryQuestion(String questionGroup) {
		
		return redis.getList(RedisConstant.PRFIX_QUESTION + questionGroup, Question.class);
		
 	}

	@Transactional
	@Override
	public void importQuestion(MultipartFile file, String questionGroup) {
		List<ExcelQuestion> questionList = null;
		InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            questionList = ExcelReadHelper.excelRead(inputStream, new String[] {"sort", "questionContent", "questionScore", "questionDesc"}, ExcelQuestion.class);
        } catch (Exception e) {
            log.error("导入出错!{}", e);
            throw new CmsException(CmsErrorCodeEnum.CMS9083007);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (questionList == null || questionList.isEmpty()) {
            log.warn("表格为空");
            throw new CmsException(CmsErrorCodeEnum.CMS9083008);
        }
		
        // 将本次之前的数据置为逻辑删除
        questionMapper.updateIsDel(questionGroup);
        
        // 导入本次数据
        List<Question> list = Lists.newArrayList();
        questionList.forEach((q)->{
        	Question question = new Question();
        	question.setId(IDUtil.nextId());
        	question.setQuestionGroup(questionGroup);
        	question.setQuestionType(QuestionEnum.Type.FILL.getString());
        	question.setQuestionContent(q.getQuestionContent().trim());
        	question.setQuestionDesc(q.getQuestionDesc());
        	question.setQuestionScore(q.getQuestionScore());
        	question.setQuestionSort(q.getSort());
        	question.setCreateDate(new Date());
        	question.setLastUpdateDate(new Date());
        	list.add(question);
        });
        
        int rows = questionMapper.batchSave(list);
        if(rows != list.size()) {
        	log.error("批量导入题目失败");
        	throw new CmsException(CmsErrorCodeEnum.CMS9083004);
        }
        
        init();
		
	}

	@Override
	public PageInfo<QuestionVO> queryAllQuestion(QuestionBO questionBO) {
		
		Integer currentPage = questionBO.getStart();
        Integer pageSize = questionBO.getLength();
        ValidateUtils.notBlank(String.valueOf(currentPage), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        ValidateUtils.notBlank(String.valueOf(pageSize), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        if (currentPage < 0 || pageSize <= 0) {
            log.error("分页数据有误");
            throw new CmsException(GlobalCallbackEnum.PARAMETER_ILLEGAL);
        }

        PageHelper.startPage(currentPage, pageSize);
		List<QuestionVO> result = questionMapper.queryALl(questionBO);
		PageInfo<QuestionVO> pageInfo = new PageInfo<QuestionVO>(result);
		return pageInfo;
	}
}
