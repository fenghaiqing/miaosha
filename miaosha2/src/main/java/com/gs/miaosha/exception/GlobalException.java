package com.gs.miaosha.exception;

import com.gs.miaosha.result.CodeMsg;

public class GlobalException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private CodeMsg cd;
	
	public GlobalException(CodeMsg cd) {
		super(cd.getMsg());
		this.cd=cd;
	}

	public CodeMsg getCd() {
		return cd;
	}

	public void setCd(CodeMsg cd) {
		this.cd = cd;
	}
	

}
