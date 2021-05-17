package com.CreateWorld.createWorld.rest;

import com.CreateWorld.createWorld.dto.AuthenticationRequestDto;
import com.CreateWorld.createWorld.models.User;
import com.CreateWorld.createWorld.security.jwt.JwtTokenProvider;
import com.CreateWorld.createWorld.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class Controller {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public Controller(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto dto) throws NotFoundException {
        System.out.println("TEEEEEEEEEEEEEEEEEEEEEEEEEEEST");
        try {
            String username = dto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, dto.getPassword()));
            User user = userService.findByUsername(username);
            if(user == null){
                throw  new NotFoundException("User not found");
            }
            String token = jwtTokenProvider.create(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);

        }catch (Exception e){
             throw new BadCredentialsException("Invalid username or password");
        }

    }

    @PostMapping("/auth/register")
    public void registerUser(@RequestBody User user){
        System.out.println("SUKA");
        userService.register(user);
    }

}
