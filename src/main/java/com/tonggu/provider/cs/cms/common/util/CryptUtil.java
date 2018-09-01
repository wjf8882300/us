package com.tonggu.provider.cs.cms.common.util;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.serializer.ToStringSerializer;

public class CryptUtil {

	private static SerializeConfig mapping = null;
	private static SerializeConfig getInstance() {
		synchronized (CryptUtil.class) {
			if(mapping == null) {
				mapping = new SerializeConfig();
		        String dateFormat;
		        dateFormat = "yyyy-MM-dd HH:mm:ss";
		        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
		        mapping.put(Long.class, ToStringSerializer.instance);
			}
		}
        return mapping;
	}
	
	public static SerializeFilter filter(String... keys) {
	    List<String> arr = (keys != null && keys.length > 0) ? Arrays.asList(keys) : null;
	    if(arr != null) {
	    	PropertyFilter propertyFilter = new PropertyFilter() {
		        @Override
		        public boolean apply(Object o, String s, Object o1) {
		        	return arr.contains(s);
		        }
		    };
		    return propertyFilter;
	    }
	    
	   return null;
	}
	
	/**
	 * 加密
	 * @param data
	 * @param token
	 * @param filter 显示需要暂时的字段
	 * @return
	 */
	public static <T> String encrypt(T data, String token, Boolean isEncrypt, String... keys) {
				
		String jsonString = toJSONString(data, keys);
		if(!isEncrypt) {
			return jsonString;
		}
		String base64 = Base64.encodeBase64String(jsonString.getBytes(Charset.forName("UTF-8")));
        return DesUtil.strEnc(base64, token, null, null);
	}
	
	/**
	 * 加密
	 * 
	 * @param data
	 * @param token
	 * @return
	 */
	public static <T> String encryptList(List<T> data, String token, Boolean isEncrypt, String... keys) {
		
		String jsonString = toJSONString(data, keys);
		if(!isEncrypt) {
			return jsonString;
		}
		String base64 = Base64.encodeBase64String(jsonString.getBytes(Charset.forName("UTF-8")));
        return DesUtil.strEnc(base64, token, null, null);
	}
	
	/**
	 * 解密
	 * @param data
	 * @param token
	 * @param clazz
	 * @return
	 */
	public static <T> T decrypt(String data, String token, Boolean isEncrypt, Class<T> clazz) {
		if(!isEncrypt) {
			return parseObject(data, clazz);	
		}
		String plainData = DesUtil.strDec(data, token, null, null);
		String decoderData = new String(Base64.decodeBase64(plainData), Charset.forName("UTF-8"));
		return parseObject(decoderData, clazz);		
	}
	
	/**
	 * 解密数组
	 * @param data
	 * @param token
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> decryptList(String data, String token, Boolean isEncrypt, Class<T> clazz) {
		if(!isEncrypt) {
			return parseArray(data, clazz);	
		}
		String plainData = DesUtil.strDec(data, token, null, null);
		String decoderData = new String(Base64.decodeBase64(plainData), Charset.forName("UTF-8"));
		return parseArray(decoderData, clazz);		
	}
	
	/**
	 * 转为json字符串
	 * @param data
	 * @param keys
	 * @return
	 */
	public static <T> String toJSONString(T data, String... keys) {
		return JSON.toJSONString(data, getInstance(), filter(keys),
				SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);
	}
	
	/**
	 * 转为json字符串
	 * @param data
	 * @param keys
	 * @return
	 */
	public static <T> String toJSONString(List<T> data, String... keys) {
		return JSON.toJSONString(data, getInstance(), filter(keys),
				SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);
	}
	
	/**
	 * json转换为对象
	 * @param data
	 * @param clazz
	 * @return
	 */
	public static <T> T parseObject(String data, Class<T> clazz) {
		return JSON.parseObject(data, clazz);		
	}
	
	/**
	 * json转换为list
	 * @param data
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> parseArray(String data, Class<T> clazz) {
		return JSONArray.parseArray(data, clazz);			
	}
}
