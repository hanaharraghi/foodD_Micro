package tn.esprit.jobgestion.service;



import tn.esprit.jobgestion.entity.Food;
import tn.esprit.jobgestion.entity.Menu;
import tn.esprit.jobgestion.repository.FoodRepository;
import tn.esprit.jobgestion.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepo;
    private final MenuRepository menuRepo;

    public FoodService(FoodRepository foodRepo, MenuRepository menuRepo) {
        this.foodRepo = foodRepo;
        this.menuRepo = menuRepo;
    }

    public List<Food> getAllByMenu(Long menuId) {
        return foodRepo.findByMenuId(menuId);
    }

    public Food getById(Long id) {
        return foodRepo.findById(id).orElseThrow(() -> new RuntimeException("Food not found: " + id));
    }

    public Food create(Long menuId, Food food) {
        Menu m = menuRepo.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found: " + menuId));
        food.setMenu(m);
        return foodRepo.save(food);
    }

    public Food update(Long id, Food food) {
        Food existing = getById(id);
        existing.setName(food.getName());
        existing.setDescription(food.getDescription());
        existing.setPrice(food.getPrice());
        existing.setImage(food.getImage());
        existing.setAvailable(food.isAvailable());
        return foodRepo.save(existing);
    }

    public void delete(Long id) {
        foodRepo.deleteById(id);
    }
}
