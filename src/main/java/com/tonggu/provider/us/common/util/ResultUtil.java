package com.tonggu.provider.us.common.util;

import com.tonggu.provider.us.common.constant.enumerate.GlobalCallbackEnum;
import com.tonggu.provider.us.model.RespData;

public class ResultUtil {

	/** 
	 * 判断结果是否正确
	 *
	 * @Title: isSuccess 
	 * @Description: TODO
	 * @param @param result
	 * @param @return 
	 * @return Boolean 
	 * @throws 
	 */
	public static <T> Boolean isSuccess(RespData<T> result) {
		if(result != null 
				&& GlobalCallbackEnum.SUCCESS.getValue().equals(result.getCode())
				&& result.getData() != null) {
			return true;
		}
		return false;
	}

}
