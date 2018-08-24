package com.runxsports.provider.cs.cms.service;

import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageInfo;
import com.runxsports.provider.cs.cms.entity.User;
import com.runxsports.provider.cs.cms.model.bo.UserBO;
import com.runxsports.provider.cs.cms.model.vo.UserVO;

public interface UserService {

	/**
	 * 查询用户
	 * @param userBO
	 * @return
	 */
	UserVO queryUser(UserBO userBO);
	
	/**
	 * 导入用户
	 * 
	 * @param file
	 */
	void importUser(MultipartFile file);
	
	/**
	 * 根据用户学号/工号查询信息
	 * @param userNo
	 * @return User
	 */
	User findByUserNo(String userNo);
	
	PageInfo<User> queryAllUser(UserBO userBO);
}
