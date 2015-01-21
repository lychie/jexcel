package org.lychie.jexcel.util;

import java.util.Date;
import org.lychie.jutil.TypeUtil;
import org.lychie.beanutil.BeanClass;
import org.lychie.jexcel.model.Stylesheet;
import org.lychie.jexcel.model.ValueFormat;

/**
 * 值格式构建者
 * 
 * @author Lychie Fan
 */
public class ValueFormatBuilder {

	/**
	 * 构建值格式
	 * 
	 * @param format
	 *            值格式对象
	 * @param propertyName
	 *            属性名称
	 * @param propertyType
	 *            属性类型
	 * @return
	 */
	public static String buildFormat(ValueFormat format, String propertyName,
			Class<?> propertyType) {

		String pattern = format.getMapper().get(propertyName);
		if (pattern == null) {
			pattern = format.getMapper().get(propertyType);
			if (pattern == null) {
				pattern = ValueFormat.TEXT_FORMAT;
			}
		}
		return pattern;

	}

	/**
	 * 构建值内容水平方向对齐方式
	 * 
	 * @param propertyType
	 *            属性类型
	 * @return
	 */
	public static short buildAlignment(Class<?> propertyType) {
		if (propertyType == Date.class) {
			return Stylesheet.HORIZONTAL_CENTER;
		}
		if (TypeUtil.isNumber(propertyType)
				|| BeanClass.isFrom(propertyType, Number.class)) {
			return Stylesheet.HORIZONTAL_RIGHT;
		}
		return Stylesheet.HORIZONTAL_LEFT;
	}

}