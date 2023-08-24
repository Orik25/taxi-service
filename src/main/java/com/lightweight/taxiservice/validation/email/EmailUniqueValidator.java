package com.lightweight.taxiservice.validation.email;

import com.lightweight.taxiservice.DAO.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class  EmailUniqueValidator implements ConstraintValidator<UniqueEmail , String> {
    private String email;
    private UserRepository userRepository;

    public EmailUniqueValidator() {
    }

    @Autowired
    public EmailUniqueValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueEmail email) {
        this.email = email.value();
    }
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        boolean isPresent = userRepository.findByEmail(email).isPresent();
        if (isPresent){
           return false;
        }
        else {
            return true;
        }
    }


}
