package com.runxsports.provider.cs.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping("/query/user")
    public RespData<UserVO> queryUser(@RequestBody UserBO userBO){
        return success(userService.queryUser(userBO));
    }
}
