package tn.esprit.jobgestion.controller;



import tn.esprit.jobgestion.entity.Menu;
import tn.esprit.jobgestion.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuController {

    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    // Menus of one restaurant
    @GetMapping("/api/restaurants/{restaurantId}/menus")
    public List<Menu> allByRestaurant(@PathVariable Long restaurantId) {
        return service.getAllByRestaurant(restaurantId);
    }

    @PostMapping("/api/restaurants/{restaurantId}/menus")
    public Menu create(@PathVariable Long restaurantId, @RequestBody Menu menu) {
        return service.create(restaurantId, menu);
    }

    // CRUD Menu by id
    @GetMapping("/api/menus/{id}")
    public Menu one(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/api/menus/{id}")
    public Menu update(@PathVariable Long id, @RequestBody Menu menu) {
        return service.update(id, menu);
    }

    @DeleteMapping("/api/menus/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

