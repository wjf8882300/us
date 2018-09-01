package com.runxsports.provider.cs.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.runxsports.provider.cs.cms.common.constant.RedisConstant;
import com.runxsports.provider.cs.cms.entity.Param;
import com.runxsports.provider.cs.cms.mapper.ParamMapper;
import com.runxsports.provider.cs.cms.service.ParamService;

@Service
public class ParamServiceImpl implements ParamService {

	@Autowired
	private ParamMapper paramMapper;
	
	@Cacheable(value=RedisConstant.CACHE_PARAM, key="'all'", cacheManager=RedisConstant.CACHE_MANAGER_NAME)
	@Override
	public List<Param> queryAll() {
		
		return paramMapper.selectAll();
	}

	@Caching(evict = { @CacheEvict(value = RedisConstant.CACHE_PARAM, allEntries = true, cacheManager=RedisConstant.CACHE_MANAGER_NAME) })
	@Override
	public void refresh() {
				
	}
}
