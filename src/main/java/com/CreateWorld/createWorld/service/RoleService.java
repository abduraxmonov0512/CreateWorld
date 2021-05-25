package com.CreateWorld.createWorld.service;

import com.CreateWorld.createWorld.models.Role;
import com.CreateWorld.createWorld.models.enums.Status;
import com.CreateWorld.createWorld.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void createRole(Role role){
        role.setStatus(Status.ACTIVE);
        roleRepository.save(role);
    }

    public Role findRoleByName(String name){
        return roleRepository.findByName(name);
    }
}
