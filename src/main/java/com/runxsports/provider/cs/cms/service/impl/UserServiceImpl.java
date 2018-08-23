package com.runxsports.provider.cs.cms.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.runxsports.provider.cs.cms.common.constant.enumerate.DeleteStatusEnum;
import com.runxsports.provider.cs.cms.common.constant.enumerate.GlobalCallbackEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsErrorCodeEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsException;
import com.runxsports.provider.cs.cms.common.util.ExcelReadHelper;
import com.runxsports.provider.cs.cms.common.util.IDUtil;
import com.runxsports.provider.cs.cms.common.util.ValidateUtils;
import com.runxsports.provider.cs.cms.entity.User;
import com.runxsports.provider.cs.cms.mapper.UserMapper;
import com.runxsports.provider.cs.cms.model.ExcelUser;
import com.runxsports.provider.cs.cms.model.bo.UserBO;
import com.runxsports.provider.cs.cms.model.vo.UserVO;
import com.runxsports.provider.cs.cms.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserVO queryUser(UserBO userBO) {
		
		if(null == userBO || null == userBO.getUserId()){
			log.error("查询用户失败，参数有误！");
            throw new CmsException(GlobalCallbackEnum.PARAMETER_ILLEGAL);
        }
		
		UserVO userVO = new UserVO();
		
		User record = new User();
		record.setId(userBO.getUserId());
		record.setIsDelete(DeleteStatusEnum.ENABLED.getString());
		User result = userMapper.selectOne(record);
		if(result != null) {
			userVO.setUserType(result.getUserType());
			userVO.setUserName(result.getUserName());
		}
		
		return userVO;
	}

	@Transactional
	@Override
	public void importUser(MultipartFile file) {
		List<ExcelUser> userList = null;
		InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            userList = ExcelReadHelper.excelRead(inputStream, new String[] {"userName", "className", "userNo", "teamName", "teamLeader", "teacher"}, ExcelUser.class);
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
        if (userList == null || userList.isEmpty()) {
            log.warn("表格为空");
            throw new CmsException(CmsErrorCodeEnum.CMS9083008);
        }
		
        // 将本次之前的数据置为逻辑删除
        userMapper.modifyOld();
        
        // 导入本次数据
        List<User> list = Lists.newArrayList();
        userList.forEach((u)->{
        	User user = new User();
        	user.setId(IDUtil.nextId());
        	user.setUserName(u.getUserName());
        	user.setClassName(u.getClassName());
        	//user.setUserType(userType);
        	user.setPassword(u.getUserNo());
        	user.setTeacher(u.getTeacher());
        	user.setTeamLeader(u.getTeamLeader());
        	user.setTeamName(u.getTeamName());
        	user.setUserNo(u.getUserNo());
        	user.setTeacher(u.getTeacher());
        	user.setCreateDate(new Date());
        	user.setLastUpdateDate(new Date());
        	list.add(user);
        });
        
        int rows = userMapper.batchInsert(list);
        if(rows != list.size()) {
        	log.error("批量插入用户信息失败");
        	throw new CmsException(CmsErrorCodeEnum.CMS9083004);
        }

	}

	@Override
	public PageInfo<User> queryAllUser(UserBO userBO) {
		
		Integer currentPage = userBO.getStart();
        Integer pageSize = userBO.getLength();
        ValidateUtils.notBlank(String.valueOf(currentPage), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        ValidateUtils.notBlank(String.valueOf(pageSize), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        if (currentPage < 0 || pageSize <= 0) {
            log.error("分页数据有误");
            throw new CmsException(GlobalCallbackEnum.PARAMETER_ILLEGAL);
        }

        PageHelper.startPage(currentPage + 1, pageSize);
        User record = new User();
		record.setUserType(userBO.getUserType());
		record.setIsDelete(DeleteStatusEnum.ENABLED.getString());
		List<User> result = userMapper.select(record);
		PageInfo<User> pageInfo = new PageInfo<User>(result);
		return pageInfo;
	}

}
