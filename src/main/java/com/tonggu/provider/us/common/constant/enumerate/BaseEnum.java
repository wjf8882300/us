package com.tonggu.provider.us.common.constant.enumerate;

/**
 * 基类枚举
 * @author wangjf
 */
public interface BaseEnum<T> {

	/**
	 * 获取枚举值
	 * @since 2018年7月11日
	 * @author wangjf
	 * @return 值
	 */
	T getValue();

	/**
	 * 获取枚举说明
	 * @since 2018年7月11日
	 * @author wangjf
	 * @return 说明
	 */
	String getIntro();

	/**
	 * 比较参数,默认调用equels方法
	 * @since 2018年7月11日
	 * @author wangjf
	 * @param value
	 * @return 是否相同
	 */
	default boolean compare(T value) {
		return getValue().equals(value);
	}

}
