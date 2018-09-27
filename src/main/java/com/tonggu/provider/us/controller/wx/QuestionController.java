package com.tonggu.provider.us.controller.wx;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.tonggu.provider.us.common.config.ParamConfig;
import com.tonggu.provider.us.common.util.CryptUtil;
import com.tonggu.provider.us.controller.BaseController;
import com.tonggu.provider.us.model.RespData;
import com.tonggu.provider.us.model.bo.QuestionBO;
import com.tonggu.provider.us.model.vo.AccessTokenVo;
import com.tonggu.provider.us.model.vo.QuestionVO;
import com.tonggu.provider.us.service.QuestionService;
import com.tonggu.provider.us.service.WeChatService;

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
