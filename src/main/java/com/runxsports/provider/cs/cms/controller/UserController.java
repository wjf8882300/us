package com.runxsports.provider.cs.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.runxsports.provider.cs.cms.common.exception.CmsErrorCodeEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsException;
import com.runxsports.provider.cs.cms.entity.User;
import com.runxsports.provider.cs.cms.model.RespData;
import com.runxsports.provider.cs.cms.model.bo.UserBO;
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
	
	@PostMapping("/queryAll")
    public RespData<PageInfo<User>> queryAllUser(@RequestBody UserBO userBO){
        return success(userService.queryAllUser(userBO));
    }
}
