package tn.esprit.jobgestion.service;

import org.springframework.stereotype.Service;
import tn.esprit.jobgestion.entity.*;
import tn.esprit.jobgestion.repository.*;

import java.util.List;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public Complaint createComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + id));
    }

    public List<Complaint> getComplaintsByCustomer(Long customerId) {
        return complaintRepository.findByCustomerId(customerId);
    }

    public List<Complaint> getComplaintsByRestaurant(Long restaurantId) {
        return complaintRepository.findByRestaurantId(restaurantId);
    }

    public List<Complaint> getComplaintsByStatus(ComplaintStatus status) {
        return complaintRepository.findByStatus(status);
    }

    public Complaint updateStatus(Long id, ComplaintStatus status) {
        Complaint complaint = getComplaintById(id);
        complaint.setStatus(status);
        return complaintRepository.save(complaint);
    }

    public void deleteComplaint(Long id) {
        complaintRepository.deleteById(id);
    }
}