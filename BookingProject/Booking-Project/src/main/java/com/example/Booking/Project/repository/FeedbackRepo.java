package com.example.Booking.Project.repository;

import com.example.Booking.Project.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {

    // Make sure property name matches entity field
    List<Feedback> findByCustomerEmail(String customerEmail);
    List<Feedback> findByShopemail(String shopemail);

}
