package org.lychie.jexcel.validity;

public interface Validation {

	/**
	 * 校验字符串类型值的有效性
	 * 
	 * @param type
	 *            属性类型
	 * @param name
	 *            属性名称
	 * @param value
	 *            字符串类型值
	 * @return 若校验通过, 则返回true; 否则返回false
	 */
	boolean validate(Class<?> type, String name, String value);

}