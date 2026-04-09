package tn.esprit.jobgestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.jobgestion.entity.ComplaintMessage;

import java.util.List;

public interface ComplaintMessageRepository extends JpaRepository<ComplaintMessage, Long> {

    List<ComplaintMessage> findByComplaintId(Long complaintId);
}