package com.runxsports.provider.cs.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.runxsports.provider.cs.cms.common.constant.enumerate.DeleteStatusEnum;
import com.runxsports.provider.cs.cms.common.constant.enumerate.GlobalCallbackEnum;
import com.runxsports.provider.cs.cms.common.constant.enumerate.UserEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsErrorCodeEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsException;
import com.runxsports.provider.cs.cms.common.util.CacheUtil;
import com.runxsports.provider.cs.cms.common.util.ExcelExportUtils;
import com.runxsports.provider.cs.cms.common.util.IDUtil;
import com.runxsports.provider.cs.cms.common.util.ValidateUtils;
import com.runxsports.provider.cs.cms.entity.Question;
import com.runxsports.provider.cs.cms.entity.User;
import com.runxsports.provider.cs.cms.entity.UserAnswer;
import com.runxsports.provider.cs.cms.mapper.UserAnswerMapper;
import com.runxsports.provider.cs.cms.model.bo.UserAnswerBO;
import com.runxsports.provider.cs.cms.model.vo.UserAnswerVO;
import com.runxsports.provider.cs.cms.service.UserAnswerService;

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
	public void save(UserAnswer answer) {
		//查询改人员是否答过该题目
		int count = this.mapper.findByQuestion(answer);
		//答过删除之前的答题
		if(count > 0) {
			this.mapper.deleteByUserId(answer);
		}
		answer.setId(IDUtil.nextId());
		answer.setCreateDate(new Date());
		answer.setLastUpdateDate(new Date());
		answer.setIsDelete("0");
		answer.setVersion(1);
		//判断是否新增成功，失败抛异常
	    if(this.mapper.insert(answer) == 0) {
	    	throw new CmsException(CmsErrorCodeEnum.CMS9083004);
	    } 
	}

	@Override
	public void export(String group,HttpServletResponse response) {
		if(StringUtils.isEmpty(group)) {
			throw new CmsException(CmsErrorCodeEnum.CMS9083010);
		}
		List<UserAnswerVO> list = this.mapper.queryByGroup(group);
		//excel标题
	    String[] title = { "姓名", "学号/工号", "班级", "所在支部", "成绩"};
	    String[] values= {"userName","userNo","className","teamName","answer"};
	    //sheet名
	    String sheetName = group.equals("0")? "党员自评":group.equals("1") ?"支部书记评价":"辅导员评价";
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
        	result = mapper.queryStudentScore();
        } else {
        	result = mapper.queryOtherScore(userAnswerBO.getUserType());
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
        	result = mapper.queryStudentNotScore();
        } else {
        	result = mapper.queryTeamNotScore(userAnswerBO.getUserType());
        }
		PageInfo<UserAnswerVO> pageInfo = new PageInfo<UserAnswerVO>(result);
		return pageInfo;
	}
}
