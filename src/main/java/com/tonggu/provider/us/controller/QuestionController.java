package com.tonggu.provider.us.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.tonggu.provider.us.common.exception.CmsErrorCodeEnum;
import com.tonggu.provider.us.common.exception.CmsException;
import com.tonggu.provider.us.entity.Question;
import com.tonggu.provider.us.model.RespData;
import com.tonggu.provider.us.model.bo.QuestionBO;
import com.tonggu.provider.us.model.vo.QuestionVO;
import com.tonggu.provider.us.service.QuestionService;

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
