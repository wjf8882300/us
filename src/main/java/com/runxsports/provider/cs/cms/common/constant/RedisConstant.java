package com.runxsports.provider.cs.cms.common.constant;

import java.util.concurrent.TimeUnit;

public class RedisConstant {

	/**
	 * redis题目前缀
	 */
	public static final String PRFIX_QUESTION = "us:question:";
	
	/**  
	 * 存储在redis中key的前缀
	 */
	public final static String PREFIX_USER = "us:user:";
	
	public static final Long TIME_OUT =1L;
    public static final TimeUnit TIME_UNIT =TimeUnit.DAYS;
}
