package com.runxsports.provider.cs.cms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.runxsports.provider.cs.cms.entity.UserAttachment;
import com.runxsports.provider.cs.cms.model.RespData;
import com.runxsports.provider.cs.cms.model.bo.AttachementBO;
import com.runxsports.provider.cs.cms.service.AttachementService;

/**
 * 资料上传
 * @author WJF
 *
 */
@RestController
@RequestMapping("/attachement")
public class AttachementController extends BaseController{

	@Autowired
	AttachementService attachementService;
		
	/**
	 * 查询路径信息
	 * @param attachementBO
	 * @return
	 */
	@PostMapping(value = {"/query"})
	public RespData<PageInfo<UserAttachment>> queryUserAttach(@RequestBody AttachementBO attachementBO){
		return success(this.attachementService.queryUserAttach(attachementBO));
	}
}
