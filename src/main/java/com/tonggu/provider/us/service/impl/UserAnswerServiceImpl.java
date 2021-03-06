package com.tonggu.provider.us.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tonggu.provider.us.common.constant.enumerate.GlobalCallbackEnum;
import com.tonggu.provider.us.common.constant.enumerate.UserEnum;
import com.tonggu.provider.us.common.exception.CmsErrorCodeEnum;
import com.tonggu.provider.us.common.exception.CmsException;
import com.tonggu.provider.us.common.util.ExcelExportUtils;
import com.tonggu.provider.us.common.util.IDUtil;
import com.tonggu.provider.us.common.util.ValidateUtils;
import com.tonggu.provider.us.entity.UserAnswer;
import com.tonggu.provider.us.mapper.UserAnswerMapper;
import com.tonggu.provider.us.model.bo.UserAnswerBO;
import com.tonggu.provider.us.model.bo.UserBO;
import com.tonggu.provider.us.model.vo.UserAnswerVO;
import com.tonggu.provider.us.service.UserAnswerService;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户答题service实现类
 * @author duanfen
 *
 */
@Slf4j
@Service
public class UserAnswerServiceImpl implements UserAnswerService{
	
	@Autowired
	private UserAnswerMapper mapper;

	@Override
	public void save(List<UserAnswer> resultList, Long userId) {
		
		if(resultList == null || resultList.size() == 0) {
			throw new CmsException(GlobalCallbackEnum.PARAMETER_ILLEGAL);
		}
				
		for(UserAnswer answer : resultList) {
			answer.setId(IDUtil.nextId());
			answer.setUserId(userId);
			answer.setDestUserId(userId);
			answer.setCreateDate(new Date());
			answer.setLastUpdateDate(new Date());
		}

		mapper.deleteByUserIdAndDestUserId(userId, userId);

		int rows = mapper.batchInsert(resultList);
		if(rows != resultList.size()) {
			throw new CmsException(CmsErrorCodeEnum.CMS9083004);
		}
	}

	@Override
	public void export(UserBO userBO,HttpServletResponse response) {
		if(StringUtils.isEmpty(userBO.getUserType())) {
			throw new CmsException(CmsErrorCodeEnum.CMS9083010);
		}
		List<UserAnswerVO> list = this.mapper.queryByGroup(userBO);
		//excel标题
	    String[] title = { "姓名", "学号", "班级", "所在支部","支部书记","辅导员","辅导员工号", "成绩"};
	    String[] values= {"userName","userNo","className","teamName","teamLeader","teacher","teacherNo","answer"};
	    //sheet名
	    String sheetName = userBO.getUserType().equals(UserEnum.Type.STUDENT.getString())? "党员自评":userBO.getUserType().equals(UserEnum.Type.LEADER.getString()) ?"支部书记评价":"辅导员评价";
	    sheetName += "成绩";
	    ExcelExportUtils.exportExcelData(sheetName, title, values,list,response);
	}

	@Override
	public PageInfo<UserAnswerVO> queryAll(UserAnswerBO userAnswerBO) {
	
		Integer currentPage = userAnswerBO.getStart();
        Integer pageSize = userAnswerBO.getLength();
        ValidateUtils.notBlank(String.valueOf(currentPage), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        ValidateUtils.notBlank(String.valueOf(pageSize), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        ValidateUtils.notBlank(userAnswerBO.getUserType(), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        if (currentPage < 0 || pageSize <= 0) {
            log.error("分页数据有误");
            throw new CmsException(GlobalCallbackEnum.PARAMETER_ILLEGAL);
        }

        PageHelper.startPage(currentPage, pageSize);
        List<UserAnswerVO> result = null;
        if(UserEnum.Type.STUDENT.getString().equals(userAnswerBO.getUserType())) {
        	result = mapper.queryStudentScore(userAnswerBO);
        } else {
        	result = mapper.queryOtherScore(userAnswerBO);
        }
		PageInfo<UserAnswerVO> pageInfo = new PageInfo<UserAnswerVO>(result);
		return pageInfo;
	}

	@Override
	public PageInfo<UserAnswerVO> queryNotScore(UserAnswerBO userAnswerBO) {
		Integer currentPage = userAnswerBO.getStart();
        Integer pageSize = userAnswerBO.getLength();
        ValidateUtils.notBlank(String.valueOf(currentPage), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        ValidateUtils.notBlank(String.valueOf(pageSize), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        ValidateUtils.notBlank(userAnswerBO.getUserType(), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        if (currentPage < 0 || pageSize <= 0) {
            log.error("分页数据有误");
            throw new CmsException(GlobalCallbackEnum.PARAMETER_ILLEGAL);
        }
        PageHelper.startPage(currentPage, pageSize);
        List<UserAnswerVO> result = null;
        if(UserEnum.Type.STUDENT.getString().equals(userAnswerBO.getUserType())) {
        	result = mapper.queryStudentNotScore(userAnswerBO);
        } else if(UserEnum.Type.LEADER.getString().equals(userAnswerBO.getUserType())){
        	result = mapper.queryTeamNotScore(userAnswerBO);
        }else {
        	result = mapper.queryTeacherNotScore(userAnswerBO);
        }
		PageInfo<UserAnswerVO> pageInfo = new PageInfo<UserAnswerVO>(result);
		return pageInfo;
	}

	@Override
	public void saveAnswer(List<UserAnswer> resultList, Long userId) {
		if(resultList == null || resultList.size() == 0) {
			throw new CmsException(GlobalCallbackEnum.PARAMETER_ILLEGAL);
		}
		for(UserAnswer answer : resultList) {
			answer.setId(IDUtil.nextId());
			answer.setUserId(userId);
			answer.setCreateDate(new Date());
			answer.setLastUpdateDate(new Date());
		}
		//批量删除之前已打分，并且本次重复打分的信息
		this.mapper.deleteByDestUserId(resultList,userId);
		//批量新增打分信息
		int rows = mapper.batchInsert(resultList);
		if(rows != resultList.size()) {
			throw new CmsException(CmsErrorCodeEnum.CMS9083004);
		}	
	}
}
