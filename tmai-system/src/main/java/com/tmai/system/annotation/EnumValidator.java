package com.tmai.system.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tommy Zeng
 * 2023/3/16 17:02
 **/
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {EnumValidator.EnumValidatorImpl.class})
public @interface EnumValidator {

    String message() default "must be any of enum {enumClass}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<?>> enumClass();

    class EnumValidatorImpl implements ConstraintValidator<EnumValidator, Object> {

        private List<String> valueList = new ArrayList<>();

        @Override
        public void initialize(EnumValidator enumValidator) {
            Class<? extends Enum<?>> enumClass = enumValidator.enumClass();
            Enum<?>[] enums = enumClass.getEnumConstants();
            for (Enum<?> e : enums) {
                valueList.add(e.name());
            }
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            if (value == null) {
                return true;
            }
            return valueList.contains(value.toString());
        }
    }
}

