package org.lychie.jexcel.model;

import java.util.Map;
import java.util.HashMap;

/**
 * 值格式
 * 
 * @author Lychie Fan
 */
public class ValueFormat extends DataFormat {
	
	private Map<Object, String> mapper = new HashMap<Object, String>();

	ValueFormat() {
		init();
	}

	/**
	 * 属性名称绑定值格式
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param format
	 *            格式
	 */
	public void set(String propertyName, String format) {
		mapper.put(propertyName, format);
	}

	/**
	 * 属性类型绑定值格式
	 * 
	 * @param propertyType
	 *            属性类型
	 * @param format
	 *            格式
	 */
	public void set(Class<?> propertyType, String format) {
		mapper.put(propertyType, format);
	}

	/**
	 * 获取映射对象
	 * 
	 * @return
	 */
	public Map<Object, String> getMapper() {
		return mapper;
	}

	/**
	 * 初始绑定
	 */
	private void init() {
		mapper.putAll(DEFAULT_MAPPER);
	}

}