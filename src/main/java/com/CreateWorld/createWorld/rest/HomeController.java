package com.CreateWorld.createWorld.rest;

import com.CreateWorld.createWorld.dto.UserDto;
import com.CreateWorld.createWorld.models.Home;
import com.CreateWorld.createWorld.models.Role;
import com.CreateWorld.createWorld.models.User;
import com.CreateWorld.createWorld.models.enums.Status;
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

@RestController
@RequestMapping("/api/adminHome")
public class HomeController {

    private RoleService roleService;
   private UserService userService;
   private HomeService homeService;


   @Autowired
    public HomeController(RoleService roleService, UserService userService, HomeService homeService) {
        this.roleService = roleService;
        this.userService = userService;
       this.homeService = homeService;
   }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserDto dto){
       User user = new User();
       user.setUsername(dto.getUsername());
       user.setPassword(dto.getPassword());
       user.setEmail(dto.getEmail());
       user.setFirstname(dto.getFirstname());
       user.setLastname(dto.getLastname());
       user.setStatus(Status.ACTIVE);
       Role role = roleService.findRoleByName("ROLE_HOME_PEOPLE");
       user.setRoles(Collections.singletonList(role));
       userService.register(user);
        Home home = homeService.findById(dto.getHome_id());
        homeService.update(home);
       return ResponseEntity.ok(user);
   }
}
