package tn.esprit.jobgestion.service;


import tn.esprit.jobgestion.entity.Restaurant;
import tn.esprit.jobgestion.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository repo;

    public RestaurantService(RestaurantRepository repo) {
        this.repo = repo;
    }

    public List<Restaurant> getAll() {
        return repo.findAll();
    }

    public Restaurant getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found: " + id));
    }

    public Restaurant create(Restaurant r) {
        return repo.save(r);
    }

    public Restaurant update(Long id, Restaurant r) {
        Restaurant existing = getById(id);
        existing.setName(r.getName());
        existing.setAddress(r.getAddress());
        existing.setPhone(r.getPhone());
        existing.setImage(r.getImage());
        existing.setDescription(r.getDescription());
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}

