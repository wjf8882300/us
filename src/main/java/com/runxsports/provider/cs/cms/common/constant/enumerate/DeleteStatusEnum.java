package com.runxsports.provider.cs.cms.common.constant.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 实体类枚举
 * @author wangjf
 *
 */
public enum DeleteStatusEnum {

	/**
	 * 启用状态
	 */
	ENABLED(0, "未删除"),
	UNABLED(1, "已删除");

	@Getter
	private Integer key;
	@Getter
	private String value;
	
	DeleteStatusEnum(Integer key, String value) {
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
