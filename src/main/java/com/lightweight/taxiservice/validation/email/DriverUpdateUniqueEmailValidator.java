package com.lightweight.taxiservice.validation.email;

import com.lightweight.taxiservice.DAO.DriverRepository;
import com.lightweight.taxiservice.DTO.driver.DriverUpdateDTO;
import com.lightweight.taxiservice.entity.Driver;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class DriverUpdateUniqueEmailValidator implements ConstraintValidator<DriverUpdateUniqueEmail, DriverUpdateDTO> {
    private DriverRepository driverRepository;

    public DriverUpdateUniqueEmailValidator(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public boolean isValid(DriverUpdateDTO driverDTO, ConstraintValidatorContext context) {
        Optional<Driver> existingDriver = driverRepository.findByEmail(driverDTO.getEmail());

        if (existingDriver.isEmpty()) {
            return true;
        }
        if (!existingDriver.get().getId().equals(driverDTO.getId())) {
            return false;
        }

        return true;
    }
}
