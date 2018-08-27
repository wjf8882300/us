package com.runxsports.provider.cs.cms.common.constant.enumerate;

/**
 * @author Admin
 */
public enum TencentUrlEnum {
    /** */
    ACCESS_TOKER_URL("https://api.weixin.qq.com/sns/jscode2session?appid={APPID}&secret={SECRET}&js_code={CODE}&grant_type=authorization_code"),

    USER_INFO_URL("https://api.weixin.qq.com/sns/userinfo?access_token={ACCESS_TOKEN}&openid={OPENID}");
	
    private String url;

    private TencentUrlEnum(String url) {
        this.url = url;
    }

    public String url() {
    	return url;
    }

}
