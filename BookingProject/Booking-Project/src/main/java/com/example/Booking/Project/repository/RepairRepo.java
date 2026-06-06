package com.example.Booking.Project.repository;

import com.example.Booking.Project.model.accident;
import com.example.Booking.Project.model.repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepo extends JpaRepository<repair, Long> {
    List<repair> findByregisternum(String registernum);
}