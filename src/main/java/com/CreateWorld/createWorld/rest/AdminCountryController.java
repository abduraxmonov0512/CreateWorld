package com.CreateWorld.createWorld.rest;

import com.CreateWorld.createWorld.dto.RegionDto;
import com.CreateWorld.createWorld.models.Region;
import com.CreateWorld.createWorld.models.Role;
import com.CreateWorld.createWorld.models.User;
import com.CreateWorld.createWorld.repositories.RoleRepository;
import com.CreateWorld.createWorld.service.RegionService;
import com.CreateWorld.createWorld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/adminCountry")
public class AdminCountryController {


    private RegionService regionService;
    private UserService userService;
    private RoleRepository roleRepository;

    @Autowired
    public AdminCountryController(RegionService regionService, UserService userService, RoleRepository roleRepository) {
        this.regionService = regionService;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }


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


    @GetMapping("/regions")
    public ResponseEntity<Iterable<Region>> getRegions(){
        return ResponseEntity.ok(regionService.findAll());
    }
}
