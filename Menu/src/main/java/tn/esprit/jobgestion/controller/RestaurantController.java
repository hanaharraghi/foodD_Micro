package tn.esprit.jobgestion.controller;


import  tn.esprit.jobgestion.entity.Restaurant;
import tn.esprit.jobgestion.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public List<Restaurant> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant one(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Restaurant create(@RequestBody Restaurant r) {
        return service.create(r);
    }

    @PutMapping("/{id}")
    public Restaurant update(@PathVariable Long id, @RequestBody Restaurant r) {
        return service.update(id, r);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
