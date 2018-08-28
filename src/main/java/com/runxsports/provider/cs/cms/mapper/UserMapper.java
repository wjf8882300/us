package com.runxsports.provider.cs.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.runxsports.provider.cs.cms.entity.User;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface UserMapper extends Mapper<User>, InsertListMapper<User> {
	
	/**
	 * 批量插入
	 * @param userList
	 * @return
	 */
	int batchInsert(List<User> userList);
	
	/**
	 * 删除老数据
	 * @return
	 */
	int modifyOld();
	
	/**
	 * 根据用户学号/工号查询信息
	 * @param userNo
	 * @return User
	 */
	User findByUserNo(@Param("userNo") String userNo);
	
	/***
	 * 查询支部书记下的学生
	 * @param teamLeaderNo
	 * @return List<User>
	 */
	List<User> queryUserByNo(@Param("No") String No,@Param("userType") String userType);
}