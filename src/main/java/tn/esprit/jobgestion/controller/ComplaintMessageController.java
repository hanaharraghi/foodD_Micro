package tn.esprit.jobgestion.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.jobgestion.entity.ComplaintMessage;
import tn.esprit.jobgestion.service.ComplaintMessageService;

import java.util.List;

@RestController
@RequestMapping("/api/complaints/{complaintId}/messages")
public class ComplaintMessageController {

    private final ComplaintMessageService messageService;

    public ComplaintMessageController(ComplaintMessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ComplaintMessage addMessage(@PathVariable Long complaintId,
                                       @RequestBody ComplaintMessage message) {
        return messageService.addMessage(complaintId, message);
    }

    @GetMapping
    public List<ComplaintMessage> getMessages(@PathVariable Long complaintId) {
        return messageService.getMessagesByComplaint(complaintId);
    }
}