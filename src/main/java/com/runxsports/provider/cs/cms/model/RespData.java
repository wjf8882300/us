package com.runxsports.provider.cs.cms.model;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * 响应数据
 * @author wangjf
 * @create 2018年7月12日
 */
public class RespData<T> {

	/** 响应码 */
	private Integer code;

	/** 响应消息 */
	private String message;

	/** 响应数据 */
	private T data;
	
	private List<?> rows;
	
	private Long total;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RespData() {
		super();
	}

	public RespData(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
		
		if(data != null && data instanceof PageInfo) {
			PageInfo<?> p = (PageInfo<?>)data;
			this.rows = p.getList();
			this.total = p.getTotal();
		}
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		total = total;
	}
}
