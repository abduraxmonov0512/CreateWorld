package com.CreateWorld.createWorld.service;


import com.CreateWorld.createWorld.dto.DistrictDto;
import com.CreateWorld.createWorld.models.District;
import com.CreateWorld.createWorld.models.Region;
import com.CreateWorld.createWorld.models.User;
import com.CreateWorld.createWorld.repositories.DistrictRepository;
import com.CreateWorld.createWorld.repositories.RegionRepository;
import com.CreateWorld.createWorld.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {


    private DistrictRepository districtRepository;
    private UserRepository userRepository;
    private RegionService regionService;

    @Autowired
    public DistrictService(DistrictRepository districtRepository, UserRepository userRepository, RegionService regionService) {
        this.districtRepository = districtRepository;
        this.userRepository = userRepository;
        this.regionService = regionService;
    }

    public District saveDistrict(DistrictDto dto){
        District result = new District();
        result.setName(dto.getName());
        User user = userRepository.findById(dto.getLeader_id()).get();
        result.setUser(user);
        Region region = regionService.findById(dto.getRegion_id());
        List<District> districts = region.getDistricts();
        districts.add(result);
        region.setDistricts(districts);
        regionService.updateRegion(region);
        return districtRepository.save(result);
    }
}
