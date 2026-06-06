package com.example.Booking.Project.repository;

import com.example.Booking.Project.model.ConfirmBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfirmBookingRepo extends JpaRepository<ConfirmBooking, Long> {
    List<ConfirmBooking> findByEmailId(String emailId);
    List<ConfirmBooking> findByshopemailId(String shopemailId);
}

