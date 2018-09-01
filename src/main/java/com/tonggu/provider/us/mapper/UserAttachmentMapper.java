package com.tonggu.provider.us.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tonggu.provider.us.entity.UserAttachment;
import com.tonggu.provider.us.model.vo.AttachementVO;

import tk.mybatis.mapper.common.Mapper;

public interface UserAttachmentMapper extends Mapper<UserAttachment> {
	
	List<AttachementVO> queryUserAttach(@Param("username") String username);
	
	
	List<AttachementVO> queryAll(@Param("username") String username);
}