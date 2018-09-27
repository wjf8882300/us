package com.tonggu.provider.us.common.util;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * Ip地址工具类
 */
@Slf4j
public class IpUtil {

	private static final String X_FORWARDED_FOR = "x-forwarded-for";
	private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
	private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

	private static final String LOCALHOST_IP = "127.0.0.1";
	private static final String LOCALHOST_IP_16 = "0:0:0:0:0:0:0:1";
	private static final String UNKNOWN = "unknown";
	private static final String COMMA = ",";
	private static final int MAX_IP_LENGTH = 15;

	/**
	 * @Title: getRemoteAddr
	 * @Description: Ip地址获取
	 * @author Jeff.hou
	 * @date 2018/4/19 11:16
	 * @param
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader(X_FORWARDED_FOR);
		if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader(PROXY_CLIENT_IP);
		}
		if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader(WL_PROXY_CLIENT_IP);
		}
		if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (LOCALHOST_IP.equals(ipAddress) || LOCALHOST_IP_16.equals(ipAddress)) {
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					log.error("IP地址获取, 异常={}", e.getMessage(), e);
				}
				assert inet != null;
				ipAddress = inet.getHostAddress();
			}
		}
		if (ipAddress != null && ipAddress.length() > MAX_IP_LENGTH && ipAddress.indexOf(COMMA) > 0) {
			ipAddress = ipAddress.substring(0, ipAddress.indexOf(COMMA));
		}
		return ipAddress;
	}

	/**  
	 * 取本机地址
	 * @author: wangjf  
	 * @createTime: 2018年6月1日 上午11:16:23  
	 * @history:  
	 * @return String  
	 */
	public static final String getLocalAddress() {
		String ip = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			// 获取本机ip
			ip = addr.getHostAddress().toString();
		} catch (Exception e) {
			ip = "127.0.0.1";
		}
		return ip;
	}
	
	/**  
	 * 取本机名称
	 * @author: wangjf  
	 * @createTime: 2018年6月1日 上午11:16:23  
	 * @history:  
	 * @return String  
	 */
	public static final String getLocalHostName() {
		String hostName = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			// 获取本机ip
			hostName = addr.getHostName();
		} catch (Exception e) {
			hostName = "localhost";
		}
		return hostName;
	}

}
