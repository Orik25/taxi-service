package com.lightweight.taxiservice.validation.email;

import com.lightweight.taxiservice.DAO.UserRepository;
import com.lightweight.taxiservice.DTO.user.UserUpdateProfileDTO;
import com.lightweight.taxiservice.entity.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class UpdateUniqueEmailValidator implements ConstraintValidator<UpdateUniqueEmail, UserUpdateProfileDTO> {
    private final UserRepository userRepository;

    public UpdateUniqueEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(UserUpdateProfileDTO userDTO, ConstraintValidatorContext context) {
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());

        if (existingUser.isEmpty()) {
            return true;
        }
        if (!existingUser.get().getId().equals(userDTO.getId())) {
            return false;
        }

        return true;
    }

}