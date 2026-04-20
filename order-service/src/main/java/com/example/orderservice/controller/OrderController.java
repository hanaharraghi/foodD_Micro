package com.example.orderservice.controller;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public List<OrderEntity> getByStatus(@PathVariable String status) {
        return orderRepository.findByStatusIgnoreCase(status);
    }

    @GetMapping("/customer/{customerName}")
    public List<OrderEntity> getByCustomerName(@PathVariable String customerName) {
        return orderRepository.findByCustomerNameIgnoreCase(customerName);
    }

    @PostMapping
    public ResponseEntity<OrderEntity> createOrder(@Valid @RequestBody OrderEntity order) {
        order.setId(null);
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderEntity> updateOrder(@PathVariable Long id, @RequestBody OrderEntity order) {
        return orderRepository.findById(id)
                .map(existing -> {
                    existing.setOrderNumber(order.getOrderNumber() != null ? order.getOrderNumber() : existing.getOrderNumber());
                    existing.setCustomerName(order.getCustomerName() != null ? order.getCustomerName() : existing.getCustomerName());
                    existing.setMenuName(order.getMenuName() != null ? order.getMenuName() : existing.getMenuName());
                    existing.setTotalPrice(order.getTotalPrice() != null ? order.getTotalPrice() : existing.getTotalPrice());
                    existing.setStatus(order.getStatus() != null ? order.getStatus() : existing.getStatus());
                    return ResponseEntity.ok(orderRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        orderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome from order-service";
    }
}
