package com.gs.student.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {

	public static final String salt = "1a2b3c4d5e";
	public static String md5(String str) {
		return DigestUtils.md5Hex(str);
	}
	
	public static String ipnutPassFormPass(String iputPass) {
		String pass = salt.charAt(2)+salt.charAt(1)+iputPass+salt.charAt(4)+salt.charAt(7);
		return md5(pass);
	}
	
	public static String formPassToDBPass(String str,String salt) {
		String pass = salt.charAt(2)+salt.charAt(1)+str+salt.charAt(4)+salt.charAt(7);
		return md5(pass);
	}
	public static void main(String[] args) {
		//System.out.println(ipnutPassFormPass("123456"));
		System.out.println(formPassToDBPass("123456","b60142d315e4596cd2d8744352ffd1ec"));
	}
}
