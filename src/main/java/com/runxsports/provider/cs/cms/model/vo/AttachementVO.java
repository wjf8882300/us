package com.runxsports.provider.cs.cms.model.vo;

import java.io.Serializable;


import lombok.Data;

@Data
public class AttachementVO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 上传路径
	 */
	private String attachementPath;
	
    /**
     * 用户编号
     */
    private String userId;
    
    /**
     * 用户名称
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
    
    /**
     * 附件名称
     */
    private String attachementName;

}
