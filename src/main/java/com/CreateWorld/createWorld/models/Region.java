package com.CreateWorld.createWorld.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "region")
public class Region extends BaseEntity{

    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "region_districts",
            joinColumns = {@JoinColumn(name = "region_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "district_id", referencedColumnName = "id")})
    private List<District> districts;

}
