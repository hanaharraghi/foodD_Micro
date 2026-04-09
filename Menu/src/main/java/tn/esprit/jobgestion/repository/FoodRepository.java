package tn.esprit.jobgestion.repository;

import tn.esprit.jobgestion.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByMenuId(Long menuId);
}
