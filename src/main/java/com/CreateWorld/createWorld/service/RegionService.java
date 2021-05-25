package com.CreateWorld.createWorld.service;

import com.CreateWorld.createWorld.dto.RegionDto;
import com.CreateWorld.createWorld.models.Country;
import com.CreateWorld.createWorld.models.Region;
import com.CreateWorld.createWorld.models.User;
import com.CreateWorld.createWorld.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {


    private RegionRepository regionRepository;
    private UserService userService;
    private CountryService countryService;

    @Autowired
    public RegionService(RegionRepository regionRepository, UserService userService, CountryService countryService) {
        this.regionRepository = regionRepository;
        this.userService = userService;
        this.countryService = countryService;
    }

    public Region saveRegion(RegionDto regionDto){
        Region region = new Region();
        region.setName(regionDto.getName());
        User user = userService.findById(regionDto.getLeader_id());
        region.setUser(user);
        Country country = countryService.findCountryById(regionDto.getCountry_id());
        List<Region> regionList = country.getRegions();
        regionList.add(region);
        country.setRegions(regionList);
        countryService.update(country);
        return regionRepository.save(region);
    }

    public Region findById(Long id){
        return regionRepository.findById(id).get();
    }

    public void updateRegion(Region region){
        regionRepository.save(region);
    }

}
