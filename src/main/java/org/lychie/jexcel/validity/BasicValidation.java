package org.lychie.jexcel.validity;

import java.util.Date;
import org.lychie.jutil.DateUtil;
import org.lychie.jutil.TypeUtil;

public class BasicValidation implements Validation {

	@Override
	public boolean validate(Class<?> type, String name, String value) {
		if (TypeUtil.isInteger(type)) {
			return isLegalInteger(value);
		}
		if (TypeUtil.isDecimal(type)) {
			return isLegalDecimal(value);
		}
		if (TypeUtil.isBooleanType(type)) {
			return isLegalBoolean(value);
		}
		if (type == Date.class) {
			return isLegalDate(value);
		}
		if (type == String.class) {
			return isLegalString(value);
		}
		return false;
	}

	/**
	 * 是否是一个合法的整数值
	 * 
	 * @param value
	 *            字符串类型值
	 * @return
	 */
	protected boolean isLegalInteger(String value) {
		return value.matches("^-?[1-9]\\d*$");
	}

	/**
	 * 是否是一个合法的小数值
	 * 
	 * @param value
	 *            字符串类型值
	 * @return
	 */
	protected boolean isLegalDecimal(String value) {
		if (value.indexOf(".") == -1) {
			value += ".";
		}
		return value.matches("^-?[1-9]\\d*\\.\\d*|-0\\.\\d*[1-9]\\d*$");
	}

	/**
	 * 是否是一个合法的字符串值
	 * 
	 * @param value
	 *            字符串类型值
	 * @return
	 */
	protected boolean isLegalString(String value) {
		return value != null;
	}

	/**
	 * 是否是一个合法的布尔值
	 * 
	 * @param value
	 *            字符串类型值
	 * @return
	 */
	protected boolean isLegalBoolean(String value) {
		return value.equals("0") || value.equals("1")
				|| value.equalsIgnoreCase("no")
				|| value.equalsIgnoreCase("yes")
				|| value.equalsIgnoreCase("true")
				|| value.equalsIgnoreCase("false");
	}

	/**
	 * 是否是一个合法的日期值, 自动匹配模式串
	 * 
	 * @param value
	 *            字符串类型值
	 * @return
	 */
	protected boolean isLegalDate(String value) {
		try {
			return DateUtil.parse(value) != null;
		} catch (Throwable e) {
			/* ignore */
			return false;
		}
	}

	/**
	 * 是否是一个合法的日期值
	 * 
	 * @param value
	 *            字符串类型值
	 * @param pattern
	 *            模式串
	 * @return
	 */
	protected boolean isLegalDate(String value, String pattern) {
		try {
			return DateUtil.parse(value, pattern) != null;
		} catch (Throwable e) {
			/* ignore */
			return false;
		}
	}

}