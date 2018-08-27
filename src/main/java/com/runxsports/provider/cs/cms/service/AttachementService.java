package com.runxsports.provider.cs.cms.service;

import org.springframework.web.multipart.MultipartFile;

import com.runxsports.provider.cs.cms.model.bo.AttachementBO;
import com.runxsports.provider.cs.cms.model.vo.AttachementVO;

public interface AttachementService {

	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	AttachementVO upload(MultipartFile file, AttachementBO attachementBO);
}
