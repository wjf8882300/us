package com.runxsports.provider.cs.cms.controller.wx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runxsports.provider.cs.cms.common.util.CryptUtil;
import com.runxsports.provider.cs.cms.controller.BaseController;
import com.runxsports.provider.cs.cms.entity.User;
import com.runxsports.provider.cs.cms.model.RespData;
import com.runxsports.provider.cs.cms.model.bo.LoginBO;
import com.runxsports.provider.cs.cms.model.bo.UserBO;
import com.runxsports.provider.cs.cms.model.vo.LoginVO;
import com.runxsports.provider.cs.cms.service.UserService;
import com.runxsports.provider.cs.cms.service.WeChatService;

/**
 * @author wangjf
 *
 */
@RestController("WxUserController")
@RequestMapping("/wx/user")
public class UserController extends BaseController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private WeChatService weChatService;
	
	@PostMapping("/apply")
    public RespData<LoginVO> apply(@RequestBody LoginBO loginBO){
        return success(userService.apply(loginBO));
    }
	
	@PostMapping("/login")
    public RespData<LoginVO> login(@RequestBody LoginBO loginBO){
        return success(userService.login(loginBO));
    }
	
	/***
	 * 查询支部书记或辅导员下的学生
	 * @param userBO
	 * @return List<User>
	 */
	@PostMapping("/query")
    public RespData<String> queryUser(@RequestBody UserBO userBO){
		weChatService.getCacheAccessToken(userBO.getToken());
		List<User> userList = userService.queryUserByNo(userBO);
        return success(CryptUtil.encrypt(userList, userBO.getToken(), "id", "userName"));
    }
}
