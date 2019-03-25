package com.gs.miaosha.result;

public enum CodeMsg {

	ERROR_O1(500,"系统错误"),
	PHONE_NUM_ISNULL(501,"手机号为空"),
	PHONE_NUM_WRONGFUL(502,"手机号格式不正确"),
	PASSWORD_IS_NULL(503,"密码不能为空"),
	USER_NOT_EXIST(504,"用户不存在"),
	PASSWORD_ERROR(505,"密码错误"),
	VALIDATE_EXCEPTION(506,""),
	STOCK_COUNT_LESS(507,"库存不足"),
	REPEAT_BUY_ERROR(508,"已经秒杀成功，不能重复秒杀！"),
	NO_LONGIN(509,"用户没有登录！");
	
	private int code;
	private String msg;
	private CodeMsg(int code,String msg) {
		this.code=code;
		this.msg=msg;
	}
	
	  public static CodeMsg valueofKey(int key) {
	        for (CodeMsg season : CodeMsg.values()) {
	            if (season.code==key) {
	                return season;
	            }
	        }
	        throw new IllegalArgumentException("No element matches " + key);
	    }

	
	public int getCode() {
		return this.code;
	}
	public String getMsg() {
		return this.msg;
	}
}
