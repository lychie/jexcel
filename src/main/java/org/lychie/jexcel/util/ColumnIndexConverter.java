package org.lychie.jexcel.util;

/**
 * 列索引转换器
 * 
 * @author Lychie Fan
 */
public class ColumnIndexConverter {

	private static final int MIN_OFFSET = 1;
	private static final int MAX_OFFSET = 26;

	/**
	 * 获取列索引名称
	 * 
	 * @param index
	 *            索引值
	 * @return
	 */
	public static String getIndex(int index) {
		return getOffset(index);
	}

	private static String getOffset(int index) {
		int offset = index / MAX_OFFSET;
		int remain = index % MAX_OFFSET;
		if (remain == 0) {
			offset--;
			remain = MAX_OFFSET;
		}
		if (offset <= MAX_OFFSET) {
			String result = "";
			Object offsetObj = convertToLetter(offset);
			Object remainObj = convertToLetter(remain);
			if (offsetObj != null) {
				result += offsetObj;
			}
			if (remainObj != null) {
				result += remainObj;
			}
			return result;
		} else {
			String result = getOffset(offset);
			Object remainObj = convertToLetter(remain);
			if (remainObj != null) {
				result += remainObj;
			}
			return result;
		}
	}

	private static Object convertToLetter(int index) {
		if (index >= MIN_OFFSET && index <= MAX_OFFSET) {
			return (char) (index - 1 + 'A');
		}
		return null;
	}

}