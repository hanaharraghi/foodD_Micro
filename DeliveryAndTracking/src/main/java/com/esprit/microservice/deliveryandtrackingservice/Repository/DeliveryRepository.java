package com.esprit.microservice.deliveryandtrackingservice.Repository;

import com.esprit.microservice.deliveryandtrackingservice.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;  // ← AJOUTEZ CECI

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}