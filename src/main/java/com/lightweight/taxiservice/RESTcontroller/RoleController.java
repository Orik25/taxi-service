package com.lightweight.taxiservice.RESTcontroller;

import com.lightweight.taxiservice.entity.Role;
import com.lightweight.taxiservice.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.findAll();
    }

    @GetMapping("/roles/{roleId}")
    public Role getRoleById(@PathVariable Long roleId) {
        return roleService.findById(roleId);
    }

    @PostMapping("/roles")
    public Role addRole(@RequestBody Role role) {
        return roleService.save(role);
    }

    @PutMapping("/roles/{roleId}")
    public Role updateRole(@PathVariable Long roleId, @RequestBody Role updatedRole) {
        updatedRole.setId(roleId);
        return roleService.update(updatedRole);
    }

    @DeleteMapping("/roles/{roleId}")
    public void deleteRole(@PathVariable Long roleId) {
        Role deletedRole = roleService.findById(roleId);
        roleService.deleteById(roleId);
    }
}
