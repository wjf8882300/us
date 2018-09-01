package com.tonggu.provider.cs.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tonggu.provider.cs.cms.common.constant.enumerate.BaseCallbackEnum;
import com.tonggu.provider.cs.cms.common.constant.enumerate.GlobalCallbackEnum;
import com.tonggu.provider.cs.cms.common.util.WebUtil;
import com.tonggu.provider.cs.cms.model.RespData;

/**
 * 基类控制器
 * @author wangjf
 * @create 2018年7月12日
 */
public class BaseController {

	/** 获取当前HttpServletRequest */
	public final HttpServletRequest getCurRequest() {
		return WebUtil.getCurRequest();
	}

	/** 获取当前HttpServletResponse */
	public final HttpServletResponse getCurResponse() {
		return WebUtil.getCurResponse();
	}

	/** 默认成功，无数据 */
	public final <T> RespData<T> success() {
		return WebUtil.callback(GlobalCallbackEnum.SUCCESS);
	}

	/** 默认成功，有数据 */
	public final <T> RespData<T> success(T data) {
		return WebUtil.callback(GlobalCallbackEnum.SUCCESS, data);
	}

	/** 默认失败，系统错误 */
	public final <T> RespData<T> failure() {
		return WebUtil.callback(GlobalCallbackEnum.SERVICE_ERROR);
	}

	/** 自定义失败错误 */
	public final <T> RespData<T> failure(T data) {
		return WebUtil.callback(GlobalCallbackEnum.SERVICE_ERROR, data);
	}

	/** 自定义失败错误 */
	public final RespData<Void> failure(BaseCallbackEnum callbackEnum) {
		return WebUtil.callback(callbackEnum);
	}

	/** 自定义响应 */
	public final <T> RespData<T> callback(Integer code, String message, T data) {
		return WebUtil.callback(code, message, data);
	}

	/**
	 * Post result wrapper.
	 * @param result
	 * @param <E>
	 * @return
	 */
	protected final <E> RespData<E> postResult(E result) {
		boolean flag = isFlag(result);
		if (flag) {
			return WebUtil.callback(GlobalCallbackEnum.SUCCESS, result);
		} else {
			return WebUtil.callback(GlobalCallbackEnum.SERVICE_ERROR, result);
		}
	}

	/**
	 * post result wrapper.
	 * @param result
	 * @param errorMsg
	 * @param <E>
	 * @return
	 */
	protected final <E> RespData<E> postResult(E result, String errorMsg) {
		boolean flag = isFlag(result);
		if (flag) {
			return WebUtil.callback(GlobalCallbackEnum.SUCCESS, result);
		} else {
			return WebUtil.callback(GlobalCallbackEnum.SERVICE_ERROR, result);
		}
	}

	private boolean isFlag(Object result) {
		boolean flag = false;
		if (result instanceof Integer) {
			flag = (Integer) result > 0;
		}
		if (result instanceof Boolean) {
			flag = (Boolean) result;
		}
		return flag;
	}
}
