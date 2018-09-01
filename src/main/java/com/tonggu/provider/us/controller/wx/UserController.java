package com.tonggu.provider.us.controller.wx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tonggu.provider.us.common.config.ParamConfig;
import com.tonggu.provider.us.common.util.CryptUtil;
import com.tonggu.provider.us.controller.BaseController;
import com.tonggu.provider.us.entity.User;
import com.tonggu.provider.us.model.RespData;
import com.tonggu.provider.us.model.bo.LoginBO;
import com.tonggu.provider.us.model.bo.UserBO;
import com.tonggu.provider.us.model.vo.AccessTokenVo;
import com.tonggu.provider.us.model.vo.LoginVO;
import com.tonggu.provider.us.service.UserService;
import com.tonggu.provider.us.service.WeChatService;

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
		AccessTokenVo token = weChatService.getCacheAccessToken(userBO.getToken());
		userBO.setUserType(token.getUserType());
		userBO.setUserId(token.getUserId());
		List<User> userList = userService.queryUserByNo(userBO);
        return success(CryptUtil.encrypt(userList, userBO.getToken(), ParamConfig.isEncrypt(token.getAlgorithm()), "id", "userName"));
    }
}
