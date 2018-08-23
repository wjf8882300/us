package com.runxsports.provider.cs.cms.model;

import lombok.Data;

@Data
public class ExcelUser {

	/**
	 * 姓名
	 */
	private String userName;
	
	 /**
     * 班级
     */
    private String className;

    /**
     * 学号
     */
    private String userNo;

    /**
     * 所在支部
     */
    private String teamName;

    /**
     * 支部书记
     */
    private String teamLeader;

    /**
     * 辅导员
     */
    private String teacher;
}
