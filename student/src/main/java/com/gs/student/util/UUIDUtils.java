package com.gs.student.util;

public class UUIDUtils {

	public static String uuid() {
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}
}
