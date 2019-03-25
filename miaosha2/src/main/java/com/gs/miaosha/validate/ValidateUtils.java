package com.gs.miaosha.validate;

import java.util.regex.Pattern;

public class ValidateUtils {

	/**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	
    public static boolean isMobile(String value) {
		return Pattern.matches(REGEX_MOBILE, value);
	}
}
