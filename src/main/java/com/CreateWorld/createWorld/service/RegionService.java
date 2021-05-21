package com.CreateWorld.createWorld.service;

import com.CreateWorld.createWorld.dto.RegionDto;
import com.CreateWorld.createWorld.models.Region;
import com.CreateWorld.createWorld.models.User;
import com.CreateWorld.createWorld.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService {


    private RegionRepository regionRepository;
    private UserService userService;

    @Autowired
    public RegionService(RegionRepository regionRepository, UserService userService) {
        this.regionRepository = regionRepository;
        this.userService = userService;
    }

    public Region saveRegion(RegionDto regionDto){
        Region region = new Region();
        region.setName(regionDto.getName());
        User user = userService.findById(regionDto.getLeader_id());
        region.setUser(user);
        return regionRepository.save(region);
    }

}
