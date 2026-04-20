package com.example.deliveryservice.controller;

import com.example.deliveryservice.client.OrderClient;
import com.example.deliveryservice.dto.OrderDto;
import com.example.deliveryservice.entity.Delivery;
import com.example.deliveryservice.repository.DeliveryRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryRepository deliveryRepository;
    private final OrderClient orderClient;

    public DeliveryController(DeliveryRepository deliveryRepository, OrderClient orderClient) {
        this.deliveryRepository = deliveryRepository;
        this.orderClient = orderClient;
    }

    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable Long id) {
        return deliveryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public List<Delivery> getByStatus(@PathVariable String status) {
        return deliveryRepository.findByStatusIgnoreCase(status);
    }

    @GetMapping("/order/{orderId}")
    public List<Delivery> getByOrderId(@PathVariable Long orderId) {
        return deliveryRepository.findByOrderId(orderId);
    }

    @PostMapping
    public ResponseEntity<?> createDelivery(@Valid @RequestBody Delivery delivery) {
        try {
            OrderDto order = orderClient.getOrderById(delivery.getOrderId());

            if (order == null || order.getId() == null) {
                return ResponseEntity.badRequest().body("Order not found");
            }

            delivery.setId(null);
            return ResponseEntity.ok(deliveryRepository.save(delivery));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Order not found or order-service unavailable");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable Long id, @RequestBody Delivery delivery) {
        return deliveryRepository.findById(id)
                .map(existing -> {
                    existing.setOrderId(delivery.getOrderId() != null ? delivery.getOrderId() : existing.getOrderId());
                    existing.setCustomerName(delivery.getCustomerName() != null ? delivery.getCustomerName() : existing.getCustomerName());
                    existing.setAddress(delivery.getAddress() != null ? delivery.getAddress() : existing.getAddress());
                    existing.setStatus(delivery.getStatus() != null ? delivery.getStatus() : existing.getStatus());
                    existing.setTransportType(delivery.getTransportType() != null ? delivery.getTransportType() : existing.getTransportType());
                    return ResponseEntity.ok(deliveryRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        if (!deliveryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        deliveryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome from delivery-service";
    }
}