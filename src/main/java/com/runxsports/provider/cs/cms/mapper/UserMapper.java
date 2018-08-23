package com.runxsports.provider.cs.cms.mapper;

import java.util.List;

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
}