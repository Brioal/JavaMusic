package com.brioal.music.util;


/**
 * 字符串相关的工具类
 * @author brioa
 *
 */
public class StringUtil {

	
	/**
	 * 判断字符串是否可用
	 * @param str
	 * @return
	 */
	public static boolean isValiable(String str){
		if (str==null) {
			return false;
		}
		if (str.equals("")) {
			return false;
		}
		return true;
	}
}
