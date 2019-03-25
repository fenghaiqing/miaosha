package com.gs.miaosha.vo;

import javax.validation.constraints.NotNull;

import com.gs.miaosha.validate.Mobile;

public class UserVo {

	@Mobile
	private String id;
	
	@NotNull
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
