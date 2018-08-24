package com.runxsports.provider.cs.cms.model.bo;

import java.io.Serializable;

import lombok.Data;

@Data
public class BaseBO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 分页起始页（从0开始）
	 */
	private int start;
	
	/**
	 * 分页条数
	 */
	private int length;

}
