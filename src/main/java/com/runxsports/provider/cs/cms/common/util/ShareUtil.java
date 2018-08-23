/** 
 * @(#)ShareUtil.java 1.0.0 2014年10月21日 上午11:54:05  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */ 

package com.runxsports.provider.cs.cms.common.util;

import static java.beans.Introspector.IGNORE_ALL_BEANINFO;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;


/**  
 * 共享工具类 
 * @desc: provider-ycd-cms_provider-ycd-cms  
 * @author: wangjf  
 * @createTime: 2018年5月31日 下午2:30:24  
 * @history:  
 * @version: v1.0    
 */
public class ShareUtil {
	public static final Logger logger = LoggerFactory.getLogger(ShareUtil.class);
	/** 缓存反射元数据 */
	static final private Table<String, String, Method> META_DATA = HashBasedTable.create();
	
	/**
	 * 将JavaBean转换为map格式
	 * 
	 * @param bean
	 *            (要转换的JavaBean对象)
	 * @return map(fieldName, fieldValue)
	 */
	static final public <T> Map<String, String> toMap(T bean) {
		final Map<String, String> result = new HashMap<>();
		if (null == bean) {
			return result;
		}
			
		String beanName;
		Object val;
		try {
			if (META_DATA.containsRow(beanName = bean.getClass().getName())) {
				for (Map.Entry<String, Method> entry : META_DATA.row(beanName).entrySet()) {
					val = entry.getValue().invoke(bean);
					if(val != null) {
						result.put(entry.getKey(), val.toString());
					}	
				}
				return result;
			}
			PropertyDescriptor[] pds = Introspector.getBeanInfo(bean.getClass(), Object.class, IGNORE_ALL_BEANINFO).getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				val = pd.getReadMethod().invoke(bean);
				if(val != null) {
					result.put(pd.getName(),val.toString());
				}
				META_DATA.put(beanName, pd.getName(), pd.getReadMethod());
			}
		} catch (ReflectiveOperationException | IntrospectionException e) {
			logger.error(ShareUtil.class.getName()+e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return result;
	}
	
	/**  
	 * 将Map拼接为key1=value1&key2=value2...
	 * @author: wangjf  
	 * @createTime: 2018年5月31日 下午2:36:28  
	 * @history:  
	 * @param map
	 * @return String  
	 */
	static final public String urlString(Map<String, String> map) {
		StringBuilder strBuild = new StringBuilder();

		map.forEach((k,v) -> {
			strBuild.append(k).append("=").append(v).append("&");
		});

		return strBuild.substring(0, strBuild.length() - 1 );
	}
	
	/**  
	 * 把Map转为XML
	 * @author: wangjf  
	 * @createTime: 2018年5月31日 下午2:55:58  
	 * @history:  
	 * @param map
	 * @return String  
	 */
	static final public String xmlString(Map<String, String> map) {
		StringBuilder strBuild = new StringBuilder();		
		strBuild.append("<xml>\n");
		map.forEach((key,value) -> {
			if (StringUtils.isNotEmpty(value = map.get(key))) {
				strBuild.append("<").append(key).append(">")
						.append(value)
						.append("</").append(key).append(">")
						.append("\n");
			}
		});
		strBuild.append("</xml>");
		return strBuild.toString();
	}
}
