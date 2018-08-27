 package com.runxsports.provider.cs.cms.service.impl;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.runxsports.provider.cs.cms.common.config.properties.UsProperties;
import com.runxsports.provider.cs.cms.common.constant.RedisConstant;
import com.runxsports.provider.cs.cms.common.constant.TencentConstant;
import com.runxsports.provider.cs.cms.common.constant.enumerate.GlobalCallbackEnum;
import com.runxsports.provider.cs.cms.common.constant.enumerate.TencentUrlEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsErrorCodeEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsException;
import com.runxsports.provider.cs.cms.common.util.MD5Util;
import com.runxsports.provider.cs.cms.common.util.RedisUtil;
import com.runxsports.provider.cs.cms.common.util.ValidateUtils;
import com.runxsports.provider.cs.cms.model.vo.AccessTokenVo;
import com.runxsports.provider.cs.cms.service.WeChatService;

import lombok.extern.slf4j.Slf4j;

/**  
 * 微信接口实现
 * 
 * @desc: provider-ycd-wx_provider-ycd-wx  
 * @author: wangjf  
 * @createTime: 2018年5月23日 下午2:06:06  
 * @history:  
 * @version: v1.0    
 */
@Service
@Slf4j
public class WeChatServiceImpl implements WeChatService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RedisUtil redisClient;
	
	@Autowired
	private UsProperties usProperties;

	@Override
	public AccessTokenVo getAccessToken(String code) {
		ValidateUtils.notBlank(code, GlobalCallbackEnum.PARAMETER_ILLEGAL);
        String accessTokenUrl = TencentUrlEnum.ACCESS_TOKER_URL.url();
        Map<String, String> uriVariables = new HashMap<>(3);
        uriVariables.put(TencentConstant.APP_ID, usProperties.getWechat().getAppId());
        uriVariables.put(TencentConstant.SECRET, usProperties.getWechat().getAppSecret());
        uriVariables.put(TencentConstant.CODE, code);
        ResponseEntity<AccessTokenVo> responseEntity = null;
        try {
        	responseEntity = restTemplate.getForEntity(accessTokenUrl, AccessTokenVo.class ,uriVariables);
        	AccessTokenVo accessToken = responseEntity.getBody();
        	if(StringUtils.isEmpty(accessToken.getOpenId())
        			|| StringUtils.isEmpty(accessToken.getSessionKey())) {
        		log.error("get access_token error, openId or sessionKey is null:" + code);
            	throw new CmsException(CmsErrorCodeEnum.CMS9083014);
        	}
        } catch(CmsException e) {
        	throw e;
        } catch(Exception e) {
        	log.error("get access_token error:" + code);
        	throw new CmsException(CmsErrorCodeEnum.CMS9083014);
        }
        return responseEntity.getBody();
	}
	
	@Override
	public String cacheAccessToken(AccessTokenVo accessToken) {
		// 把用户的ID进行转储
		String userToken = MD5Util.MD5Encode(accessToken.getUserId().toString());
		String value = JSON.toJSONString(accessToken);
		redisClient.setKey(RedisConstant.PREFIX_USER + userToken, value, RedisConstant.TIME_OUT, RedisConstant.TIME_UNIT);
		return Base64.encodeBase64String(userToken.getBytes(Charset.forName("UTF-8")));
	}

	@Override
	public AccessTokenVo getCacheAccessToken(String sessionKey) {
		if(StringUtils.isEmpty(sessionKey)) {
			log.error("用户sessionKey不能为空");
			throw new CmsException(CmsErrorCodeEnum.CMS9083015);
		}

		// 验证客户
		String userToken = new String(Base64.decodeBase64(sessionKey), Charset.forName("UTF-8"));
		String userValue = redisClient.getKey(RedisConstant.PREFIX_USER + userToken);
		if(StringUtils.isEmpty(userValue)) {
			log.error("用户未登录或者登录已过期|sessionKey expired|{}",sessionKey);
			throw new CmsException(CmsErrorCodeEnum.CMS9083015);
		}
		AccessTokenVo accessToken = JSON.parseObject(userValue, AccessTokenVo.class);
		// 每访问一次，失效时间延长一天
		redisClient.expire(RedisConstant.PREFIX_USER + userToken, RedisConstant.TIME_OUT, RedisConstant.TIME_UNIT);
		return accessToken;
	}

	@Override
	public void removeAccessToken(String sessionKey) {
		String userToken = new String(Base64.decodeBase64(sessionKey), Charset.forName("UTF-8"));
		String userValue = redisClient.getKey(RedisConstant.PREFIX_USER + userToken);
		if(!StringUtils.isEmpty(userValue)) {
			redisClient.deleteKey(RedisConstant.PREFIX_USER + userToken);
		}		
	}

}
