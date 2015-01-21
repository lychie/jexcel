package org.lychie.jexcel.model;

/**
 * 标题行
 * 
 * @author Lychie Fan
 */
public final class Title extends RowStyle {

	private Title() {
		init();
	}

	/**
	 * 单元格宽度
	 */
	private int width;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width * 256;
	}

	private void init() {
		autoWrap = true;
		height = 20;
		fontSize = 12;
		fontColor = COLOR_BLUE_GREY;
		fontStyle = FONT_STYLE_NORMAL;
		fontWeight = FONT_WEIGHT_NORMAL;
		backgroundColor = COLOR_YELLOW;
		verticalAlignment = VERTICAL_CENTER;
		horizontalAlignment = HORIZONTAL_CENTER;
		setBorderColor(COLOR_GREY_25_PERCENT);
		setBorder(BORDER_SOLID);
		setWidth(15);
	}

}