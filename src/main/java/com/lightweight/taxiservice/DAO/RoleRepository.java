package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findFirstByOrderByIdAsc();

    Optional<Role> findByName(String name);
}
