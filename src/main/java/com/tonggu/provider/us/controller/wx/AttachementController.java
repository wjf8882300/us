package com.tonggu.provider.us.controller.wx;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tonggu.provider.us.controller.BaseController;
import com.tonggu.provider.us.model.RespData;
import com.tonggu.provider.us.model.bo.AttachementBO;
import com.tonggu.provider.us.model.vo.AttachementVO;
import com.tonggu.provider.us.service.AttachementService;

/**
 * 资料上传
 * @author WJF
 *
 */
@RestController("WxAttachementController")
@RequestMapping("/wx/attachement")
public class AttachementController extends BaseController{

	@Autowired
	AttachementService attachementService;
	
	@PostMapping(value = {"/upload"})
    public RespData<AttachementVO> uploadFile(@RequestParam("file")MultipartFile file, @RequestParam("token") String token){
		AttachementBO attachementBO = new AttachementBO();
		attachementBO.setToken(token);
		return success(attachementService.upload(file, attachementBO));
	}
}
