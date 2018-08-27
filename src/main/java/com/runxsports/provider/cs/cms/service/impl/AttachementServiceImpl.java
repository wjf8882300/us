package com.runxsports.provider.cs.cms.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.runxsports.provider.cs.cms.common.config.properties.UsProperties;
import com.runxsports.provider.cs.cms.common.exception.CmsErrorCodeEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsException;
import com.runxsports.provider.cs.cms.common.util.FileUtil;
import com.runxsports.provider.cs.cms.common.util.IDUtil;
import com.runxsports.provider.cs.cms.entity.UserAttachment;
import com.runxsports.provider.cs.cms.mapper.UserAttachmentMapper;
import com.runxsports.provider.cs.cms.model.bo.AttachementBO;
import com.runxsports.provider.cs.cms.model.vo.AccessTokenVo;
import com.runxsports.provider.cs.cms.model.vo.AttachementVO;
import com.runxsports.provider.cs.cms.service.AttachementService;
import com.runxsports.provider.cs.cms.service.WeChatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AttachementServiceImpl implements AttachementService {

	@Autowired
	UsProperties usProperties;
	
	@Autowired
	private WeChatService weChatService;
	
	@Autowired
	private UserAttachmentMapper userAttachmentMapper;
	
	@Override
	public AttachementVO upload(MultipartFile file, AttachementBO attachementBO) {
		
		if(file == null) {
			throw new CmsException(CmsErrorCodeEnum.CMS9083009);
		}
		
		AccessTokenVo accessTokenVo = weChatService.getCacheAccessToken(attachementBO.getToken());
		Long userId = accessTokenVo.getUserId();
		
		AttachementVO attachementVO = new AttachementVO();
		
		String originalFilename = file.getOriginalFilename();
		String extName = FileUtil.getExt(originalFilename);
		
		// 生成随机路径
		String basePath = usProperties.getFile().getBasePath();
		String uploadPath = usProperties.getFile().getUploadPath();
		String dirPath = FileUtil.createDateDir(basePath + uploadPath);
		StringBuilder tempFileName = new StringBuilder();
		tempFileName.append(IDUtil.nextStrId()).append(extName);
		String fileName = tempFileName.toString();
		StringBuilder tempFilePath = new StringBuilder(dirPath);
		tempFilePath.append(fileName);
		
		// 保存文件
		String filePath = tempFilePath.toString();
		FileOutputStream out = null;
		InputStream in = null;
		try {
			out = new FileOutputStream(filePath);
			in  = file.getInputStream();
			StreamUtils.copy(in, out);
			
			UserAttachment userAttachment = new UserAttachment();
			userAttachment.setId(IDUtil.nextId());
			userAttachment.setAttachementName(fileName);
			userAttachment.setAttachementPath(filePath.replace(basePath, ""));
			userAttachment.setAttachementType("");
			userAttachment.setUserId(userId);
			userAttachment.setCreateDate(new Date());
			userAttachment.setLastUpdateDate(new Date());
			int rows = userAttachmentMapper.insertSelective(userAttachment);
			if(rows < 1) {
				log.error("插入附近失败！用户Id:{}", userId);
				throw new CmsException(CmsErrorCodeEnum.CMS9083004);
			}
			
			attachementVO.setAttachementPath(userAttachment.getAttachementPath());
		} catch (FileNotFoundException e) {
			log.error("上传文件失败！{}", e);
			throw new CmsException(CmsErrorCodeEnum.CMS9083016);
		} catch (IOException e) {
			log.error("上传文件失败！{}", e);
			throw new CmsException(CmsErrorCodeEnum.CMS9083016);
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				log.error("关闭文件失败！{}", e);
			}
		}

		return attachementVO;
	}

}
