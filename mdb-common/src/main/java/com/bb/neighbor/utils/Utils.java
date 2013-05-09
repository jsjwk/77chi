package com.bb.neighbor.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 工具类
 * 
 * @author song.zhang@boco.jp
 * 
 */
public class Utils {
	/**
	 * 判断字符串是否都是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (StringUtils.isBlank(str))
			return false;
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
