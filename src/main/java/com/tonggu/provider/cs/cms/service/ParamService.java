package com.tonggu.provider.cs.cms.service;

import java.util.List;

import com.tonggu.provider.cs.cms.entity.Param;

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
