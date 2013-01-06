package com.mike.util;

public class StringUtil {
	public static String subString(String str,Integer length){
		Integer size = str.length();
		if(size < length){
			return str;
		}
		return str.substring(0, length);
	}
}
