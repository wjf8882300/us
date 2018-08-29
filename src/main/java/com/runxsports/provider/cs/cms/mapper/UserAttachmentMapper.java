package com.runxsports.provider.cs.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.runxsports.provider.cs.cms.entity.UserAttachment;
import tk.mybatis.mapper.common.Mapper;

public interface UserAttachmentMapper extends Mapper<UserAttachment> {
	
	List<UserAttachment> queryUserAttach(@Param("username") String username);
}