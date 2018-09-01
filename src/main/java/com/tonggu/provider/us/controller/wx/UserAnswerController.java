package com.tonggu.provider.us.controller.wx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tonggu.provider.us.common.config.ParamConfig;
import com.tonggu.provider.us.common.util.CryptUtil;
import com.tonggu.provider.us.controller.BaseController;
import com.tonggu.provider.us.entity.UserAnswer;
import com.tonggu.provider.us.model.RespData;
import com.tonggu.provider.us.model.bo.AnswerBO;
import com.tonggu.provider.us.model.vo.AccessTokenVo;
import com.tonggu.provider.us.model.vo.UserAnswerVO;
import com.tonggu.provider.us.service.UserAnswerService;
import com.tonggu.provider.us.service.WeChatService;

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
