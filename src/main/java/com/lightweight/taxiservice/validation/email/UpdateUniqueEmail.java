package com.lightweight.taxiservice.validation.email;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UpdateUniqueEmailValidator.class)
public @interface UpdateUniqueEmail {
    String message() default "User with this email has already exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}