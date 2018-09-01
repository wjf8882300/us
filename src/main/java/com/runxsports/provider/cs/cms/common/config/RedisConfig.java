package com.runxsports.provider.cs.cms.common.config;

import java.time.Duration;
import java.util.Map;
import java.util.Set;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.runxsports.provider.cs.cms.common.constant.RedisConstant;

@Configuration
@EnableCaching
public class RedisConfig {

	@Bean(name = RedisConstant.CACHE_MANAGER_NAME)
	public CacheManager cacheManager(RedisConnectionFactory factory) {
	    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
	    config = config.entryTtl(Duration.ofDays(30)) 
	            .disableCachingNullValues();

	    // 设置一个初始化的缓存空间set集合
	    Set<String> cacheNames =  Sets.newConcurrentHashSet();
	    cacheNames.add(RedisConstant.CACHE_QUESTION);
	    cacheNames.add(RedisConstant.CACHE_PARAM);
	    cacheNames.add(RedisConstant.CACHE_USER);

	    // 对每个缓存空间应用不同的配置
	    Map<String, RedisCacheConfiguration> configMap = Maps.newConcurrentMap();
	    configMap.put(RedisConstant.CACHE_QUESTION, config);
	    configMap.put(RedisConstant.CACHE_PARAM, config);
	    configMap.put(RedisConstant.CACHE_USER, config);

	    RedisCacheManager cacheManager = RedisCacheManager.builder(factory)     
	            .initialCacheNames(cacheNames)
	            .withInitialCacheConfigurations(configMap)
	            .build();
	    return cacheManager;
	}
}
