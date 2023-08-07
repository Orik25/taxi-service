package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.RoleRepository;
import com.lightweight.taxiservice.entity.Role;
import com.lightweight.taxiservice.exception.*;
import com.lightweight.taxiservice.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = roleRepository.findAll();
        if (!roles.isEmpty()) {
            return roles;
        } else {
            throw new NoRolesFoundException("There are no roles exist");
        }
    }

    @Override
    public Role findById(Long id) {
        isDatabaseEmpty();
        return roleRepository.findById(id)
                .orElseThrow(() -> new NoRoleFoundException("Role not found with id: " + id));
    }

    @Override
    public Role save(Role role) throws NoRoleFoundException{
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        isDatabaseEmpty();
        Long id = role.getId();
        roleRepository.findById(id)
                .orElseThrow(() -> new NoRoleFoundException("Impossible to update the Role. Role not found with id: " + id));
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.findById(id)
                .orElseThrow(() -> new NoRoleFoundException("That role cannot be deleted because " +
                        "role with id: " + id + " not found"));
        roleRepository.deleteById(id);
    }

    private void isDatabaseEmpty() {
        roleRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new NoRoleFoundException("Data base has not any records of roles"));
    }
}
