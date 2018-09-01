package com.tonggu.provider.cs.cms.common.exception;

import com.tonggu.provider.cs.cms.common.constant.enumerate.BaseCallbackEnum;

/**
 * 基类异常
 * @author wangjf
 *
 */
public class BaseException extends RuntimeException implements BaseCallbackEnum {

	private static final long serialVersionUID = 1L;

	/** 错误码 */
	private Integer value;

	/** 说明 */
	private String intro;

	@Override
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(BaseCallbackEnum baseCallbackEnum) {
		this.value = baseCallbackEnum.getValue();
		this.intro = baseCallbackEnum.getIntro();
	}

	public BaseException(BaseCallbackEnum baseCallbackEnum, Throwable cause) {
		super(cause);
		this.value = baseCallbackEnum.getValue();
		this.intro = baseCallbackEnum.getIntro();
	}

	public BaseException(Integer value, String intro) {
		super();
		this.value = value;
		this.intro = intro;
	}

}
