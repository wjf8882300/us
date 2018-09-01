package com.runxsports.provider.cs.cms.common.constant.enumerate;

import lombok.Getter;

/**
 * 实体类枚举
 * @author wangjf
 *
 */
public enum StatusEnum {

	/**
	 * 启用状态
	 */
	ENABLED(0, "启用"),
	UNABLED(1, "禁用");

	@Getter
	private Integer key;
	@Getter
	private String value;
	
	StatusEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public Integer getInteger(){
		return this.key;
	}
	public String getString(){
		return this.key.toString();
	}
}
