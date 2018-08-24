package com.runxsports.provider.cs.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.runxsports.provider.cs.cms.common.exception.CmsErrorCodeEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsException;
import com.runxsports.provider.cs.cms.entity.UserAnswer;
import com.runxsports.provider.cs.cms.model.RespData;
import com.runxsports.provider.cs.cms.model.vo.QuestionVO;
import com.runxsports.provider.cs.cms.model.vo.UserAnswerVO;
import com.runxsports.provider.cs.cms.service.UserAnswerService;

/**
 * 用户答题管理
 * @author duanfen
 *
 */
@RestController
@RequestMapping("/answer")
public class UserAnswerController  extends BaseController {
	
	@Autowired
	UserAnswerService userAnswerService;
	
	/**
	 * 新增答题
	 * @param file
	 * @return
	 */
	@PostMapping("/save")
    public RespData<UserAnswerVO> save(@RequestBody UserAnswer answer){
		this.userAnswerService.save(answer);
		return success();
    }
		
	/**
	 * 查询答题情况
	 * @param file
	 * @return
	 */
	@PostMapping("/query")
    public RespData<UserAnswerVO> query(@RequestBody UserAnswer answer){
		this.userAnswerService.save(answer);
		return success();
    }
		
}
