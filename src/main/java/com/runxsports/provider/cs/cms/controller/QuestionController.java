package com.runxsports.provider.cs.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.runxsports.provider.cs.cms.common.exception.CmsErrorCodeEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsException;
import com.runxsports.provider.cs.cms.entity.Question;
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
    public RespData<QuestionVO> queryQuestion(@RequestBody QuestionBO questionBO){
        return success(questionService.queryQuestion(questionBO.getQuestionGroup()));
    }
	
	/**
	 * 根据问题类别查询问题
	 * @param questionGroup
	 * @return
	 */
	@PostMapping("/queryAll")
    public RespData<PageInfo<Question>> queryAllQuestion(@RequestBody QuestionBO questionBO){
        return success(questionService.queryAllQuestion(questionBO));
    }
	
	/**
	 * 批量导入问题
	 * @param file
	 * @return
	 */
	@PostMapping("/import/{questionGroup}")
    public RespData<QuestionVO> importExcel(MultipartFile file, @PathVariable("questionGroup") String questionGroup){
		if(file == null) {
			throw new CmsException(CmsErrorCodeEnum.CMS9083009);
		}
		questionService.importQuestion(file, questionGroup);
        return success();
    }
		
}
