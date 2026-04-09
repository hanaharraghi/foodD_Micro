package tn.esprit.smartfood.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.smartfood.entities.Order;
import tn.esprit.smartfood.repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;


    public List<Order> getOrdersByUser(Long userId) {
        return repo.findByUserId(userId);
    }



    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public List<Order> getAll() {
        return repo.findAll();
    }

    public Order addOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        if (order.getStatus() == null) {
            order.setStatus("CREATED");
        }
        return repo.save(order);
    }

    public Order updateOrder(Integer id, Order newOrder) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setStatus(newOrder.getStatus());
                    existing.setTotalAmount(newOrder.getTotalAmount());
                    existing.setDeliveryAddress(newOrder.getDeliveryAddress());
                    existing.setAdditionalNotes(newOrder.getAdditionalNotes());
                    existing.setCustomerId(newOrder.getCustomerId());
                    existing.setRestaurantId(newOrder.getRestaurantId());
                    existing.setDriverId(newOrder.getDriverId());
                    existing.setUpdatedAt(LocalDateTime.now());
                    return repo.save(existing);
                })
                .orElse(null);
    }

    public String deleteOrder(Integer id) {
        if (repo.findById(id).isPresent()) {
            repo.deleteById(id);
            return "order deleted";
        }
        return "order not deleted";
    }
}


