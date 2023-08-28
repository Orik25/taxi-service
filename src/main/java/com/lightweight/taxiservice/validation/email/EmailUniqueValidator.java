package com.lightweight.taxiservice.validation.email;

import com.lightweight.taxiservice.DAO.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EmailUniqueValidator implements ConstraintValidator<UniqueEmail, String> {
    private UserRepository userRepository;

    public EmailUniqueValidator() {
    }

    @Autowired
    public EmailUniqueValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(email);
        return !userRepository.findByEmail(email).isPresent();

    }
}
