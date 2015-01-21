package org.lychie.jexcel;

import java.util.LinkedHashMap;

/**
 * Excel 抽象类
 * 
 * @author Lychie Fan
 */
public abstract class Excel {

	protected String sheetName;
	protected LinkedHashMap<String, String> mapper;
	protected static final int TITLE_ROW_NUMBER = 0;

	public Excel() {
		mapper = new LinkedHashMap<String, String>();
	}

	/**
	 * 设置模型属性与文档标题的映射关系
	 * 
	 * @param property
	 *            bean属性名称
	 * @param name
	 *            excel标题名称
	 */
	public void setMapper(String property, String name) {
		mapper.put(name, property);
	}

	/**
	 * 设置操作的工作表名称
	 * 
	 * @param name
	 *            工作表名称
	 */
	public void setSheetName(String name) {
		this.sheetName = name;
	}

}