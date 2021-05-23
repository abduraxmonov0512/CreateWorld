package com.CreateWorld.createWorld.rest;

import com.CreateWorld.createWorld.dto.RegionDto;
import com.CreateWorld.createWorld.models.Region;
import com.CreateWorld.createWorld.models.Role;
import com.CreateWorld.createWorld.models.User;
import com.CreateWorld.createWorld.repositories.RoleRepository;
import com.CreateWorld.createWorld.service.RegionService;
import com.CreateWorld.createWorld.service.RoleService;
import com.CreateWorld.createWorld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("/api/adminCountry")
public class AdminCountryController {

    @Autowired
    private RegionService regionService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;


    @PostMapping("/addRegion")
    public ResponseEntity<Region> addRegion(@RequestBody RegionDto regionDto){
        return ResponseEntity.ok(regionService.saveRegion(regionDto));
    }

    @PostMapping("/AddRegionLeader")
    public ResponseEntity<String> addLeader(@RequestBody User user){
        Role role = roleRepository.findByName("ROLE_ADMIN_REGION");
        user.setRoles(Collections.singletonList(role));
        userService.register(user);
        return ResponseEntity.ok("created");
    }

}
