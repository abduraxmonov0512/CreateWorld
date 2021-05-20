package com.CreateWorld.createWorld.rest;

import com.CreateWorld.createWorld.models.Role;
import com.CreateWorld.createWorld.repositories.RoleRepository;
import com.CreateWorld.createWorld.service.RoleService;
import com.CreateWorld.createWorld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class RoleController {

    @Autowired
    private RoleService service;

    @PostMapping(value = "/auth/role")
    public ResponseEntity<String> createRole(@RequestBody Role role){
        service.createRole(role);
        return ResponseEntity.ok("created");
    }
}
