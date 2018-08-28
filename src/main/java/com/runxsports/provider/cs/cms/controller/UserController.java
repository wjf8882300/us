package com.runxsports.provider.cs.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageInfo;
import com.runxsports.provider.cs.cms.common.constant.enumerate.UserEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsErrorCodeEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsException;
import com.runxsports.provider.cs.cms.entity.User;
import com.runxsports.provider.cs.cms.model.RespData;
import com.runxsports.provider.cs.cms.model.bo.LoginBO;
import com.runxsports.provider.cs.cms.model.bo.UserBO;
import com.runxsports.provider.cs.cms.model.vo.LoginVO;
import com.runxsports.provider.cs.cms.model.vo.UserVO;
import com.runxsports.provider.cs.cms.service.UserService;

/**
 * @author wangjf
 *
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
    public RespData<LoginVO> login(@RequestBody LoginBO loginBO){
        return success(userService.login(loginBO));
    }

	@PostMapping("/query")
    public RespData<UserVO> queryUser(@RequestBody UserBO userBO){
        return success(userService.queryUser(userBO));
    }
	
	@PostMapping("/import")
    public RespData<UserVO> importExcel(MultipartFile file){
		if(file == null) {
			throw new CmsException(CmsErrorCodeEnum.CMS9083009);
		}
		userService.importUser(file);
        return success();
    }
	
	/**
	 * 登录根据学号或者工号查询
	 * @param userNo
	 * @return
	 */
	@GetMapping("byuserno/{userNo}")
    public RespData<User> findByUserNo(@PathVariable("userNo")String userNo){
		return success(this.userService.findByUserNo(userNo));
	}
		
	@PostMapping("/queryAll")
    public RespData<PageInfo<User>> queryAllUser(@RequestBody UserBO userBO){
        return success(userService.queryAllUser(userBO));
    }
	
	/***
	 * 查询支部书记下的学生
	 * @param userBO
	 * @return List<User>
	 */
	@PostMapping("/queryByTeamLeaderNo")
    public RespData<PageInfo<User>> queryByTeamLeaderNo(@RequestBody UserBO userBO){
		userBO.setUserType(UserEnum.Type.STUDENT.getString());
        return success(userService.queryUserByNo(userBO));
    }
	/***
	 * 查询辅导员下的学生
	 * @param userBO
	 * @return List<User>
	 */
	@PostMapping("/queryByTeacherNo")
    public RespData<PageInfo<User>> queryByTeacherNo(@RequestBody UserBO userBO){
		userBO.setUserType(UserEnum.Type.TEACHER.getString());
        return success(userService.queryUserByNo(userBO));
    }
}
