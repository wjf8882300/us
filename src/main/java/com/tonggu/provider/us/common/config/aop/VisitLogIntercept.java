package com.tonggu.provider.us.common.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tonggu.provider.us.common.util.JsonUtil;
import com.tonggu.provider.us.common.util.WebUtil;

/**
 * @desc 访问日志拦截器
 * @author wangjf
 * @since 2018年1月17日
 */
@Aspect
@Component
public class VisitLogIntercept {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/** 打印日志与响应时间 */
	@Around("within(com.tonggu.provider.us.controller..*Controller)")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		
		long curTime = System.currentTimeMillis();
		
		ServletRequestAttributes req = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		
		String method = pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName();
		
		logger.info("IP：{}，访问：{}，参数：{}", WebUtil.getHost(req.getRequest()), method, JsonUtil.toJson(pjp.getArgs()));
		
		Object result = pjp.proceed();
		
		logger.info("方法：{}，消耗时间：{}ms", method, System.currentTimeMillis() - curTime);
		
		return result;
		
	}
	
}
