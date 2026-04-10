package com.esprit.microservice.deliveryandtrackingservice.Controller;

import com.esprit.microservice.deliveryandtrackingservice.Service.DeliveryService;  // ← AJOUTEZ
import com.esprit.microservice.deliveryandtrackingservice.entity.Delivery;          // ← AJOUTEZ
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService service;

    @PostMapping
    public Delivery create(@RequestBody Delivery d) { return service.create(d); }

    @GetMapping
    public List<Delivery> findAll() { return service.findAll();
    }

    @GetMapping("/{id}")
    public Delivery findById(@PathVariable Long id) { return service.findById(id);
    }

    @PutMapping("/{id}")
    public Delivery update(@PathVariable Long id, @RequestBody Delivery d) { return service.update(id, d); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}