package com.tonggu.provider.us.service;

import java.util.List;

import com.tonggu.provider.us.entity.Param;

public interface ParamService {

	/**
	 * 查询所有参数
	 * @return
	 */
	List<Param> queryAll();
	
	/**
	 * 刷新参数表
	 */
	void refresh();
}
