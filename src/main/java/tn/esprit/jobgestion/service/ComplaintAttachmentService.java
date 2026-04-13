package tn.esprit.jobgestion.service;

import org.springframework.stereotype.Service;
import tn.esprit.jobgestion.entity.Complaint;
import tn.esprit.jobgestion.entity.ComplaintAttachment;
import tn.esprit.jobgestion.repository.ComplaintAttachmentRepository;
import tn.esprit.jobgestion.repository.ComplaintRepository;

import java.util.List;

@Service
public class ComplaintAttachmentService {

    private final ComplaintAttachmentRepository attachmentRepository;
    private final ComplaintRepository complaintRepository;

    public ComplaintAttachmentService(ComplaintAttachmentRepository attachmentRepository,
                                      ComplaintRepository complaintRepository) {
        this.attachmentRepository = attachmentRepository;
        this.complaintRepository = complaintRepository;
    }

    public ComplaintAttachment addAttachment(Long complaintId, ComplaintAttachment attachment) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + complaintId));
        attachment.setComplaint(complaint);
        return attachmentRepository.save(attachment);
    }

    public List<ComplaintAttachment> getAttachmentsByComplaint(Long complaintId) {
        return attachmentRepository.findByComplaintId(complaintId);
    }
}