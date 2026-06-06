package com.example.Booking.Project.controller;

import com.example.Booking.Project.model.Feedback;
import com.example.Booking.Project.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // allow requests from HTML page
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // Save feedback with all details
    @PostMapping("/submit")
    public String submitFeedback(@RequestBody Feedback feedback) {
        feedbackService.saveFeedback(feedback);
        return "Feedback submitted successfully!";
    }

    // Optional: fetch feedback by customer email
    @GetMapping("/list/{email}")
    public List<Feedback> getFeedbackByEmail(@PathVariable String email) {
        return feedbackService.getFeedbackByCustomerEmail(email);
    }

    @GetMapping("/list/shop/{shopemail}")
    public List<Feedback> getFeedbackByshopEmail(@PathVariable String shopemail) {
        return feedbackService.getFeedbackByshopEmail(shopemail);
    }

}
