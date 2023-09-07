package com.lightweight.taxiservice.validation.email;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DriverUpdateUniqueEmailValidator.class)
public @interface DriverUpdateUniqueEmail {
    String message() default "Driver with this email has already exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
