package com.gs.miaosha.util;

import com.alibaba.fastjson.JSON;

public class JsonUtil {

	public static <T> String beanToString(T value) {
		if (value == null) {
			return null;
		}
		Class<?> clazz = value.getClass();
		if (clazz == int.class || clazz == Integer.class) {
			return String.valueOf(value);
		} else if (clazz == String.class) {
			return value.toString();
		} else if (clazz == long.class || clazz == Long.class) {
			return String.valueOf(value);
		} else {
			return JSON.toJSONString(value);
		}

	}

	@SuppressWarnings("unchecked")
	public static <T> T stringBean(String value, Class<T> clazz) {

		if (value == null || value.length() == 0 || clazz == null) {
			return null;
		} else if (clazz == int.class || clazz == Integer.class) {
			return (T) Integer.valueOf(value);
		} else if (clazz == String.class) {
			return (T) value;
		} else if (clazz == long.class || clazz == Long.class) {
			return (T) Long.valueOf(value);
		} else {
			return JSON.toJavaObject(JSON.parseObject(value), clazz);
		}

	}

}
