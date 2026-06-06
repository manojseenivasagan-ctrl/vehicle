package com.example.Booking.Project.repository;

import com.example.Booking.Project.model.customer;
import com.example.Booking.Project.model.mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicRepo extends JpaRepository<mechanic, Long> {

    mechanic findByEmailId(String emailId);

    // ✅ Get mechanic/shop by name
    mechanic findByName(String name);
}
