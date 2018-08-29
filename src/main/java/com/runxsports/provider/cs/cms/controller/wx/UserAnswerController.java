package com.runxsports.provider.cs.cms.controller.wx;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.runxsports.provider.cs.cms.common.constant.enumerate.UserEnum;
import com.runxsports.provider.cs.cms.common.util.CryptUtil;
import com.runxsports.provider.cs.cms.controller.BaseController;
import com.runxsports.provider.cs.cms.entity.UserAnswer;
import com.runxsports.provider.cs.cms.model.RespData;
import com.runxsports.provider.cs.cms.model.bo.AnswerBO;
import com.runxsports.provider.cs.cms.model.bo.UserAnswerBO;
import com.runxsports.provider.cs.cms.model.vo.AccessTokenVo;
import com.runxsports.provider.cs.cms.model.vo.UserAnswerVO;
import com.runxsports.provider.cs.cms.service.UserAnswerService;
import com.runxsports.provider.cs.cms.service.WeChatService;

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
		List<UserAnswer> resultList = CryptUtil.decryptList(answerBO.getResult(), answerBO.getToken(), UserAnswer.class);
		AccessTokenVo token = weChatService.getCacheAccessToken(answerBO.getToken());
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
		List<UserAnswer> resultList = CryptUtil.decryptList(answerBO.getResult(), answerBO.getToken(), UserAnswer.class);
		AccessTokenVo token = weChatService.getCacheAccessToken(answerBO.getToken());
		this.userAnswerService.saveAnswer(resultList, token.getUserId());
		return success();
    }
}
