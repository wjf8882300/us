package com.runxsports.provider.cs.cms.model.bo;

import java.util.List;

import com.runxsports.provider.cs.cms.entity.UserAnswer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserAnswerBO extends UserBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户回答问题列表（微信前端提交时使用）
	 */
	List<UserAnswer> resultList;
	
	/**
	 * 目标用户ID(为空时表示自己)
	 */
	private Long destUserId;
}
