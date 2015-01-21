package org.lychie.jexcel;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.lychie.beanutil.BeanClass;
import org.lychie.beanutil.ClassWrapper;
import org.lychie.jexcel.exception.ExcelCastException;
import org.lychie.jexcel.exception.ValidationCastException;
import org.lychie.jexcel.util.ColumnIndexConverter;
import org.lychie.jexcel.validity.Validation;
import org.lychie.jutil.ConvertUtil;
import org.lychie.jutil.DateUtil;
import org.lychie.jutil.IOUtil;
import org.lychie.jutil.TypeUtil;
import org.lychie.jutil.exception.UnexpectedException;

/**
 * 可读的EXCEL, 兼容03和07
 * 
 * @author Lychie Fan
 */
public class ReadableExcel extends Excel {

	private Sheet sheet;
	private int sheetIndex;
	private int columnCount;
	private String dateFormat;
	private Class<?> beanClass;
	private ClassWrapper wrapper;
	private LinkedList<String> titles;

	/**
	 * 构建可读的excel实例
	 * 
	 * @param beanClass
	 *            pojo类, excel文档的每一行解析成该类的一个实例
	 */
	public ReadableExcel(Class<?> beanClass) {
		this.beanClass = beanClass;
		this.wrapper = ClassWrapper.wrap(beanClass);
	}

	/**
	 * 载入文件
	 * 
	 * @param file
	 *            excel文件
	 */
	public void load(File file) {
		load(IOUtil.openFileInputStream(file));
	}

	/**
	 * 载入输入流
	 * 
	 * @param in
	 *            文件输入流
	 */
	public void load(InputStream in) {
		try {
			if (mapper.isEmpty()) {
				throw new ExcelCastException("the mapper relation is not set");
			}
			Workbook workbook = WorkbookFactory.create(in);
			if (sheetName == null) {
				sheetName = workbook.getSheetName(sheetIndex);
			}
			sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new ExcelCastException("sheet name \"" + sheetName
						+ "\" can not be found");
			}
			columnCount = mapper.size();
			int rowCount = sheet.getLastRowNum();
			if (rowCount < 1) {
				throw new ExcelCastException("the sheet \"" + sheetName
						+ "\" is invalid or empty");
			}
			init();
		} catch (Throwable e) {
			throw new UnexpectedException(e);
		} finally {
			IOUtil.close(in);
		}
	}

	/**
	 * 解析excel文档内容成集合对象
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> toList() {
		List<E> pojoList = new ArrayList<E>();
		Iterator<Row> rows = sheet.iterator();
		rows.next();
		while (rows.hasNext()) {
			pojoList.add((E) parseRow(rows.next()));
		}
		return pojoList;
	}

	/**
	 * 校验excel文档内容
	 * 
	 * @param validation
	 *            Validation
	 * @throws ValidationCastException
	 *             校验不通过而抛出的异常
	 */
	public void validate(Validation validation) throws ValidationCastException {
		Iterator<Row> rows = sheet.iterator();
		rows.next();
		while (rows.hasNext()) {
			validate(validation, rows.next());
			if (!(Boolean) vmap.get(VALIDITY)) {
				throw new ValidationCastException(vmap.get(EXTRA).toString());
			}
		}
	}

	/**
	 * 设置读取的工作表索引, 默认读取索引为0的工作表
	 * 
	 * @param index
	 *            工作表索引
	 */
	public void setSheetIndex(int index) {
		this.sheetIndex = index;
	}

	/**
	 * 设置日期格式
	 * 
	 * @param dateFormat
	 *            日期格式
	 */
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * 解析行内容成对象
	 * 
	 * @param row
	 *            行对象
	 * @return
	 */
	private Object parseRow(Row row) {
		final Object bean = BeanClass.newInstance(beanClass);
		new OnReadableRowReady(this, row) {
			@Override
			public void ready(int columnIndex, Class<?> propertyType,
					String propertyName, String stringCellValue) {
				Object propertyValue = ConvertUtil.convert(stringCellValue,
						propertyType);
				wrapper.setPropertyValue(bean, propertyName, propertyValue);
			}
		}.startup();
		return bean;
	}

	private static final Short EXTRA = 1;
	private static final Short VALIDITY = 2;
	private Map<Short, Object> vmap = new HashMap<Short, Object>(2, 1);

	/**
	 * 校验行内容
	 * 
	 * @param validation
	 *            Validation
	 * @param row
	 *            行对象
	 */
	private void validate(final Validation validation, final Row row) {
		new OnReadableRowReady(this, row) {
			@Override
			public void ready(int columnIndex, Class<?> propertyType,
					String propertyName, String stringCellValue) {
				boolean validity = validation.validate(propertyType,
						propertyName, stringCellValue);
				vmap.put(VALIDITY, validity);
				if (!validity) {
					String extra = errorOf(propertyType, stringCellValue,
							row.getRowNum(), columnIndex, validation.getCause());
					vmap.put(EXTRA, extra);
					interrupt();
				}
			}
		}.startup();
	}

	private static final String G = "General";

	/**
	 * 判断单元格内容格式是否是日期格式
	 * 
	 * @param cell
	 *            单元格对象
	 * @return
	 */
	private boolean isCellDateFormatted(Cell cell) {
		String pattern = cell.getCellStyle().getDataFormatString();
		if (pattern != G && pattern.length() > 1) {
			if (pattern.contains("y") || pattern.contains("m")
					|| pattern.contains("d") || pattern.contains("h")
					|| pattern.contains("s")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取单元格格式为日期格式的字符串值
	 * 
	 * @param cell
	 *            单元格对象
	 * @return
	 */
	private String getCellDateFormatString(Cell cell) {
		Date date = cell.getDateCellValue();
		if (dateFormat == null) {
			return DateUtil.format(date);
		} else {
			return DateUtil.format(date, dateFormat);
		}
	}

	/**
	 * 构建异常信息
	 * 
	 * @param type
	 *            目标类型
	 * @param value
	 *            字符串类型值
	 * @param row
	 *            单元格所在的行
	 * @param column
	 *            单元格所在的列
	 * @param cause
	 *            校验失败原因
	 * @return
	 */
	private String errorOf(Class<?> type, String value, int row, int column,
			String cause) {
		StringBuilder message = new StringBuilder();
		message.append("【").append(++row).append("行,");
		message.append(ColumnIndexConverter.getIndex(++column));
		message.append("列】校验未通过, \"").append(value).append("\" ");
		if (cause == null) {
			message.append("无法转换成期望的");
			message.append(typeNameConverter(type)).append("类型。");
		} else {
			message.append(cause);
		}
		return message.toString();
	}

	/**
	 * 类型名称转换
	 * 
	 * @param type
	 *            类型
	 * @return
	 */
	private String typeNameConverter(Class<?> type) {
		String typeName = type.getSimpleName();
		if (TypeUtil.isInteger(type)) {
			typeName = "整数[" + typeName + "]";
		}
		if (TypeUtil.isDecimal(type)) {
			typeName = "小数[" + typeName + "]";
		}
		if (TypeUtil.isBooleanType(type)) {
			typeName = "布尔[" + typeName + "]";
		}
		if (type == Date.class) {
			typeName = "日期[" + typeName + "]";
		}
		if (type == String.class) {
			typeName = "字符串[" + typeName + "]";
		}
		return typeName;
	}

	/**
	 * 初始化
	 */
	private void init() {
		titles = new LinkedList<String>();
		Row titleRow = sheet.getRow(TITLE_ROW_NUMBER);
		Iterator<Cell> cells = titleRow.cellIterator();
		while (cells.hasNext()) {
			Cell cell = cells.next();
			if (cell.getColumnIndex() >= columnCount) {
				break;
			}
			titles.add(cell.getStringCellValue());
		}
	}

	/**
	 * 行对象解析包装
	 * 
	 * @author Lychie Fan
	 */
	private static abstract class OnReadableRowReady {

		private Row row;
		private ReadableExcel re;
		private boolean interrupted;

		private OnReadableRowReady(ReadableExcel re, Row row) {
			this.re = re;
			this.row = row;
		}

		/**
		 * 打断后续操作
		 */
		public void interrupt() {
			this.interrupted = true;
		}

		/**
		 * 就绪
		 * 
		 * @param columnIndex
		 *            单元格所在的列的索引值
		 * @param propertyType
		 *            属性类型
		 * @param propertyName
		 *            属性名称
		 * @param stringCellValue
		 *            单元格内容串的值
		 */
		public abstract void ready(int columnIndex, Class<?> propertyType,
				String propertyName, String stringCellValue);

		/**
		 * 开始解析工作
		 */
		public void startup() {
			Iterator<Cell> cells = row.cellIterator();
			while (cells.hasNext() && !interrupted) {
				Cell cell = cells.next();
				int columnIndex = cell.getColumnIndex();
				if (columnIndex >= re.columnCount) {
					break;
				}
				String stringCellValue;
				if (re.isCellDateFormatted(cell)) {
					stringCellValue = re.getCellDateFormatString(cell);
				} else {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					stringCellValue = cell.getStringCellValue();
				}
				String propertyName = re.mapper.get(re.titles.get(columnIndex));
				Class<?> propertyType = re.wrapper
						.getPropertyType(propertyName);
				ready(columnIndex, propertyType, propertyName, stringCellValue);
			}
		}
	}

}