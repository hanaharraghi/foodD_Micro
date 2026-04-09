package tn.esprit.jobgestion.service;



import tn.esprit.jobgestion.entity.Menu;
import tn.esprit.jobgestion.entity.Restaurant;
import tn.esprit.jobgestion.repository.MenuRepository;
import tn.esprit.jobgestion.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepo;
    private final RestaurantRepository restaurantRepo;

    public MenuService(MenuRepository menuRepo, RestaurantRepository restaurantRepo) {
        this.menuRepo = menuRepo;
        this.restaurantRepo = restaurantRepo;
    }

    public List<Menu> getAllByRestaurant(Long restaurantId) {
        return menuRepo.findByRestaurantId(restaurantId);
    }

    public Menu getById(Long id) {
        return menuRepo.findById(id).orElseThrow(() -> new RuntimeException("Menu not found: " + id));
    }

    public Menu create(Long restaurantId, Menu menu) {
        Restaurant r = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found: " + restaurantId));
        menu.setRestaurant(r);
        return menuRepo.save(menu);
    }

    public Menu update(Long id, Menu menu) {
        Menu existing = getById(id);
        existing.setName(menu.getName());
        existing.setDescription(menu.getDescription());
        return menuRepo.save(existing);
    }

    public void delete(Long id) {
        menuRepo.deleteById(id);
    }
}
