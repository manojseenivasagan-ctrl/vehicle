package com.example.Booking.Project.service;

import com.example.Booking.Project.model.customer;
import com.example.Booking.Project.model.mechanic;
import com.example.Booking.Project.repository.BookingRepo;
import com.example.Booking.Project.repository.MechanicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MechanicService {

    @Autowired
    MechanicRepo mechanicRepo;

    public List<mechanic> mechanicList() {
        return mechanicRepo.findAll();
    }

    public void mechanics(String name, String mobileno, String emailId, String city, String address, String zipcode, Integer count, String VehicleName, String opentime, String closetime) {
        mechanic mechanics = new mechanic(name, mobileno, emailId, city, address, zipcode, count, VehicleName, opentime, closetime);
        mechanicRepo.save(mechanics);
    }

    public mechanic getByEmail(String emailId) {
        return mechanicRepo.findByEmailId(emailId);
    }

    // ✅ New method to fetch mechanic by name
    public mechanic getByName(String name) {
        return mechanicRepo.findByName(name);
    }
}

