package org.lychie.jexcel.model;

import java.util.Map;
import java.util.Date;
import java.util.HashMap;

/**
 * 数据格式
 * 
 * @author Lychie Fan
 */
public abstract class DataFormat {

	/**
	 * 文本格式
	 */
	public static final String TEXT_FORMAT = "GENERAL";

	/**
	 * 日期格式
	 */
	public static final String DATE_FORMAT = "yyyy/mm/dd";

	/**
	 * 时间格式
	 */
	public static final String TIME_FORMAT = "hh:mm:ss";

	/**
	 * 日期时间格式
	 */
	public static final String DATETIME_FORMAT = "yyyy/mm/dd hh:mm:ss";

	/**
	 * 整数格式
	 */
	public static final String INTEGER_FORMAT = "0";

	/**
	 * 小数格式, 精确到小数点后一位
	 */
	public static final String DECIMAL_FORMAT_1 = "0.0";

	/**
	 * 小数格式, 精确到小数点后两位
	 */
	public static final String DECIMAL_FORMAT_2 = "0.00";

	/**
	 * 百分比格式
	 */
	public static final String PERCENT_FORMAT = "0%";

	/**
	 * 百分比格式, 精确到小数点后一位
	 */
	public static final String PERCENT_FORMAT_1 = "0.0%";

	/**
	 * 百分比格式, 精确到小数点后两位
	 */
	public static final String PERCENT_FORMAT_2 = "0.00%";

	/**
	 * 货币格式
	 */
	public static final String CURRENCY_FORMAT = "￥#,##0;￥-#,##0";

	/**
	 * 货币格式, 精确到小数点后一位
	 */
	public static final String CURRENCY_FORMAT_1 = "￥#,##0.0;￥-#,##0.0";

	/**
	 * 货币格式, 精确到小数点后两位
	 */
	public static final String CURRENCY_FORMAT_2 = "￥#,##0.00;￥-#,##0.00";
	
	/**
	 * 默认绑定
	 */
	protected static final Map<Object, String> DEFAULT_MAPPER;
	
	static {
		DEFAULT_MAPPER = new HashMap<Object, String>();
		DEFAULT_MAPPER.put(Date.class, DATE_FORMAT);
		DEFAULT_MAPPER.put(Short.TYPE, INTEGER_FORMAT);
		DEFAULT_MAPPER.put(Short.class, INTEGER_FORMAT);
		DEFAULT_MAPPER.put(Integer.TYPE, INTEGER_FORMAT);
		DEFAULT_MAPPER.put(Integer.class, INTEGER_FORMAT);
		DEFAULT_MAPPER.put(Long.TYPE, INTEGER_FORMAT);
		DEFAULT_MAPPER.put(Long.class, INTEGER_FORMAT);
		DEFAULT_MAPPER.put(Float.TYPE, DECIMAL_FORMAT_2);
		DEFAULT_MAPPER.put(Float.class, DECIMAL_FORMAT_2);
		DEFAULT_MAPPER.put(Double.TYPE, DECIMAL_FORMAT_2);
		DEFAULT_MAPPER.put(Double.class, DECIMAL_FORMAT_2);
	}

}