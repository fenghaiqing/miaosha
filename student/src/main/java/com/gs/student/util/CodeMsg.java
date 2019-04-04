package com.gs.student.util;

public enum CodeMsg {

	ERROR_O1(500,"系统错误"),
	PHONE_NUM_ISNULL(501,"手机号空！"),
	PHONE_NUM_WRONGFUL(502,"手机号格式不正确"),
	USERNAME_OR_PASSWORD_IS_NULL(503,"用户名密码不能为空！"),
	USER_NOT_EXIST(504,"用户不存在!"),
	USER_EXIST(1505,"用户已存在!"),
	ACCOUNT_EXIST(1505,"账号已存在!"),
	PASSWORD_ERROR(505,"密码错误"),
	VALIDATE_EXCEPTION(506,""),
	PARAM_ERROR(1507,"参数异常！"),
	STD_NUMBER_ERROR(1507,"学号不能为空"),
	STD_ClASS_ERROR(1508,"班级不能为空！"),
	STD_DORM_ERROR(1509,"宿舍不能为空！"),
	STD_NAME_ERROR(1510,"姓名不能为空！"), 
	STD_SEX_ERROR(1511,"性别不能为空！"),
	STD_EXIST(1512,"该学生已存在！"), 
	DORM_IS_FUll(1513,"宿舍已满员！"),
	DORM_NAME_ERROR(1514,"宿舍编号不能为空！"),
	DORM_SEX_ERROR(1515,"宿舍性别属性不能为空！"),
	DORM_EXIST(1516,"宿舍信息已存在！"),
	DORM_EXIST_STUDENT(1517,"学生信息与该宿舍信息有关联，不能直接删除！"),
	CLASSNAME_ERROR(1518,"班级名称不能为空！"), 
	TEACHER_NAME_ERROR(1519,"班主任名称不能为空！"),
	TEACHER_NUM_ERROR(1520,"班主任编号不能为空！"),
	CLASS_EXIST(1521,"班级已存在！"),
	CLASS_EXIST_STUDENT(1522,"学生信息中包含该班级信息，不能直接删除！"),
	DORM_IS_NOT_EXIST(1523,"宿舍不存在！"), 
	OLD_PASSWORD_ERROR(1524,"原密码输入有误，请重新输入！"), 
	PASSWORD_CONFORM_ERROR(1525,"两次密码输入不一致，请检查！"), 
	PASSWORD_SAM_ERROR(1526,"新密码不能与旧密码相同！");
	
	
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
