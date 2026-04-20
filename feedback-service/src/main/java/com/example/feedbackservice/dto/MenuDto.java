package com.example.feedbackservice.dto;

import org.springframework.cloud.openfeign.FeignClient;


public class MenuDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category;
    private Boolean available;

    public MenuDto() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}