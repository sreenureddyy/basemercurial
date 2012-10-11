package com.sree.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {
	public static String RegEx(String Expres, String Replacement, String strTMP) {
		StringBuffer sb = new StringBuffer();

		Pattern p = Pattern.compile(Expres);
		Matcher m = p.matcher(strTMP);
		boolean result = m.find();

		while (result) {
			m.appendReplacement(sb, Replacement);
			result = m.find();
		}
		m.appendTail(sb);
		return sb.toString();
	}
}