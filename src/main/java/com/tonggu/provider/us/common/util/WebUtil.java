package com.tonggu.provider.us.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tonggu.provider.us.common.constant.CommonConstant;
import com.tonggu.provider.us.common.constant.enumerate.BaseCallbackEnum;
import com.tonggu.provider.us.model.RespData;

/**
 * Web层辅助工具类
 * @author wangjf
 * @create 2018年7月12日
 */
public class WebUtil {

	/** 获取当前ServletRequestAttributes */
	public static ServletRequestAttributes getCurServletRequestAttributes() {
		return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	}

	/** 获取当前HttpServletRequest */
	public static HttpServletRequest getCurRequest() {
		return getCurServletRequestAttributes().getRequest();
	}

	/** 获取当前HttpServletResponse */
	public static HttpServletResponse getCurResponse() {
		return getCurServletRequestAttributes().getResponse();
	}

	/** 获取当前客户端IP */
	public static final String getCurHost() {
		return getHost(getCurRequest());
	}

	/** 公共响应,无返回值 */
	public static <T> RespData<T> callback(BaseCallbackEnum BaseCallbackEnum) {
		return callback(BaseCallbackEnum, null);
	}

	/** 公共响应,有返回值 */
	public static <T> RespData<T> callback(BaseCallbackEnum BaseCallbackEnum, T data) {
		return callback(BaseCallbackEnum.getValue(), BaseCallbackEnum.getIntro(), data);
	}

	/** 公共响应,有返回值 */
	public static <T> RespData<T> callback(Integer code, String message, T data) {
		return new RespData<T>(code, message, data);
	}

	/** 获取客户端IP */
	public static final String getHost(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (CommonConstant.LOCALHOST.equals(ip)) {
			InetAddress inet = null;
			try {
				inet = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			ip = inet.getHostAddress();
		}
		if (ip != null && ip.length() > 15) {
			if (ip.indexOf(CommonConstant.SPLIT_COMMA) > 0) {
				ip = ip.substring(0, ip.indexOf(CommonConstant.SPLIT_COMMA));
			}
		}
		return ip;
	}

}
