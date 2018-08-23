package com.runxsports.provider.cs.cms.common.util;

import java.math.BigDecimal;

/**
 * @author Admin
 */
public class StringUtil {

	/**  
	 * 格式化金额
	 * @author: wangjf  
	 * @createTime: 2018年7月3日 上午11:03:37  
	 * @history:  
	 * @param money (分)
	 * @return String  
	 */
	public static String formatMoney(Integer money) {
		
		// 把分格式化为元
		BigDecimal source = new BigDecimal(money.toString());
		source = source.divide(new BigDecimal("100"));
		
		return source.toPlainString() + "元";
	}
}
