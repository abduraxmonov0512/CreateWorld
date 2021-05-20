package com.CreateWorld.createWorld.repositories;

import com.CreateWorld.createWorld.models.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

}
