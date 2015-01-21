package org.lychie.jexcel.model;

/**
 * 行样式
 * 
 * @author Lychie Fan
 */
public abstract class RowStyle implements Stylesheet {

	/**
	 * 高度
	 */
	protected float height;
	
	/**
	 * 背景颜色
	 */
	protected short backgroundColor;
	
	/**
	 * 垂直方向对齐方式
	 */
	protected short verticalAlignment;
	
	/**
	 * 水平方向对齐方式
	 */
	protected short horizontalAlignment;
	
	/**
	 * 上边框
	 */
	protected short borderTop;
	
	/**
	 * 上边框颜色
	 */
	protected short borderTopColor;
	
	/**
	 * 左边框
	 */
	protected short borderLeft;
	
	/**
	 * 左边框颜色
	 */
	protected short borderLeftColor;
	
	/**
	 * 右边框
	 */
	protected short borderRight;
	
	/**
	 * 右边框颜色
	 */
	protected short borderRightColor;
	
	/**
	 * 下边框
	 */
	protected short borderBottom;
	
	/**
	 * 下边框颜色
	 */
	protected short borderBottomColor;
	
	/**
	 * 自动换行
	 */
	protected boolean autoWrap;
	
	/**
	 * 字体名称
	 */
	protected String fontName;
	
	/**
	 * 字体大小
	 */
	protected short fontSize;
	
	/**
	 * 字体颜色
	 */
	protected short fontColor;
	
	/**
	 * 字体样式(斜体/正常)
	 */
	protected short fontStyle;
	
	/**
	 * 字体粗细(粗体/正常)
	 */
	protected short fontWeight;

	public float getHeight() {
		return height;
	}

	public short getBackgroundColor() {
		return backgroundColor;
	}

	public short getVerticalAlignment() {
		return verticalAlignment;
	}

	public short getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public short getBorderTop() {
		return borderTop;
	}

	public short getBorderTopColor() {
		return borderTopColor;
	}

	public short getBorderLeft() {
		return borderLeft;
	}

	public short getBorderLeftColor() {
		return borderLeftColor;
	}

	public short getBorderRight() {
		return borderRight;
	}

	public short getBorderRightColor() {
		return borderRightColor;
	}

	public short getBorderBottom() {
		return borderBottom;
	}

	public short getBorderBottomColor() {
		return borderBottomColor;
	}

	public boolean isAutoWrap() {
		return autoWrap;
	}

	public String getFontName() {
		return fontName;
	}

	public short getFontSize() {
		return fontSize;
	}

	public short getFontColor() {
		return fontColor;
	}

	public short getFontStyle() {
		return fontStyle;
	}

	public short getFontWeight() {
		return fontWeight;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setBackgroundColor(short backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setVerticalAlignment(short verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	public void setHorizontalAlignment(short horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	public void setBorderTop(short borderTop) {
		this.borderTop = borderTop;
	}

	public void setBorderTopColor(short borderTopColor) {
		this.borderTopColor = borderTopColor;
	}

	public void setBorderLeft(short borderLeft) {
		this.borderLeft = borderLeft;
	}

	public void setBorderLeftColor(short borderLeftColor) {
		this.borderLeftColor = borderLeftColor;
	}

	public void setBorderRight(short borderRight) {
		this.borderRight = borderRight;
	}

	public void setBorderRightColor(short borderRightColor) {
		this.borderRightColor = borderRightColor;
	}

	public void setBorderBottom(short borderBottom) {
		this.borderBottom = borderBottom;
	}

	public void setBorderBottomColor(short borderBottomColor) {
		this.borderBottomColor = borderBottomColor;
	}

	public void setAutoWrap(boolean autoWrap) {
		this.autoWrap = autoWrap;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public void setFontSize(short fontSize) {
		this.fontSize = fontSize;
	}

	public void setFontColor(short fontColor) {
		this.fontColor = fontColor;
	}

	public void setFontStyle(short fontStyle) {
		this.fontStyle = fontStyle;
	}

	public void setFontWeight(short fontWeight) {
		this.fontWeight = fontWeight;
	}

	public void setBorder(short border) {
		this.borderTop = border;
		this.borderLeft = border;
		this.borderRight = border;
		this.borderBottom = border;
	}

	public void setBorderColor(short borderColor) {
		this.borderTopColor = borderColor;
		this.borderLeftColor = borderColor;
		this.borderRightColor = borderColor;
		this.borderBottomColor = borderColor;
	}

}