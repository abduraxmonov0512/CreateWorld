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

import static com.CreateWorld.createWorld.models.enums.Status.ACTIVE;

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

    public void register(User user){
//        Role role = roleRepository.findByName("ROLE_ADMIN");
//        System.out.println(role.getName()+ " ROLE_NAME");
//        List<Role> userRoles = new ArrayList<>();
//        userRoles.add(role);
        System.out.println(user.getPassword() + " PASSWORD");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus(ACTIVE);
     //   user.setRoles(userRoles);
        User userRegister = userRepository.save(user);

        log.info("User successfully registered: {}", userRegister);
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
