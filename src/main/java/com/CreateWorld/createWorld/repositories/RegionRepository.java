package com.CreateWorld.createWorld.repositories;

import com.CreateWorld.createWorld.models.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {


}
