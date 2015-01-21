package org.lychie.jexcel.model;

/**
 * 主体行
 * 
 * @author Lychie Fan
 */
public final class Body extends RowStyle {
	
	/**
	 * 值格式
	 */
	private ValueFormat valueFormat;
	
	private Body() {
		init();
	}

	public ValueFormat getValueFormat() {
		return valueFormat;
	}

	private void init() {
		autoWrap = true;
		height = 20;
		fontSize = 11;
		fontStyle = FONT_STYLE_NORMAL;
		fontWeight = FONT_WEIGHT_NORMAL;
		fontColor = COLOR_GREY_50_PERCENT;
		verticalAlignment = VERTICAL_CENTER;
		backgroundColor = NONE_STYLE;
		horizontalAlignment = NONE_STYLE;
		setBorderColor(NONE_STYLE);
		setBorder(NONE_STYLE);
		valueFormat = new ValueFormat();
	}

}