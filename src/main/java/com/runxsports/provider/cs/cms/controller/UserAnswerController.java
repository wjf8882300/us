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

import com.github.pagehelper.PageInfo;
import com.runxsports.provider.cs.cms.common.constant.enumerate.UserEnum;
import com.runxsports.provider.cs.cms.entity.UserAnswer;
import com.runxsports.provider.cs.cms.model.RespData;
import com.runxsports.provider.cs.cms.model.bo.UserAnswerBO;
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
	@GetMapping("/exportAllStudent")
	@ResponseBody
    public void exportAllStudent(HttpServletResponse response){
		this.userAnswerService.export(UserEnum.Type.STUDENT.getString(), response);
    }
	
	/**
	 * 查询答题情况
	 * @param file
	 * @return
	 */
	@GetMapping("/exportAllLeader")
	@ResponseBody
    public void exportAllLeader(HttpServletResponse response){
		this.userAnswerService.export(UserEnum.Type.LEADER.getString(), response);
    }
	
	/**
	 * 查询答题情况
	 * @param file
	 * @return
	 */
	@GetMapping("/exportAllTeacher")
	@ResponseBody
    public void exportAllTeacher(HttpServletResponse response){
		this.userAnswerService.export(UserEnum.Type.TEACHER.getString(), response);
    }
		
		
	/**
	 * 查询答题情况
	 * @param file
	 * @return
	 */
	@PostMapping("/query")
    public RespData<UserAnswerVO> query(@RequestBody UserAnswer answer){
		//this.userAnswerService.query(answer);
		return success();
    }
	
	/**
	 * 查询学生评分
	 * @param userAnswerBO
	 * @return
	 */
	@PostMapping("/queryAllStudent")
    public RespData<PageInfo<UserAnswerVO>> queryAllStudent(@RequestBody UserAnswerBO userAnswerBO){
		return success(userAnswerService.queryAll(userAnswerBO));
    }
	
	/**
	 * 查询支部书记评分
	 * @param userAnswerBO
	 * @return
	 */
	@PostMapping("/queryAllLeader")
    public RespData<PageInfo<UserAnswerVO>> queryAllLeader(@RequestBody UserAnswerBO userAnswerBO){
		return success(userAnswerService.queryAll(userAnswerBO));
    }
	
	/**
	 * 查询辅导员评分
	 * @param userAnswerBO
	 * @return
	 */
	@PostMapping("/queryAllTeacher")
    public RespData<PageInfo<UserAnswerVO>> queryAllTeacher(@RequestBody UserAnswerBO userAnswerBO){
		return success(userAnswerService.queryAll(userAnswerBO));
    }
	
	/**
	 * 查询学生未自评分
	 * @param userAnswerBO
	 * @return
	 */
	@PostMapping("/queryNotScoreStudent")
    public RespData<PageInfo<UserAnswerVO>> queryNotScoreStudent(@RequestBody UserAnswerBO userAnswerBO){
		return success(userAnswerService.queryNotScore(userAnswerBO));
    }
	
	/**
	 * 查询支部书记未给学生评分信息
	 * @param userAnswerBO
	 * @return
	 */
	@PostMapping("/queryNotScoreLeader")
    public RespData<PageInfo<UserAnswerVO>> queryNotScoreLeader(@RequestBody UserAnswerBO userAnswerBO){
		return success(userAnswerService.queryNotScore(userAnswerBO));
    }
	
	/**
	 * 查询辅导员未给学生评分信息
	 * @param userAnswerBO
	 * @return
	 */
	@PostMapping("/queryNotScoreTeacher")
    public RespData<PageInfo<UserAnswerVO>> queryNotScoreTeacher(@RequestBody UserAnswerBO userAnswerBO){
		return success(userAnswerService.queryNotScore(userAnswerBO));
    }
}
