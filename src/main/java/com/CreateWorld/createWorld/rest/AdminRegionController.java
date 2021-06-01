package com.CreateWorld.createWorld.rest;


import com.CreateWorld.createWorld.dto.DistrictDto;
import com.CreateWorld.createWorld.models.District;
import com.CreateWorld.createWorld.models.Role;
import com.CreateWorld.createWorld.models.User;
import com.CreateWorld.createWorld.models.enums.Status;
import com.CreateWorld.createWorld.service.DistrictService;
import com.CreateWorld.createWorld.service.RoleService;
import com.CreateWorld.createWorld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("/api/adminRegion/")
public class AdminRegionController {

    private UserService userService;
    private DistrictService districtService;
    private RoleService roleService;


    @Autowired
    public AdminRegionController(UserService userService, DistrictService districtService, RoleService roleService) {
        this.userService = userService;
        this.districtService = districtService;
        this.roleService = roleService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        System.out.println("ADD USER TEEEEEEEEEEEEEEEST");
        System.out.println("PASSWORD " + user.getPassword());
        System.out.println("USERNAME " + user.getUsername());
        Role role = roleService.findRoleByName("ROLE_ADMIN_DISTRICT");
        user.setRoles(Collections.singletonList(role));
        user.setStatus(Status.ACTIVE);
        userService.register(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/createDistrict")
    public ResponseEntity<District> createDistrict(@RequestBody DistrictDto dto){
        return ResponseEntity.ok(districtService.saveDistrict(dto));
    }

    @GetMapping("/districts")
    public ResponseEntity<Iterable<District>> getAllDistricts(){
        return ResponseEntity.ok(districtService.findAll());
    }
}
