package com.example.Booking.Project.repository;

import com.example.Booking.Project.model.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerLoginRepo extends JpaRepository<customer, Long> {
    customer findByEmailId(String emailId); // now works correctly
}
