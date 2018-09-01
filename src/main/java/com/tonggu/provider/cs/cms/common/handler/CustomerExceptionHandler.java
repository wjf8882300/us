package com.tonggu.provider.cs.cms.common.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tonggu.provider.cs.cms.common.constant.enumerate.GlobalCallbackEnum;
import com.tonggu.provider.cs.cms.common.exception.CmsException;
import com.tonggu.provider.cs.cms.common.util.WebUtil;
import com.tonggu.provider.cs.cms.model.RespData;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常处理
 *
 * @author wangjf
 * @date 2018-04-01
 */
@RestControllerAdvice
@Slf4j(topic = "error")
public class CustomerExceptionHandler {

	@ExceptionHandler(value = CmsException.class)
	public RespData<String> processMatchException(CmsException ex) {
		log.warn("processMatchException {}", ex);
		return WebUtil.callback(ex);
	}

	@ExceptionHandler(value = Exception.class)
	public RespData<String> processException(Exception ex) {
		log.warn("processException {}", ex);
		return WebUtil.callback(GlobalCallbackEnum.SERVICE_ERROR);
	}

}
