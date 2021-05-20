package com.CreateWorld.createWorld.rest;

import com.CreateWorld.createWorld.dto.AuthenticationRequestDto;
import com.CreateWorld.createWorld.models.Role;
import com.CreateWorld.createWorld.models.User;
import com.CreateWorld.createWorld.repositories.RoleRepository;
import com.CreateWorld.createWorld.security.jwt.JwtTokenProvider;
import com.CreateWorld.createWorld.service.UserService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/auth")
public class Controller {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private RoleRepository roleRepository;

    @Autowired
    public Controller(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto dto) throws NotFoundException {
        System.out.println("TEEEEEEEEEEEEEEEEEEEEEEEEEEEST");
        try {
            String username = dto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, dto.getPassword()));
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new NotFoundException("User not found");
            }
            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password");
        }

    }


    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {

        List<Role> list = new ArrayList<>();
        Role role = roleRepository.findByName("ROLE_ADMIN");
        list.add(role);
        user.setRoles(list);
        System.out.println(role.getName());
        userService.register(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }


}
