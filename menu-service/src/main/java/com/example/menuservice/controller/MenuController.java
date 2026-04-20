package com.example.menuservice.controller;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.repository.MenuRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {

    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @GetMapping
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Long id) {
        return menuRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/available")
    public List<Menu> getAvailableMenus() {
        return menuRepository.findByAvailableTrue();
    }

    @GetMapping("/category/{category}")
    public List<Menu> getMenusByCategory(@PathVariable String category) {
        return menuRepository.findByCategoryIgnoreCase(category);
    }

    @PostMapping
    public ResponseEntity<Menu> createMenu(@Valid @RequestBody Menu menu) {
        menu.setId(null);
        if (menu.getAvailable() == null) {
            menu.setAvailable(true);
        }
        return ResponseEntity.ok(menuRepository.save(menu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        if (!menuRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        menuRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome from menu-service";
    }
}