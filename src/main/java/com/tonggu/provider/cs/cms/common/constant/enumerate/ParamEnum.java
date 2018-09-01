package com.tonggu.provider.cs.cms.common.constant.enumerate;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 参数枚举
 * @author wangjf
 * @create 2018年08月01日
 */
public interface ParamEnum {

	@AllArgsConstructor
	enum Name{
		/**
		 * 类别
		 */
		ENCRYPT("us:param:encrypt", "是否开启加密");

		@Getter
		private String key;
		@Getter
		private String value;

		public String getString(){
			return this.key;
		}
		
		public static Name getEnum(String key) {
	        for (Name tmp : Name.values()) {
	            if (tmp.getKey().equals(key)) {
	                return tmp;
	            }
	        }
	        return null;
	    }
	}

}
