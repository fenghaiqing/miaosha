package com.gs.miaosha.exception;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gs.miaosha.result.CodeMsg;
import com.gs.miaosha.result.Result;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	public Result<String> exceptionHandler(HttpServletRequest requestHandler,Exception e){
	
		if(e instanceof GlobalException){
			GlobalException gx = (GlobalException)e;
			String msg = gx.getMessage();
			return Result.ERROR(CodeMsg.VALIDATE_EXCEPTION, msg);
		}else if(e instanceof BindException) {
			BindException bindException = (BindException)e;
			List<ObjectError> errors = bindException.getAllErrors();
			String error = errors.get(0).getDefaultMessage();
			return Result.ERROR(CodeMsg.VALIDATE_EXCEPTION,error);
		}else {
			return Result.ERROR(CodeMsg.ERROR_O1);
		}
	}
}
