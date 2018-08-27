package com.runxsports.provider.cs.cms.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.runxsports.provider.cs.cms.common.constant.enumerate.DeleteStatusEnum;
import com.runxsports.provider.cs.cms.common.constant.enumerate.GlobalCallbackEnum;
import com.runxsports.provider.cs.cms.common.constant.enumerate.UserEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsErrorCodeEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsException;
import com.runxsports.provider.cs.cms.common.util.CacheUtil;
import com.runxsports.provider.cs.cms.common.util.ExcelReadHelper;
import com.runxsports.provider.cs.cms.common.util.IDUtil;
import com.runxsports.provider.cs.cms.common.util.ValidateUtils;
import com.runxsports.provider.cs.cms.entity.User;
import com.runxsports.provider.cs.cms.entity.UserLogin;
import com.runxsports.provider.cs.cms.mapper.UserLoginMapper;
import com.runxsports.provider.cs.cms.mapper.UserMapper;
import com.runxsports.provider.cs.cms.model.ExcelUser;
import com.runxsports.provider.cs.cms.model.bo.LoginBO;
import com.runxsports.provider.cs.cms.model.bo.UserBO;
import com.runxsports.provider.cs.cms.model.vo.AccessTokenVo;
import com.runxsports.provider.cs.cms.model.vo.LoginVO;
import com.runxsports.provider.cs.cms.model.vo.UserVO;
import com.runxsports.provider.cs.cms.service.UserService;
import com.runxsports.provider.cs.cms.service.WeChatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private WeChatService weChatService;
	
	@Autowired
	private UserLoginMapper userLoginMapper;
	
	@Autowired
	@Qualifier("asyncServiceExecutor")
	Executor executor;

	@Override
	public UserVO queryUser(UserBO userBO) {
		
		if(null == userBO || null == userBO.getUserId()){
			log.error("查询用户失败，参数有误！");
            throw new CmsException(GlobalCallbackEnum.PARAMETER_ILLEGAL);
        }
		
		UserVO userVO = new UserVO();
		
		User record = new User();
		record.setId(userBO.getUserId());
		record.setIsDelete(DeleteStatusEnum.ENABLED.getString());
		User result = userMapper.selectOne(record);
		if(result != null) {
			userVO.setUserType(result.getUserType());
			userVO.setUserName(result.getUserName());
		}
		
		return userVO;
	}

	@Transactional
	@Override
	public void importUser(MultipartFile file) {
		List<ExcelUser> userList = null;
		InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            userList = ExcelReadHelper.excelRead(inputStream, new String[] {"userName", "className", "userNo", "teamName", "teamLeader", "teamLeaderNo", "teacher", "teacherNo"}, ExcelUser.class);
        } catch (Exception e) {
            log.error("导入出错!{}", e);
            throw new CmsException(CmsErrorCodeEnum.CMS9083007);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (userList == null || userList.isEmpty()) {
            log.warn("表格为空");
            throw new CmsException(CmsErrorCodeEnum.CMS9083008);
        }
		
        // 将本次之前的数据置为逻辑删除
        userMapper.modifyOld();
        
        // 导入本次数据
        List<User> list = Lists.newArrayList();
        // 支部书记
        Map<String, User> leaderMap = Maps.newConcurrentMap();
        // 辅导员
        Map<String, User> teacherMap = Maps.newConcurrentMap();
        
        userList.forEach((u)->{
        	
        	if(StringUtils.isEmpty(u.getUserName())
        			|| StringUtils.isEmpty(u.getUserNo())) {
        		return;
        	}
        	
        	User user = new User();
        	user.setId(IDUtil.nextId());
        	user.setUserName(u.getUserName());
        	user.setClassName(u.getClassName());
        	user.setUserType(UserEnum.Type.STUDENT.getString());
        	user.setPassword(u.getUserNo());
        	user.setTeacher(u.getTeacher());
        	user.setTeamLeader(u.getTeamLeader());
        	user.setTeamLeaderNo(u.getTeamLeaderNo());
        	user.setTeamName(u.getTeamName());
        	user.setUserNo(u.getUserNo());
        	user.setTeacher(u.getTeacher());
        	user.setTeacherNo(u.getTeacherNo());
        	user.setCreateDate(new Date());
        	user.setLastUpdateDate(new Date());
        	list.add(user);
        	
        	// 解析支部书记
        	if(!leaderMap.containsKey(u.getTeamLeaderNo())) {
        		User leader = new User();
        		leader.setId(IDUtil.nextId());
        		leader.setUserType(UserEnum.Type.LEADER.getString());
        		leader.setUserName(u.getTeamLeader());
        		leader.setUserNo(u.getTeamLeaderNo());
        		leader.setPassword(u.getTeamLeaderNo());
        		leader.setTeamName(u.getTeamName());
        		leader.setCreateDate(new Date());
        		leader.setLastUpdateDate(new Date());
        		leaderMap.put(u.getTeamLeaderNo(), leader);
        		list.add(leader);
        	}
        	
        	// 解析辅导员
        	if(!teacherMap.containsKey(u.getTeacherNo())) {
        		User teacher = new User();
        		teacher.setId(IDUtil.nextId());
        		teacher.setUserType(UserEnum.Type.TEACHER.getString());
        		teacher.setUserName(u.getTeacher());
        		teacher.setUserNo(u.getTeacherNo());
        		teacher.setPassword(u.getTeacherNo());
        		teacher.setCreateDate(new Date());
        		teacher.setLastUpdateDate(new Date());
        		teacherMap.put(u.getTeacherNo(), teacher);
        		list.add(teacher);
        	}
        });
        
        int rows = userMapper.batchInsert(list);
        if(rows != list.size()) {
        	log.error("批量插入用户信息失败");
        	throw new CmsException(CmsErrorCodeEnum.CMS9083004);
        }
	}

	@Override
	public User findByUserNo(String userNo) {
		User user = this.userMapper.findByUserNo(userNo);
		if(user == null) {
			log.error("查询用户失败，参数有误！");
            throw new CmsException(GlobalCallbackEnum.SC_FORBIDDEN);
		}
		CacheUtil.put(user);
		return user;
	}
	
	public PageInfo<User> queryAllUser(UserBO userBO) {
		
		Integer currentPage = userBO.getStart();
        Integer pageSize = userBO.getLength();
        ValidateUtils.notBlank(String.valueOf(currentPage), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        ValidateUtils.notBlank(String.valueOf(pageSize), GlobalCallbackEnum.PARAMETER_ILLEGAL);
        if (currentPage < 0 || pageSize <= 0) {
            log.error("分页数据有误");
            throw new CmsException(GlobalCallbackEnum.PARAMETER_ILLEGAL);
        }

        PageHelper.startPage(currentPage, pageSize);
        User record = new User();
        if(StringUtils.isNotEmpty(userBO.getUserName())) {
        	record.setUserName(userBO.getUserName());
        }
        if(StringUtils.isNotEmpty(userBO.getUserType())) {
        	record.setUserType(userBO.getUserType());
        }
		record.setIsDelete(DeleteStatusEnum.ENABLED.getString());
		List<User> result = userMapper.select(record);
		result.forEach((t)->{ t.setUserType(UserEnum.Type.getEnum(Integer.parseInt(t.getUserType())).getValue());});
		PageInfo<User> pageInfo = new PageInfo<User>(result);
		return pageInfo;
	}

	@Override
	public LoginVO login(LoginBO loginBO) {
		ValidateUtils.notBlank(loginBO.getPassword(), CmsErrorCodeEnum.CMS9083011);
		ValidateUtils.notBlank(loginBO.getCode(), CmsErrorCodeEnum.CMS9083013);

		LoginVO loginVO = new LoginVO();
		
		// 登录校验
		User record = new User();
		record.setPassword(loginBO.getPassword());
		record.setIsDelete(DeleteStatusEnum.ENABLED.getString());
		User result = userMapper.selectOne(record);
		if(result != null) {
			loginVO.setUserType(result.getUserType());
		} else {
			throw new CmsException(CmsErrorCodeEnum.CMS9083012);
		}

		// 生成token
		AccessTokenVo token = new AccessTokenVo();
		token.setUserId(result.getId());
		
		String userToken = weChatService.cacheAccessToken(token);
		loginVO.setToken(userToken);
		
		// 存储登录信息
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				// 获取用户token
				try {
					AccessTokenVo token = weChatService.getAccessToken(loginBO.getCode());
					token.setUserId(result.getId());

					UserLogin userLogin = new UserLogin();
					userLogin.setId(IDUtil.nextId());
					userLogin.setUserId(result.getId());
					userLogin.setOpenId(token.getOpenId());
					userLoginMapper.insertSelective(userLogin);
				} catch (Exception e) {
					log.error("获取token失败, {}", e);
				}
			}
		});
		
		return loginVO;
	}

}