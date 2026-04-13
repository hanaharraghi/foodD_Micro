package tn.esprit.jobgestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.jobgestion.entity.ComplaintAttachment;

import java.util.List;

public interface ComplaintAttachmentRepository extends JpaRepository<ComplaintAttachment, Long> {

    List<ComplaintAttachment> findByComplaintId(Long complaintId);
}