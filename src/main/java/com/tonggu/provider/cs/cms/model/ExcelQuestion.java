package com.tonggu.provider.cs.cms.model;

import lombok.Data;

@Data
public class ExcelQuestion {
	
	/**
	 * 排序
	 */
	private Integer sort;

	 /**
     * 题目内容
     */
    private String questionContent;
    
    /**
	 * 考评分
	 */
	private Integer questionScore;

    /**
     * 描述
     */
    private String questionDesc;

}
