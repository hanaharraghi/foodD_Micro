package tn.esprit.jobgestion.controller;


import tn.esprit.jobgestion.entity.Food;
import tn.esprit.jobgestion.service.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class    FoodController {

    private final FoodService service;

    public FoodController(FoodService service) {
        this.service = service;
    }

    // Foods of one menu
    @GetMapping("/api/menus/{menuId}/foods")
    public List<Food> allByMenu(@PathVariable Long menuId) {
        return service.getAllByMenu(menuId);
    }

    @PostMapping("/api/menus/{menuId}/foods")
    public Food create(@PathVariable Long menuId, @RequestBody Food food) {
        return service.create(menuId, food);
    }

    // CRUD Food by id
    @GetMapping("/api/foods/{id}")
    public Food one(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/api/foods/{id}")
    public Food update(@PathVariable Long id, @RequestBody Food food) {
        return service.update(id, food);
    }

    @DeleteMapping("/api/foods/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

