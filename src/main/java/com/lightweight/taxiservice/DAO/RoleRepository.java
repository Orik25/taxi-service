package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.entity.Role;
import com.lightweight.taxiservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

}
