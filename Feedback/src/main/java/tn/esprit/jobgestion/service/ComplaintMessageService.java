package tn.esprit.jobgestion.service;

import org.springframework.stereotype.Service;
import tn.esprit.jobgestion.entity.Complaint;
import tn.esprit.jobgestion.entity.ComplaintMessage;
import tn.esprit.jobgestion.repository.ComplaintMessageRepository;
import tn.esprit.jobgestion.repository.ComplaintRepository;

import java.util.List;

@Service
public class ComplaintMessageService {

    private final ComplaintMessageRepository messageRepository;
    private final ComplaintRepository complaintRepository;

    public ComplaintMessageService(ComplaintMessageRepository messageRepository,
                                   ComplaintRepository complaintRepository) {
        this.messageRepository = messageRepository;
        this.complaintRepository = complaintRepository;
    }

    public ComplaintMessage addMessage(Long complaintId, ComplaintMessage message) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + complaintId));
        message.setComplaint(complaint);
        return messageRepository.save(message);
    }

    public List<ComplaintMessage> getMessagesByComplaint(Long complaintId) {
        return messageRepository.findByComplaintId(complaintId);
    }
}