package com.gs.miaosha.validate;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)  // 约束注解应用的时机
@Documented
@Constraint(validatedBy = {IsMobileValidator.class }) // 与约束注解关联的验证器
public @interface Mobile {
	
	boolean required() default true;
	String message() default "手机号码格式有误";

	  Class<?>[] groups() default { };
	  
	  Class<? extends Payload>[] payload() default { };
}
