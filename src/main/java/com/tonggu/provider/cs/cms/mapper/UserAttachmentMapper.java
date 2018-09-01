package com.tonggu.provider.cs.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tonggu.provider.cs.cms.entity.UserAttachment;
import com.tonggu.provider.cs.cms.model.vo.AttachementVO;

import tk.mybatis.mapper.common.Mapper;

public interface UserAttachmentMapper extends Mapper<UserAttachment> {
	
	List<AttachementVO> queryUserAttach(@Param("username") String username);
	
	
	List<AttachementVO> queryAll(@Param("username") String username);
}