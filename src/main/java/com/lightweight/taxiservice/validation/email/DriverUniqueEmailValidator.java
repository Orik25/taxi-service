package com.lightweight.taxiservice.validation.email;

import com.lightweight.taxiservice.DAO.DriverRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DriverUniqueEmailValidator implements ConstraintValidator<DriverUniqueEmail, String> {
    private DriverRepository driverRepository;

    public DriverUniqueEmailValidator() {
    }

    @Autowired
    public DriverUniqueEmailValidator(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return driverRepository.findByEmail(email).isEmpty();
    }
}
