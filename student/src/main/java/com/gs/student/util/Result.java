package com.gs.student.util;

public class Result<T> {

	private int code;

	private String msg;

	private T data;

	private Result(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static <T> Result<T> SUCCESS(T data) {
		return new Result<T>(200, "success", data);
	}

	public static <T> Result<T> ERROR(CodeMsg msg) {
		return new Result<T>(msg.getCode(), msg.getMsg(), null);
	}

	public static <T> Result<T> ERROR(CodeMsg codemsg,String msg) {
		return new Result<T>(codemsg.getCode(), msg, null);
	}
	public int getCode() {
		return code;
	}

	
	public String getMsg() {
		return msg;
	}

	
	public T getData() {
		return data;
	}

	
	
	
}
