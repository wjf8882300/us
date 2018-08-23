package com.runxsports.provider.cs.cms.service;

import org.springframework.web.multipart.MultipartFile;

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
}
