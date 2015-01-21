package org.lychie.jexcel.model;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * 样式表
 * 
 * @author Lychie Fan
 */
public interface Stylesheet {

	/**
	 * 垂直方向居上
	 */
	short VERTICAL_TOP = 0x0;

	/**
	 * 垂直方向居中
	 */
	short VERTICAL_CENTER = 0x1;

	/**
	 * 垂直方向居下
	 */
	short VERTICAL_BOTTOM = 0x2;

	/**
	 * 水平方向居左
	 */
	short HORIZONTAL_LEFT = 0x1;

	/**
	 * 水平方向居中
	 */
	short HORIZONTAL_CENTER = 0x2;

	/**
	 * 水平方向居右
	 */
	short HORIZONTAL_RIGHT = CellStyle.ALIGN_RIGHT;

	/**
	 * 边框无
	 */
	short BORDER_NONE = 0x0;

	/**
	 * 边框 solid
	 */
	short BORDER_SOLID = 0x1;

	/**
	 * 边框 dashed
	 */
	short BORDER_DASHED = 0x3;

	/**
	 * 边框 dotted
	 */
	short BORDER_DOTTED = 0x7;

	/**
	 * 边框 double
	 */
	short BORDER_DOUBLE = 0x6;

	/**
	 * 字体样式 斜体
	 */
	short FONT_STYLE_ITALIC = 0x1;

	/**
	 * 字体样式 正常
	 */
	short FONT_STYLE_NORMAL = 0x2;

	/**
	 * 字体粗细 粗体
	 */
	short FONT_WEIGHT_BOLD = 0x2bc;

	/**
	 * 字体粗细 正常
	 */
	short FONT_WEIGHT_NORMAL = 0x190;
	
	/**
	 * 无样式
	 */
	short NONE_STYLE = 0xffffffff;

	/**
	 * 颜色
	 */
	short COLOR_BLACK = 0x8;

	/**
	 * 颜色
	 */
	short COLOR_BLUE = 0xc;

	/**
	 * 颜色
	 */
	short COLOR_BLUE_GREY = 0x36;

	/**
	 * 颜色
	 */
	short COLOR_BRIGHT_GREEN = 0xb;

	/**
	 * 颜色
	 */
	short COLOR_BROWN = 0x3c;

	/**
	 * 颜色
	 */
	short COLOR_GOLD = 0x33;

	/**
	 * 颜色
	 */
	short COLOR_GREEN = 0x11;

	/**
	 * 颜色
	 */
	short COLOR_LIGHT_BLUE = 0x30;

	/**
	 * 颜色
	 */
	short COLOR_LIGHT_GREEN = 0x2a;

	/**
	 * 颜色
	 */
	short COLOR_LIGHT_ORANGE = 0x34;

	/**
	 * 颜色
	 */
	short COLOR_ORANGE = 0x35;

	/**
	 * 颜色
	 */
	short COLOR_YELLOW = 0xd;

	/**
	 * 颜色
	 */
	short COLOR_PINK = 0xe;

	/**
	 * 颜色
	 */
	short COLOR_RED = 0xa;

	/**
	 * 颜色
	 */
	short COLOR_SKY_BLUE = 0x28;

	/**
	 * 颜色
	 */
	short COLOR_WHITE = 0x9;

	/**
	 * 颜色
	 */
	short COLOR_GREY_25_PERCENT = 0x16;

	/**
	 * 颜色
	 */
	short COLOR_GREY_40_PERCENT = 0x37;

	/**
	 * 颜色
	 */
	short COLOR_GREY_50_PERCENT = 0x17;

	/**
	 * 颜色
	 */
	short COLOR_GREY_80_PERCENT = 0x3f;

}