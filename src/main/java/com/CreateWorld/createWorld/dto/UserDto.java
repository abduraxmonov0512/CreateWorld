package com.CreateWorld.createWorld.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserDto {

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private long home_id;
}
