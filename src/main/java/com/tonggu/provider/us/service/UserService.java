package com.tonggu.provider.us.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageInfo;
import com.tonggu.provider.us.entity.User;
import com.tonggu.provider.us.model.bo.LoginBO;
import com.tonggu.provider.us.model.bo.UserBO;
import com.tonggu.provider.us.model.vo.LoginVO;
import com.tonggu.provider.us.model.vo.UserVO;

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
	
	/**
	 * 查询所有用户
	 * 
	 * @param userBO
	 * @return
	 */
	PageInfo<User> queryAllUser(UserBO userBO);
	
	/**
	 * 登陆
	 * 
	 * @param loginBO
	 * @return
	 */
	LoginVO login(LoginBO loginBO);
	
	/***
	 * 查询支部书记下的学生
	 * @param teamLeaderNo
	 * @return List<User>
	 */
	List<User> queryUserByNo(UserBO userBO);
	
	/**
	 * 申请接入
	 * @param loginBO
	 * @return
	 */
	LoginVO apply(LoginBO loginBO);
	
	/**
	 * 查询班级
	 * @return
	 */
	List<User> queryClass();
	
	/**
	 * 查询班级
	 * @return
	 */
	List<User> queryTeam();
}
