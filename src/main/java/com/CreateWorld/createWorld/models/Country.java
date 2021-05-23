package com.CreateWorld.createWorld.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "country")
public class Country extends BaseEntity {
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "country_regions",
            joinColumns = {@JoinColumn(name = "country_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "region_id", referencedColumnName = "id")})
    private List<Region> regions;
}
