package com.tonggu.provider.us.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tonggu.provider.us.common.config.properties.UsProperties;
import com.tonggu.provider.us.common.constant.enumerate.GlobalCallbackEnum;
import com.tonggu.provider.us.common.exception.CmsErrorCodeEnum;
import com.tonggu.provider.us.common.exception.CmsException;
import com.tonggu.provider.us.common.util.FileUtil;
import com.tonggu.provider.us.common.util.IDUtil;
import com.tonggu.provider.us.common.util.ValidateUtils;
import com.tonggu.provider.us.entity.UserAttachment;
import com.tonggu.provider.us.mapper.UserAttachmentMapper;
import com.tonggu.provider.us.model.bo.AttachementBO;
import com.tonggu.provider.us.model.vo.AccessTokenVo;
import com.tonggu.provider.us.model.vo.AttachementVO;
import com.tonggu.provider.us.service.AttachementService;
import com.tonggu.provider.us.service.WeChatService;
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

	@Override
	public PageInfo<AttachementVO> queryUserAttach(AttachementBO attachementBO) {
		Integer currentPage = attachementBO.getStart();
        Integer pageSize = attachementBO.getLength();
        ValidateUtils.notBlank(String.valueOf(currentPage), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        ValidateUtils.notBlank(String.valueOf(pageSize), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        if (currentPage < 0 || pageSize <= 0) {
            log.error("分页数据有误");
            throw new CmsException(GlobalCallbackEnum.PARAMETER_ILLEGAL);
        }
        PageHelper.startPage(currentPage, pageSize);
		List<AttachementVO> result = this.userAttachmentMapper.queryUserAttach(attachementBO.getUsername());
//        List<AttachementVO> result = this.userAttachmentMapper.queryAll(attachementBO.getUsername());
		PageInfo<AttachementVO> pageInfo = new PageInfo<AttachementVO>(result);
		return pageInfo;
	}

}
