package com.runxsports.provider.cs.cms.controller.wx;


import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.runxsports.provider.cs.cms.common.util.DesUtil;
import com.runxsports.provider.cs.cms.controller.BaseController;
import com.runxsports.provider.cs.cms.model.RespData;
import com.runxsports.provider.cs.cms.model.bo.QuestionBO;
import com.runxsports.provider.cs.cms.model.vo.QuestionVO;
import com.runxsports.provider.cs.cms.service.QuestionService;

/**
 * 问卷问题管理
 * @author wangjf
 *
 */
@RestController
@RequestMapping("/question")
public class QuestionController extends BaseController {
	
	@Autowired
	QuestionService questionService;
	
	/**
	 * 根据问题类别查询问题
	 * @param questionGroup
	 * @return
	 */
	@PostMapping("/query")
    public RespData<String> queryQuestionForEncryt(@RequestBody QuestionBO questionBO){
		QuestionVO questionVO = questionService.queryQuestion(questionBO.getQuestionGroup());
		String questionJson = JSON.toJSONString(questionVO);
		String base64 = Base64.encodeBase64String(questionJson.getBytes(Charset.forName("UTF-8")));
        return success(DesUtil.strEnc(base64, questionBO.getToken(), null, null));
    }		
}
