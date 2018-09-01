package com.tonggu.provider.cs.cms.common.config;

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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.tonggu.provider.cs.cms.common.constant.RedisConstant;

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
	
	@Bean
	public RedisTemplate<String, Object> jsonSerializerRedisTemplate(
			GenericJackson2JsonRedisSerializer objectSerializer, RedisConnectionFactory factory) {
		RedisTemplate<String, Object> redis = new RedisTemplate<String, Object>();
		redis.setConnectionFactory(factory);
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();

		redis.setKeySerializer(stringSerializer);
		redis.setValueSerializer(objectSerializer);
		redis.setHashKeySerializer(stringSerializer);
		redis.setHashValueSerializer(objectSerializer);
		return redis;
	}

	@Bean
	public GenericJackson2JsonRedisSerializer redisSerializer() {
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		om.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		return new GenericJackson2JsonRedisSerializer(om);
	}
}
