package com.tonggu.provider.us.service;


import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.tonggu.provider.us.model.bo.AttachementBO;
import com.tonggu.provider.us.model.vo.AttachementVO;

public interface AttachementService {

	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	AttachementVO upload(MultipartFile file, AttachementBO attachementBO);
	
	/**
	 * 查询路径信息
	 * @param attachementBO
	 * @return
	 */
	PageInfo<AttachementVO> queryUserAttach(AttachementBO attachementBO);
}
