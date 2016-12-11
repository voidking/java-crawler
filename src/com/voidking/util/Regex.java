package com.voidking.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	public static boolean checkUrl(String urlStr) {

		String patternStr = "(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*";
		Pattern pattern = Pattern.compile(patternStr);
		
		Matcher matcher = pattern.matcher(urlStr);

		if (matcher.find()) {
			
			return true;
		}
		return false;
	}
}
