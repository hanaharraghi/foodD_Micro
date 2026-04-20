package com.example.feedbackservice.controller;

import com.example.feedbackservice.client.MenuClient;
import com.example.feedbackservice.client.UserClient;
import com.example.feedbackservice.dto.MenuDto;
import com.example.feedbackservice.dto.UserDto;
import com.example.feedbackservice.entity.Feedback;
import com.example.feedbackservice.repository.FeedbackRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    private final FeedbackRepository feedbackRepository;
    private final UserClient userClient;
    private final MenuClient menuClient;

    public FeedbackController(
            FeedbackRepository feedbackRepository,
            UserClient userClient,
            MenuClient menuClient
    ) {
        this.feedbackRepository = feedbackRepository;
        this.userClient = userClient;
        this.menuClient = menuClient;
    }

    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        return feedbackRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Feedback> getByUserId(@PathVariable Long userId) {
        return feedbackRepository.findByUserId(userId);
    }

    @GetMapping("/menu/{menuId}")
    public List<Feedback> getByMenuId(@PathVariable Long menuId) {
        return feedbackRepository.findByMenuId(menuId);
    }

    @PostMapping
    public ResponseEntity<?> createFeedback(@Valid @RequestBody Feedback feedback) {
        try {
            UserDto user = userClient.getUserById(feedback.getUserId());
            if (user == null || user.getId() == null) {
                return ResponseEntity.badRequest().body("User not found: " + feedback.getUserId());
            }

            MenuDto menu = menuClient.getMenuById(feedback.getMenuId());
            if (menu == null || menu.getId() == null) {
                return ResponseEntity.badRequest().body("Menu not found: " + feedback.getMenuId());
            }

            feedback.setId(null);
            feedback.setCreatedAt(LocalDateTime.now());

            return ResponseEntity.ok(feedbackRepository.save(feedback));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Feign error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        if (!feedbackRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        feedbackRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome from feedback-service";
    }
}