package com.example.feedbackservice.client;

import com.example.feedbackservice.dto.MenuDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MENU-SERVICE")
public interface MenuClient {

    @GetMapping("/menus/{id}")
    MenuDto getMenuById(@PathVariable("id") Long id);
}