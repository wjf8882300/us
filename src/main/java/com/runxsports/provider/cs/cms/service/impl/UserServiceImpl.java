package com.runxsports.provider.cs.cms.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runxsports.provider.cs.cms.common.constant.enumerate.GlobalCallbackEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsException;
import com.runxsports.provider.cs.cms.entity.User;
import com.runxsports.provider.cs.cms.mapper.UserMapper;
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
		User result = userMapper.selectOne(record);
		if(result != null) {
			userVO.setUserType(result.getUserType());
			userVO.setUserName(result.getUserName());
		}
		
		return userVO;
	}


}
