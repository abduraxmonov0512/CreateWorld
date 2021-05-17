package com.CreateWorld.createWorld.service;

import com.CreateWorld.createWorld.models.Role;
import com.CreateWorld.createWorld.models.User;
import com.CreateWorld.createWorld.repositories.RoleRepository;
import com.CreateWorld.createWorld.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public User register(User user){
        Role role = roleRepository.findByName("ROLE_ADMIN");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(role);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        User userRegister = userRepository.save(user);

        log.info("User successfully registered: {}", userRegister);
        return userRegister;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

}
