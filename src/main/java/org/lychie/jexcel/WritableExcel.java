package org.lychie.jexcel;

import java.io.File;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.lychie.beanutil.BeanClass;
import org.lychie.beanutil.ClassWrapper;
import org.lychie.jexcel.exception.ExcelCastException;
import org.lychie.jexcel.model.Body;
import org.lychie.jexcel.model.Stylesheet;
import org.lychie.jexcel.model.Title;
import org.lychie.jexcel.model.ValueFormat;
import org.lychie.jexcel.util.CellStyleBuilder;
import org.lychie.jexcel.util.ValueFormatBuilder;
import org.lychie.jutil.ConvertUtil;
import org.lychie.jutil.DateUtil;
import org.lychie.jutil.IOUtil;
import org.lychie.jutil.TypeUtil;
import org.lychie.jutil.exception.UnexpectedException;

/**
 * 可写的EXCEL, 抛弃03[从能容纳的行列数考虑], 以07[*.xlsx]方式写出
 * 
 * @author Lychie Fan
 */
public class WritableExcel extends Excel {

	private Body body;
	private Title title;
	private List<?> pojos;
	private ClassWrapper wrapper;
	private XSSFWorkbook workbook;
	private ValueFormat valueFormat;

	/**
	 * 构建可写的EXCEL对象
	 * 
	 * @param pojos
	 *            待写出的集合
	 */
	public WritableExcel(List<?> pojos) {
		this.pojos = pojos;
		this.workbook = new XSSFWorkbook();
		this.wrapper = ClassWrapper.wrap(pojos.get(0).getClass());
		this.body = BeanClass.newInstance(Body.class, null, null);
		this.title = BeanClass.newInstance(Title.class, null, null);
		this.valueFormat = body.getValueFormat();
	}

	/**
	 * 写出到文件
	 * 
	 * @param file
	 *            目标文件
	 */
	public void write(File file) {
		write(IOUtil.openFileOutputStream(file));
	}

	/**
	 * 写出到输出流
	 * 
	 * @param out
	 *            输出流
	 */
	public void write(OutputStream out) {
		try {
			init();
			XSSFSheet sheet = workbook.createSheet(sheetName);
			createTitleRow(sheet);
			int rowIndex = TITLE_ROW_NUMBER + 1;
			for (Object pojo : pojos) {
				createBodyRow(sheet.createRow(rowIndex++), pojo);
			}
			workbook.write(out);
		} catch (Throwable e) {
			throw new UnexpectedException(e);
		} finally {
			IOUtil.close(out);
		}
	}

	/**
	 * 获取主体对象
	 * 
	 * @return
	 */
	public Body getBody() {
		return body;
	}

	/**
	 * 获取标题对象
	 * 
	 * @return
	 */
	public Title getTitle() {
		return title;
	}

	/**
	 * 获取主体单元格值格式对象
	 * 
	 * @return
	 */
	public ValueFormat getValueFormat() {
		return valueFormat;
	}

	/**
	 * 构建行
	 * 
	 * @param row
	 *            行对象
	 * @param pojo
	 *            该行写出的对象
	 * @throws Throwable
	 */
	private void createBodyRow(XSSFRow row, Object pojo) throws Throwable {
		int cellIndex = 0;
		row.setHeightInPoints(body.getHeight());
		for (String property : mapper.values()) {
			Object value = wrapper.getPropertyValue(pojo, property);
			Class<?> propertyType = wrapper.getPropertyType(property);
			XSSFCell cell = row.createCell(cellIndex++);
			setCellStyle(cell, property, propertyType);
			setCellValue(cell, value, propertyType);
		}
	}

	/**
	 * 构建标题行
	 * 
	 * @param sheet
	 *            工作表
	 * @throws Throwable
	 */
	private void createTitleRow(XSSFSheet sheet) throws Throwable {
		int cellIndex = 0;
		int cellWidth = title.getWidth();
		XSSFRow row = sheet.createRow(TITLE_ROW_NUMBER);
		row.setHeightInPoints(title.getHeight());
		CellStyle style = CellStyleBuilder.buildCellStyle(workbook, title,
				ValueFormat.TEXT_FORMAT);
		for (String key : mapper.keySet()) {
			sheet.setColumnWidth(cellIndex, cellWidth);
			XSSFCell cell = row.createCell(cellIndex++);
			cell.setCellStyle(style);
			cell.setCellValue(key);
		}
	}

	/**
	 * 设置单元格的值
	 * 
	 * @param cell
	 *            单元格对象
	 * @param value
	 *            值对象
	 * @param type
	 *            值类型
	 */
	private void setCellValue(XSSFCell cell, Object value, Class<?> type) {
		if (TypeUtil.isNumber(type) || BeanClass.isFrom(type, Number.class)) {
			BigDecimal decimal = new BigDecimal(value.toString(),
					MathContext.UNLIMITED);
			cell.setCellValue(decimal.doubleValue());
		} else if (TypeUtil.isBooleanType(type)) {
			Boolean bool = ConvertUtil.convert(value.toString(), Boolean.class);
			cell.setCellValue(bool);
		} else if (type == Date.class) {
			cell.setCellValue(DateUtil.format((Date) value));
		} else {
			cell.setCellValue(value.toString());
		}
	}

	/**
	 * 设置单元格的样式
	 * 
	 * @param cell
	 *            单元格对象
	 * @param property
	 *            属性名称
	 * @param type
	 *            属性类型
	 */
	private void setCellStyle(XSSFCell cell, String property, Class<?> type) {
		String dataFormat = ValueFormatBuilder.buildFormat(valueFormat,
				property, type);
		CellStyle style = CellStyleBuilder.buildCellStyle(workbook, body,
				dataFormat);
		if (body.getHorizontalAlignment() == Stylesheet.NONE_STYLE) {
			style.setAlignment(ValueFormatBuilder.buildAlignment(type));
		}
		cell.setCellStyle(style);
	}

	/**
	 * 初始化
	 */
	private void init() {
		if (mapper.isEmpty()) {
			throw new ExcelCastException("the mapper relation is not set");
		}
		if (sheetName == null) {
			sheetName = "Sheet1";
		}
	}

}