package com.gs.miaosha.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class IsMobileValidator implements ConstraintValidator<Mobile, String> {

	private boolean required = false;
	@Override
	public  void initialize(Mobile constraintAnnotation) {
		required = constraintAnnotation.required();
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(required) {
			return ValidateUtils.isMobile(value);
		}else {
			if(StringUtils.isEmpty(value)) {
				return true;
			}else {
				return ValidateUtils.isMobile(value);
			}
		}
	}

}
