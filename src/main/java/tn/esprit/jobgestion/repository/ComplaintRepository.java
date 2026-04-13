package tn.esprit.jobgestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.jobgestion.entity.Complaint;
import tn.esprit.jobgestion.entity.ComplaintStatus;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByCustomerId(Long customerId);

    List<Complaint> findByRestaurantId(Long restaurantId);

    List<Complaint> findByStatus(ComplaintStatus status);
}