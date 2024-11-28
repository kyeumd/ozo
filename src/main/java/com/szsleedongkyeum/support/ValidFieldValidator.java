package com.szsleedongkyeum.support;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidFieldValidator implements ConstraintValidator<ValidRequired, String> {

    private String fieldName;

    @Override
    public void initialize(ValidRequired annotation) {
        this.fieldName = annotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (validatingNotBlank(value, context)) {
            return false;
        }

        if (validatingWhiteSpace(value, context)) {
            return false;
        }

        return true;
    }

    private boolean validatingNotBlank(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldName + "은(는) 필수 입력입니다.")
                   .addConstraintViolation();
            return true;
        }
        return false;
    }

    private boolean validatingWhiteSpace(String value, ConstraintValidatorContext context) {
        if (!value.matches("^(\\S.*\\S|\\S)$")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldName + "의 앞뒤 공백은 허용되지 않습니다.")
                   .addConstraintViolation();
            return true;
        }
        return false;
    }
}
