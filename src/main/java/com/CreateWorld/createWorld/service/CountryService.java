package com.CreateWorld.createWorld.service;

import com.CreateWorld.createWorld.models.Country;
import com.CreateWorld.createWorld.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService  {

    @Autowired
    private CountryRepository countryRepository;

    public Country save(Country country){
        return countryRepository.save(country);
    }
}
