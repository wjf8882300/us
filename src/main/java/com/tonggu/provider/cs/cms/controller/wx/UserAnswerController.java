package com.tonggu.provider.cs.cms.controller.wx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tonggu.provider.cs.cms.common.config.ParamConfig;
import com.tonggu.provider.cs.cms.common.util.CryptUtil;
import com.tonggu.provider.cs.cms.controller.BaseController;
import com.tonggu.provider.cs.cms.entity.UserAnswer;
import com.tonggu.provider.cs.cms.model.RespData;
import com.tonggu.provider.cs.cms.model.bo.AnswerBO;
import com.tonggu.provider.cs.cms.model.vo.AccessTokenVo;
import com.tonggu.provider.cs.cms.model.vo.UserAnswerVO;
import com.tonggu.provider.cs.cms.service.UserAnswerService;
import com.tonggu.provider.cs.cms.service.WeChatService;

/**
 * 用户答题管理
 * @author duanfen
 *
 */
@RestController("WxUserAnswerController")
@RequestMapping("/wx/answer")
public class UserAnswerController  extends BaseController {
	
	@Autowired
	UserAnswerService userAnswerService;

	@Autowired
	private WeChatService weChatService;
	
	/**
	 * 新增答题
	 * @param file
	 * @return
	 */
	@PostMapping("/save")
    public RespData<UserAnswerVO> save(@RequestBody AnswerBO answerBO){
		AccessTokenVo token = weChatService.getCacheAccessToken(answerBO.getToken());
		List<UserAnswer> resultList = CryptUtil.decryptList(answerBO.getResult(), answerBO.getToken(), ParamConfig.isEncrypt(token.getAlgorithm()), UserAnswer.class);
		
		this.userAnswerService.save(resultList, token.getUserId());
		return success();
    }

	/**
	 * 新增党支部或辅导员答题
	 * @param file
	 * @return
	 */
	@PostMapping("/saveTeam")
    public RespData<UserAnswerVO> saveTeam(@RequestBody AnswerBO answerBO){
		AccessTokenVo token = weChatService.getCacheAccessToken(answerBO.getToken());
		List<UserAnswer> resultList = CryptUtil.decryptList(answerBO.getResult(), answerBO.getToken(), ParamConfig.isEncrypt(token.getAlgorithm()), UserAnswer.class);
		this.userAnswerService.saveAnswer(resultList, token.getUserId());
		return success();
    }
}
