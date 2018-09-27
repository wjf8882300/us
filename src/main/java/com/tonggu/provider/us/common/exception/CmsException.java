package com.tonggu.provider.us.common.exception;

import com.tonggu.provider.us.common.constant.enumerate.GlobalCallbackEnum;

/**
 * 自定义异常
 * @author wangjf
 *
 */
public class CmsException extends BaseException {

	private static final long serialVersionUID = 1L;

	public CmsException(CmsErrorCodeEnum cmsErrorCodeEnum) {
		super(cmsErrorCodeEnum);
	}
	
	public CmsException(GlobalCallbackEnum globalCallbackEnum) {
		super(globalCallbackEnum);
	}

	public CmsException() {

	}
}
