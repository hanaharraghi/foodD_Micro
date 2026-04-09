package tn.esprit.jobgestion.service;

import org.springframework.stereotype.Service;
import tn.esprit.jobgestion.entity.Feedback;
import tn.esprit.jobgestion.repository.FeedbackRepository;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public List<Feedback> getFeedbacksByCustomer(Long customerId) {
        return feedbackRepository.findByCustomerId(customerId);
    }

    public List<Feedback> getFeedbacksByRestaurant(Long restaurantId) {
        return feedbackRepository.findByRestaurantId(restaurantId);
    }

    public List<Feedback> getFeedbacksByOrder(Long orderId) {
        return feedbackRepository.findByOrderId(orderId);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}