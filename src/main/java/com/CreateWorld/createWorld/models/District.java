package com.CreateWorld.createWorld.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class District extends BaseEntity{

    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "district_homes",
            joinColumns = {@JoinColumn(name = "district_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "home_id", referencedColumnName = "id")})
    private List<Region> regions;

}
