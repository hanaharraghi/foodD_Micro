package tn.esprit.jobgestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.jobgestion.entity.Feedback;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByCustomerId(Long customerId);

    List<Feedback> findByRestaurantId(Long restaurantId);

    List<Feedback> findByOrderId(Long orderId);
}