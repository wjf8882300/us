package com.runxsports.provider.cs.cms.common.util;

import java.util.Hashtable;

import com.runxsports.provider.cs.cms.common.constant.CommonConstant;
import com.runxsports.provider.cs.cms.entity.User;

/**
 * 缓存用户信息
 * @author duanfen
 *
 */
public class CacheUtil {

	private static  Hashtable<String, User> cache = new Hashtable<String, User>();
	
	/**
	 * 缓存一个用户
	 * 
	 * @param user
	 */
	public static void put(User user){
		cache.clear();
		cache.put( CommonConstant.USER_INFO, user );
	}
	
	/**
	 *获取缓存的用户
	 * 
	 * @param user
	 */
	public static User get(){
		return cache.get(CommonConstant.USER_INFO);
	}
	
}
