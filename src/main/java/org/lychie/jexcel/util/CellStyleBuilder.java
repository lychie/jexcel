package org.lychie.jexcel.util;

import org.apache.poi.ss.usermodel.Font;
import org.lychie.jexcel.model.RowStyle;
import org.lychie.jexcel.model.Stylesheet;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * 单元格样式构建者
 * 
 * @author Lychie Fan
 */
public class CellStyleBuilder {

	private static final String systemDefaultFontName;

	/**
	 * 构建单元格样式对象
	 * 
	 * @param workbook
	 *            Workbook
	 * @param rowStyle
	 *            行样式对象
	 * @param dataFormat
	 *            数据格式
	 * @return
	 */
	public static CellStyle buildCellStyle(Workbook workbook, RowStyle rowStyle, 
			String dataFormat) {

		CellStyle style = workbook.createCellStyle();

		if (rowStyle.getHorizontalAlignment() != Stylesheet.NONE_STYLE) {
			style.setAlignment(rowStyle.getHorizontalAlignment());
		}

		if (rowStyle.getBorderBottom() != Stylesheet.NONE_STYLE) {
			style.setBorderBottom(rowStyle.getBorderBottom());
		}

		if (rowStyle.getBorderLeft() != Stylesheet.NONE_STYLE) {
			style.setBorderLeft(rowStyle.getBorderLeft());
		}

		if (rowStyle.getBorderRight() != Stylesheet.NONE_STYLE) {
			style.setBorderRight(rowStyle.getBorderRight());
		}

		if (rowStyle.getBorderTop() != Stylesheet.NONE_STYLE) {
			style.setBorderTop(rowStyle.getBorderTop());
		}

		if (rowStyle.getBorderBottomColor() != Stylesheet.NONE_STYLE) {
			style.setBottomBorderColor(rowStyle.getBorderBottomColor());
		}

		if (rowStyle.getBackgroundColor() != Stylesheet.NONE_STYLE) {
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(rowStyle.getBackgroundColor());
		}

		if (rowStyle.getBorderLeftColor() != Stylesheet.NONE_STYLE) {
			style.setLeftBorderColor(rowStyle.getBorderLeftColor());
		}

		if (rowStyle.getBorderRightColor() != Stylesheet.NONE_STYLE) {
			style.setRightBorderColor(rowStyle.getBorderRightColor());
		}

		if (rowStyle.getBorderTopColor() != Stylesheet.NONE_STYLE) {
			style.setTopBorderColor(rowStyle.getBorderTopColor());
		}

		if (rowStyle.getVerticalAlignment() != Stylesheet.NONE_STYLE) {
			style.setVerticalAlignment(rowStyle.getVerticalAlignment());
		}

		style.setWrapText(rowStyle.isAutoWrap());

		style.setFont(buildFont(workbook, rowStyle));
		
		DataFormat format = workbook.createDataFormat();
		
		style.setDataFormat(format.getFormat(dataFormat));

		return style;
	}

	/**
	 * 构建字体对象
	 * 
	 * @param workbook
	 *            Workbook
	 * @param rowStyle
	 *            行样式对象
	 * @return
	 */
	private static Font buildFont(Workbook workbook, RowStyle rowStyle) {

		Font font = workbook.createFont();

		if (rowStyle.getFontWeight() != Stylesheet.NONE_STYLE) {
			font.setBoldweight(rowStyle.getFontWeight());
		}

		if (rowStyle.getFontColor() != Stylesheet.NONE_STYLE) {
			font.setColor(rowStyle.getFontColor());
		}

		if (rowStyle.getFontSize() != Stylesheet.NONE_STYLE) {
			font.setFontHeightInPoints(rowStyle.getFontSize());
		}

		if (rowStyle.getFontName() != null) {
			font.setFontName(rowStyle.getFontName());
		} else {
			font.setFontName(systemDefaultFontName);
		}

		if (rowStyle.getFontStyle() != Stylesheet.NONE_STYLE) {
			font.setItalic(rowStyle.getFontStyle() == Stylesheet.FONT_STYLE_ITALIC);
		}

		return font;
	}

	static {
		String systemName = System.getProperty("os.name");
		if (systemName.contains("Windows")) {
			systemDefaultFontName = "微软雅黑";
		} else {
			systemDefaultFontName = null;
		}
	}

}