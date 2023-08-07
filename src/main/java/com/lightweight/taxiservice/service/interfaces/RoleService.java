package com.lightweight.taxiservice.service.interfaces;

import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.entity.Role;
import com.lightweight.taxiservice.entity.User;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findById(Long id);
    Role save(Role role);
    Role update(Role role);
    void deleteById(Long id);
}
