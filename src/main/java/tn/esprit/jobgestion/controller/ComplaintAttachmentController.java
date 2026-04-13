package tn.esprit.jobgestion.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.jobgestion.entity.ComplaintAttachment;
import tn.esprit.jobgestion.service.ComplaintAttachmentService;

import java.util.List;

@RestController
@RequestMapping("/api/complaints/{complaintId}/attachments")
public class ComplaintAttachmentController {

    private final ComplaintAttachmentService attachmentService;

    public ComplaintAttachmentController(ComplaintAttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping
    public ComplaintAttachment addAttachment(@PathVariable Long complaintId,
                                             @RequestBody ComplaintAttachment attachment) {
        return attachmentService.addAttachment(complaintId, attachment);
    }

    @GetMapping
    public List<ComplaintAttachment> getAttachments(@PathVariable Long complaintId) {
        return attachmentService.getAttachmentsByComplaint(complaintId);
    }
}