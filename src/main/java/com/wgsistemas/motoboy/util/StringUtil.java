package com.wgsistemas.motoboy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static boolean matchesEmailPattern(String input) {
		Pattern pattern = Pattern.compile(StringUtil.EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
}