package com.runxsports.provider.cs.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.runxsports.provider.cs.cms.common.exception.CmsErrorCodeEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsException;
import com.runxsports.provider.cs.cms.model.RespData;
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
    public RespData<QuestionVO> queryQuestion(@RequestParam String questionGroup){
        return success(questionService.queryQuestion(questionGroup));
    }
	
	/**
	 * 批量导入问题
	 * @param file
	 * @return
	 */
	@PostMapping("/import")
    public RespData<QuestionVO> importExcel(MultipartFile file){
		if(file == null) {
			throw new CmsException(CmsErrorCodeEnum.CMS9083009);
		}
		questionService.importQuestion(file);
        return success();
    }
		
}
