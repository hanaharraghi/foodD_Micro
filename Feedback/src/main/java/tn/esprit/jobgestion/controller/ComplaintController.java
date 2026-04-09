package tn.esprit.jobgestion.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.jobgestion.entity.*;
import tn.esprit.jobgestion.service.ComplaintService;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping
    public Complaint create(@RequestBody Complaint complaint) {
        return complaintService.createComplaint(complaint);
    }

    @GetMapping
    public List<Complaint> getAll() {
        return complaintService.getAllComplaints();
    }

    @GetMapping("/{id}")
    public Complaint getById(@PathVariable Long id) {
        return complaintService.getComplaintById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Complaint> getByCustomer(@PathVariable Long customerId) {
        return complaintService.getComplaintsByCustomer(customerId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Complaint> getByRestaurant(@PathVariable Long restaurantId) {
        return complaintService.getComplaintsByRestaurant(restaurantId);
    }

    @PutMapping("/{id}/status")
    public Complaint updateStatus(@PathVariable Long id, @RequestParam ComplaintStatus status) {
        return complaintService.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
    }
}