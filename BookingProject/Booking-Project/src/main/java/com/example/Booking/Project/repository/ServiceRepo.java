package com.example.Booking.Project.repository;

import com.example.Booking.Project.model.accident;
import com.example.Booking.Project.model.service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepo extends JpaRepository<service, Long> {
    List<service> findByregisternum(String registernum);
}