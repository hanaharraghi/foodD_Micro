package com.esprit.microservice.deliveryandtrackingservice.Service;

import com.esprit.microservice.deliveryandtrackingservice.entity.Delivery;        // ← AJOUTEZ CECI
import com.esprit.microservice.deliveryandtrackingservice.Repository.DeliveryRepository;  // ← AJOUTEZ CECI
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository repo;

    public Delivery create(Delivery d) {
        d.setTrackingNumber("TRK-" + UUID.randomUUID().toString().substring(0,8));
        d.setStatus("PENDING");
        return repo.save(d);
    }

    public List<Delivery> findAll() { return repo.findAll(); }

    public Delivery findById(Long id) { return repo.findById(id).orElse(null); }

    public Delivery update(Long id, Delivery d) {
        Delivery existing = findById(id);
        if (existing != null) {
            existing.setSenderName(d.getSenderName());
            existing.setReceiverName(d.getReceiverName());
            existing.setStatus(d.getStatus());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(Long id) { repo.deleteById(id); }
}