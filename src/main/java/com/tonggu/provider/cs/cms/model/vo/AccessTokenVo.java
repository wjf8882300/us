package com.tonggu.provider.cs.cms.model.vo;

import java.io.Serializable;

/**  
 * 微信token
 * @desc: provider-ycd-wx_provider-ycd-wx  
 * @author: wangjf  
 * @createTime: 2018年5月23日 下午2:22:09  
 * @history:  
 * @version: v1.0    
 */
public class AccessTokenVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 接口调用凭证
	 */
	private String sessionKey;
	/**
	 * 授权用户唯一标识
	 */
	private String openid;

	/**  
	 * 用户在开放平台的唯一标识符  
	 */
	private String unionid;
	
	/**  
	 * 用户ID  
	 */
	private Long userId;
	
	/**
	 * 用户类型
	 */
	private String userType;
	
	/**
	 * 加密算法，1-不启用/0-启用
	 */
	private Integer algorithm;
	
	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getOpenId() {
		return openid;
	}

	public void setOpenId(String openid) {
		this.openid = openid;
	}

	public String getUnionId() {
		return unionid;
	}

	public void setUnionId(String unionid) {
		this.unionid = unionid;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Integer getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Integer algorithm) {
		this.algorithm = algorithm;
	}
}
