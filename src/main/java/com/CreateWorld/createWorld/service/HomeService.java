package com.CreateWorld.createWorld.service;

import com.CreateWorld.createWorld.dto.HomeDto;
import com.CreateWorld.createWorld.models.Home;
import com.CreateWorld.createWorld.models.User;
import com.CreateWorld.createWorld.repositories.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {


    private UserService userService;
    private HomeRepository homeRepository;

    @Autowired
    public HomeService(UserService userService, HomeRepository homeRepository) {
        this.userService = userService;
        this.homeRepository = homeRepository;
    }


    public Home createHome(HomeDto dto){
        Home result = new Home();
        result.setName(dto.getName());
        User user = userService.findById(dto.getLeader_id());
        result.setUser(user);
        homeRepository.save(result);
        return result;
    }

}
