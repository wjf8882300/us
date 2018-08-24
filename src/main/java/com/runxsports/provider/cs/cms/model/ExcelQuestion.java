package com.runxsports.provider.cs.cms.model;

import lombok.Data;

@Data
public class ExcelQuestion {

	/**
	 * 题目类别:0-学生题/1-支部题/2-指导员题
	 */
	private String questionGroup;
	
	 /**
     * 题目内容
     */
    private String questionContent;

    /**
     * 排序
     */
    private Integer questionSort;

}
