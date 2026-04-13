package tn.esprit.jobgestion.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.jobgestion.entity.Feedback;
import tn.esprit.jobgestion.service.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public Feedback create(@RequestBody Feedback feedback) {
        return feedbackService.createFeedback(feedback);
    }

    @GetMapping
    public List<Feedback> getAll() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/customer/{customerId}")
    public List<Feedback> getByCustomer(@PathVariable Long customerId) {
        return feedbackService.getFeedbacksByCustomer(customerId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Feedback> getByRestaurant(@PathVariable Long restaurantId) {
        return feedbackService.getFeedbacksByRestaurant(restaurantId);
    }

    @GetMapping("/order/{orderId}")
    public List<Feedback> getByOrder(@PathVariable Long orderId) {
        return feedbackService.getFeedbacksByOrder(orderId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
    }
}