package com.CreateWorld.createWorld.rest;


import com.CreateWorld.createWorld.models.Country;
import com.CreateWorld.createWorld.models.Role;
import com.CreateWorld.createWorld.models.User;
import com.CreateWorld.createWorld.repositories.CountryRepository;
import com.CreateWorld.createWorld.repositories.RoleRepository;
import com.CreateWorld.createWorld.service.CountryService;
import com.CreateWorld.createWorld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminController {


    private UserService userService;
    private RoleRepository roleRepository;
    private CountryService countryService;

    @Autowired
    public AdminController(UserService userService, RoleRepository roleRepository, CountryService countryService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.countryService = countryService;
    }


    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user){
        List<Role> list = new ArrayList<>();
        Role role = roleRepository.findByName("ROLE_ADMIN_COUNTRY");
        System.out.println(role.getName());
        list.add(role);
        user.setRoles(list);
        userService.register(user);
        return ResponseEntity.status(200).body("user created");
    }

    @PostMapping("/addCountry")
    public ResponseEntity<Country> addCountry(@RequestBody Country country){
        return ResponseEntity.ok(countryService.save(country));
    }
}
