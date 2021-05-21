package com.CreateWorld.createWorld.service;

import com.CreateWorld.createWorld.dto.AddCountryDto;
import com.CreateWorld.createWorld.models.Country;
import com.CreateWorld.createWorld.models.User;
import com.CreateWorld.createWorld.models.enums.Status;
import com.CreateWorld.createWorld.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService  {

    private UserService userService;
    private CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository, UserService userService) {
        this.countryRepository = countryRepository;
        this.userService = userService;
    }

    public Country save(AddCountryDto dto){
        User user = userService.findById(dto.getLeader_id());
        Country country = new Country();
        country.setUser(user);
        country.setName(dto.getName());
        country.setStatus(Status.ACTIVE);
        return countryRepository.save(country);
    }
}
