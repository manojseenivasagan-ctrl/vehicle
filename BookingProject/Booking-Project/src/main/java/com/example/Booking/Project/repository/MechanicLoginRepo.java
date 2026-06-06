package com.example.Booking.Project.repository;

import com.example.Booking.Project.model.shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicLoginRepo extends JpaRepository<shop,Long> {
    shop findByEmailId(String emailId);
}
