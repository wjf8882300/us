package com.tonggu.provider.cs.cms.common.constant.enumerate;

/**
 * 全局响应枚举
 * @author wangjf
 * @create 2018年7月11日
 */
public enum GlobalCallbackEnum implements BaseCallbackEnum {

	SUCCESS(200, "操作成功"),
	SERVICE_ERROR(500, "抱歉，您的操作出问题了，请反馈客服处理，谢谢"),
	BUSINESS_ERROR(420, "业务异常，请联系客服"),
	TOKEN_TIMEOUT(9999, "登录信息失效，请重新登录"),
	UNAUTHORIZED(99990401, "非法请求"),
	PARAMETER_ILLEGAL(9999100, "参数非法"),
	UN_BIND_PHONE(1000, "为了账号安全，请绑定手机号"),
	OUT_VERSION(1001, "您的版本过旧，请更新版本"),
	MORE_DEVICE_LOGIN(5555, "您的账号已在其他终端登陆!"),
	SC_FORBIDDEN(403, "无权限访问"),
	SC_BAD_REQUEST(400, "请求无效"),
	SQL_INSERT_FAILURE(800, "插入失败"),
	SQL_UPDATE_FAILURE(801, "更新失败"),
	SQL_DELTE_FAILURE(802, "更新失败"),
	REDIS_KEY_NOT_EXISTS(9999101, "未找到redis缓存的key")
	;
	/** 编码 */
	private Integer value;

	/** 说明 */
	private String intro;

	@Override
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	private GlobalCallbackEnum(Integer value, String intro) {
		this.value = value;
		this.intro = intro;
	}

}
