package com.CreateWorld.createWorld.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Home extends BaseEntity {

    private String name;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
