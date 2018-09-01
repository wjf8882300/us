 package com.tonggu.provider.cs.cms.service;

import com.tonggu.provider.cs.cms.model.vo.AccessTokenVo;

/**  
 * 微信接口服务
 * 
 * @desc: provider-ycd-wx_provider-ycd-wx  
 * @author: wangjf  
 * @createTime: 2018年5月23日 下午2:04:11  
 * @history:  
 * @version: v1.0    
 */
public interface WeChatService {

	 /**  
	 * 获取sessionKey等
	 * @author: wangjf  
	 * @createTime: 2018年5月23日 下午2:05:17  
	 * @history:  
	 * @param code
	 * @return AccessTokenVo  
	 */
	AccessTokenVo getAccessToken(String code);
	
	/**  
	 * 缓存用户token
	 * @author: wangjf  
	 * @createTime: 2018年5月23日 下午7:28:13  
	 * @history:  
	 * @param accessToken
	 * @return String  
	 */
	String cacheAccessToken(AccessTokenVo accessToken);
	
	/**  
	 * 从缓存中获取当前用户token
	 * @author: wangjf  
	 * @createTime: 2018年5月23日 下午7:25:07  
	 * @history:  
	 * @param sessionKey
	 * @return AccessTokenVo  
	 */
	AccessTokenVo getCacheAccessToken(String sessionKey);
	
	/**  
	 * 移除用户token
	 * @author: wangjf  
	 * @createTime: 2018年6月29日 下午4:19:55  
	 * @history:  
	 * @param sessionKey
	 */
	void removeAccessToken(String sessionKey);
}
