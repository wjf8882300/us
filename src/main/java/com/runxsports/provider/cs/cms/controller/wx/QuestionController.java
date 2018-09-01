package com.runxsports.provider.cs.cms.controller.wx;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.runxsports.provider.cs.cms.common.config.ParamConfig;
import com.runxsports.provider.cs.cms.common.util.CryptUtil;
import com.runxsports.provider.cs.cms.controller.BaseController;
import com.runxsports.provider.cs.cms.model.RespData;
import com.runxsports.provider.cs.cms.model.bo.QuestionBO;
import com.runxsports.provider.cs.cms.model.vo.AccessTokenVo;
import com.runxsports.provider.cs.cms.model.vo.QuestionVO;
import com.runxsports.provider.cs.cms.service.QuestionService;
import com.runxsports.provider.cs.cms.service.WeChatService;

/**
 * 问卷问题管理
 * @author wangjf
 *
 */
@RestController("WxQuestionController")
@RequestMapping("/wx/question")
public class QuestionController extends BaseController {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	private WeChatService weChatService;
	
	/**
	 * 根据问题类别查询问题
	 * @param questionGroup
	 * @return
	 */
	@PostMapping("/query")
    public RespData<String> queryQuestionForEncryt(@RequestBody QuestionBO questionBO){
		AccessTokenVo token = weChatService.getCacheAccessToken(questionBO.getToken());
		questionBO.setQuestionGroup(token.getUserType());
		PageInfo<QuestionVO> questionList = questionService.queryQuestion(questionBO);
		return success(CryptUtil.encrypt(questionList, questionBO.getToken(), ParamConfig.isEncrypt(token.getAlgorithm())));        
    }		
}
