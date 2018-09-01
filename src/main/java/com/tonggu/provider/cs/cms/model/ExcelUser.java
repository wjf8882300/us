package com.tonggu.provider.cs.cms.model;

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
     * 支部书记学号
     */
    private String teamLeaderNo;

    /**
     * 辅导员
     */
    private String teacher;
    
    /**
     * 辅导员工号
     */
    private String teacherNo;
}
