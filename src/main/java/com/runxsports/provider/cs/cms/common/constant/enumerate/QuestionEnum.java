package com.runxsports.provider.cs.cms.common.constant.enumerate;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 问题枚举
 * @author wangjf
 * @create 2018年08月01日
 */
public interface QuestionEnum {

	@AllArgsConstructor
	enum Group{
		/**
		 * 类别
		 */
		STUDENT(0, "学生自评题"),
		LEADER(1, "支部书记题"),
		TEACHER(2, "辅导员题");

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
		
		public static Group getEnum(Integer key) {
	        for (Group tmp : Group.values()) {
	            if (tmp.getKey().equals(key)) {
	                return tmp;
	            }
	        }
	        return null;
	    }
	}
	
	@AllArgsConstructor
	enum Type{
		/**
		 * 类型
		 */
		SELECT(0, "选择题"),
		FILL(1, "填空题"),;

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
	}
}
