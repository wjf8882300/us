package com.tonggu.provider.cs.cms.common.config;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tonggu.provider.cs.cms.common.constant.enumerate.ParamEnum;
import com.tonggu.provider.cs.cms.common.constant.enumerate.StatusEnum;
import com.tonggu.provider.cs.cms.entity.Param;
import com.tonggu.provider.cs.cms.service.ParamService;

@Component
public class ParamConfig implements InitializingBean {

	@Autowired
	ParamService paramService;
	
	private static List<Param> list = null;
	
	@Override
	public void afterPropertiesSet() throws Exception {
			
		list = paramService.queryAll();
	}
	
	public static List<Param> getList() {
		return list;
	}
	
	public static Param getKey(String key) {
		if(StringUtils.isBlank(key)) {
			return null;
		}
		for(Param p : list) {
			if(p.getParameterType().equals(key)){
				return p;
			}
		}
		return null;
	}
	
	public static Integer getEncrypt() {
		Param param = getKey(ParamEnum.Name.ENCRYPT.getKey());
		if(param != null && StatusEnum.UNABLED.getString().equals(param.getParameterValue())) {
			return StatusEnum.UNABLED.getInteger();
		} else {
			return StatusEnum.ENABLED.getInteger();
		}
	}

	public static Boolean isEncrypt(Integer key) {
		if(key == null) return true;
		
		return StatusEnum.ENABLED.getInteger().equals(key);
	}

}
