package com.tonggu.provider.cs.cms.common.constant.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 问题枚举
 * @author wangjf
 * @create 2018年08月01日
 */
public interface UserEnum {

	@AllArgsConstructor
	enum Type{
		/**
		 * 类别
		 */
		STUDENT(0, "学生"),
		LEADER(1, "支部书记"),
		TEACHER(2, "辅导员");

		@Getter
		private Integer key;
		@Getter
		private String value;

		public Integer getInteger(){
			return this.key;
		}
		public String getString(){
			return this.key.toString();
		}
		
		public static Type getEnum(Integer key) {
	        for (Type tmp : Type.values()) {
	            if (tmp.getKey().equals(key)) {
	                return tmp;
	            }
	        }
	        return null;
	    }
	}

}
