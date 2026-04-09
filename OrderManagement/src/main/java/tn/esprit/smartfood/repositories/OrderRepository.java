package tn.esprit.smartfood.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.smartfood.entities.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByStatus(String status);
    List<Order> findByCustomerId(Integer customerId);
    List<Order> findByUserId(Long userId);
}
