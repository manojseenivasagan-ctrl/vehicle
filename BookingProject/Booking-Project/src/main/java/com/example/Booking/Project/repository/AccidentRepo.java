package com.example.Booking.Project.repository;
import com.example.Booking.Project.model.accident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccidentRepo extends JpaRepository<accident, Long> {
    List<accident> findByregisternum(String registernum);
}