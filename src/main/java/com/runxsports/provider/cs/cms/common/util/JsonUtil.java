package com.runxsports.provider.cs.cms.common.util;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.runxsports.provider.cs.cms.common.exception.BaseException;

/**
 * Json工具类
 * @author wangjf
 * @create 2018年7月16日
 */
public final class JsonUtil {

	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private static final ObjectMapper objectMapper = newInstance();

	/**
	 * 将单个键值对转换成JSON字符串，用于返回只有一个键值对json时的便捷方法
	 *
	 * @param key 目标key
	 * @param value 目标value
	 * @return 字符串，转换失败时返回null
	 */
	public static String toJson(Object key, Object value) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put(key, value);
		try {
			return objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			logger.error("write to json string error:" + map, e);
			return null;
		}
	}

	/** 对象转Json */
	public static String toJson(Object obj) {
		if (ObjectUtils.isEmpty(obj)) {
			return StringUtils.EMPTY;
		}
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("对象转换Json失败{}, data:{}", e.getMessage(), obj);
		}
		return StringUtils.EMPTY;
	}

	/** Json转对象 */
	public static <T> T toBean(String json, Class<T> type) {
		return toBean(json, objectMapper.getTypeFactory().constructType(type));
	}

	/** Json转对象 */
	public static <T> T toBean(String json, Class<?> paramType, Class<?>... types) {
		return toBean(json, objectMapper.getTypeFactory().constructParametricType(paramType, types));
	}

	/** Json转对象 */
	public static <T> T toBean(String json, TypeReference<T> type) {
		return toBean(json, objectMapper.getTypeFactory().constructType(type));
	}

	/** Json转对象 */
	public static <T> T toBean(String json, JavaType type) {
		try {
			return objectMapper.readValue(json, type);
		} catch (IOException e) {
			throw new BaseException("Json转换对象失败", e);
		}
	}

	/** 根据策略生成Json */
	public static String toJson(Object obj, Include strategy) {
		if (ObjectUtils.isEmpty(obj)) {
			return StringUtils.EMPTY;
		}
		try {
			ObjectMapper mapper = newInstance();
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			mapper.setSerializationInclusion(strategy);
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("对象转换Json失败", e.getMessage());
		}
		return StringUtils.EMPTY;
	}

	/** 初始化ObjectMapper */
	private static ObjectMapper config(ObjectMapper objectMapper) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	/** 返回一个新的ObjectMapper,同时也设置默认值,供外部使用 */
	public static ObjectMapper newInstance() {
		return config(new ObjectMapper());
	}

}
