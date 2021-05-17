package com.CreateWorld.createWorld.security;

import com.CreateWorld.createWorld.models.User;
import com.CreateWorld.createWorld.security.jwt.JwtUser;
import com.CreateWorld.createWorld.security.jwt.JwtUserFactory;
import com.CreateWorld.createWorld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService service;

    @Autowired
    public JwtUserDetailsService(UserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.findByUsername(username);

        if(user == null){
            throw  new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.createJwtUser(user);
        return jwtUser;
    }
}
