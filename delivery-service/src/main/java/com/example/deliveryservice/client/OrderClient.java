package com.example.deliveryservice.client;

import com.example.deliveryservice.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderClient {

    @GetMapping("/orders/{id}")
    OrderDto getOrderById(@PathVariable("id") Long id);
}