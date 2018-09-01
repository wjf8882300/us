package com.tonggu.provider.cs.cms.common.util;

import com.tonggu.provider.cs.cms.common.constant.enumerate.GlobalCallbackEnum;
import com.tonggu.provider.cs.cms.model.RespData;

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
