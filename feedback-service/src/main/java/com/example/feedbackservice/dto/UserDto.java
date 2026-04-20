package com.example.feedbackservice.dto;

import org.springframework.cloud.openfeign.FeignClient;


public class UserDto {

    private Long id;
    private String name;
    private String email;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}