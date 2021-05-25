package com.CreateWorld.createWorld.rest;


import com.CreateWorld.createWorld.dto.HomeDto;
import com.CreateWorld.createWorld.models.Home;
import com.CreateWorld.createWorld.models.Role;
import com.CreateWorld.createWorld.models.User;
import com.CreateWorld.createWorld.service.HomeService;
import com.CreateWorld.createWorld.service.RoleService;
import com.CreateWorld.createWorld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static com.CreateWorld.createWorld.models.enums.Status.ACTIVE;

@RestController
@RequestMapping("/api/adminDistrict")
public class AdminDistrictController {


    private HomeService homeService;
    private RoleService roleService;
    private UserService userService;

    @Autowired
    public AdminDistrictController(HomeService homeService, RoleService roleService, UserService userService) {
        this.homeService = homeService;
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        Role role = roleService.findRoleByName("ROLE_ADMIN_HOME");
        user.setRoles(Collections.singletonList(role));
        user.setStatus(ACTIVE);
        userService.register(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/addHome")
    public ResponseEntity<Home> addHome(@RequestBody HomeDto dto){
        return ResponseEntity.ok(homeService.createHome(dto));
    }
}
