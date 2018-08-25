package com.runxsports.provider.cs.cms.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.runxsports.provider.cs.cms.entity.UserAnswer;
import com.runxsports.provider.cs.cms.model.RespData;
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
	@GetMapping("/export")
	@ResponseBody
    public void export(@RequestParam String group,HttpServletResponse response){
		this.userAnswerService.export(group, response);
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
