package com.lightweight.taxiservice.validation.email;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DriverUniqueEmailValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DriverUniqueEmail {

    public String value() default "";

    public String message() default "Driver with this email has already exist!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
