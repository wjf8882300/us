package com.runxsports.provider.cs.cms.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.CacheEvict;
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
	private QuestionMapper questionMapper;
	
	@Cacheable(value=RedisConstant.CACHE_QUESTION, key="#questionBO.questionGroup + ':' + #questionBO.start", cacheManager=RedisConstant.CACHE_MANAGER_NAME)
	@Override
	public PageInfo<QuestionVO> queryQuestion(QuestionBO questionBO) {
		
		Integer currentPage = questionBO.getStart();
        Integer pageSize = questionBO.getLength();
        ValidateUtils.notBlank(String.valueOf(currentPage), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        ValidateUtils.notBlank(String.valueOf(pageSize), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        ValidateUtils.notBlank(questionBO.getQuestionGroup(), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        if (currentPage < 0 || pageSize <= 0) {
            log.error("分页数据有误");
            throw new CmsException(GlobalCallbackEnum.PARAMETER_ILLEGAL);
        }

        PageHelper.startPage(currentPage, pageSize);
		List<QuestionVO> studentQuestionList = questionMapper.queryByQuestionGroup(questionBO.getQuestionGroup());
		
		return new PageInfo<QuestionVO>(studentQuestionList);
 	}

	@Caching(evict = { @CacheEvict(value = RedisConstant.CACHE_QUESTION, allEntries = true, cacheManager=RedisConstant.CACHE_MANAGER_NAME) })
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
	}

	@Override
	public PageInfo<Question> queryAllQuestion(QuestionBO questionBO) {
		
		Integer currentPage = questionBO.getStart();
        Integer pageSize = questionBO.getLength();
        ValidateUtils.notBlank(String.valueOf(currentPage), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        ValidateUtils.notBlank(String.valueOf(pageSize), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        if (currentPage < 0 || pageSize <= 0) {
            log.error("分页数据有误");
            throw new CmsException(GlobalCallbackEnum.PARAMETER_ILLEGAL);
        }

        PageHelper.startPage(currentPage, pageSize);
		List<Question> result = questionMapper.queryALl(questionBO);
		result.forEach((t)->{
			t.setQuestionGroup(QuestionEnum.Group.getEnum(Integer.valueOf(t.getQuestionGroup())).getValue());
		});
		PageInfo<Question> pageInfo = new PageInfo<Question>(result);
		return pageInfo;
	}
}
