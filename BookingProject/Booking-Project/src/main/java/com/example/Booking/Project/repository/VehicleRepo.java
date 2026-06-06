package com.example.Booking.Project.repository;

import com.example.Booking.Project.model.vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<vehicle, Long> {

}

