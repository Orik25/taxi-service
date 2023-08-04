package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findUsersByRoleName(String role);

    Optional<User> findByEmail(String email);
}
