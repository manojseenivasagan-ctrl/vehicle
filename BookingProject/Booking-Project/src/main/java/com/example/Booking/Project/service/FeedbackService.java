package com.example.Booking.Project.service;

import com.example.Booking.Project.model.Feedback;
import com.example.Booking.Project.repository.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo;

    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepo.save(feedback);
    }

    public List<Feedback> getFeedbackByCustomerEmail(String email) {
        return feedbackRepo.findByCustomerEmail(email);
    }

    public List<Feedback> getFeedbackByshopEmail(String shopemail) {
        return feedbackRepo.findByShopemail(shopemail);
    }
}
