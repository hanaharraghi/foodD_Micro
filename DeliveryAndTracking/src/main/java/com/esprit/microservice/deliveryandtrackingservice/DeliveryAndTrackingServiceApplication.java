package com.esprit.microservice.deliveryandtrackingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class DeliveryAndTrackingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryAndTrackingServiceApplication.class, args);
    }

}
