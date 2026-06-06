package com.example.Booking.Project.service;

import com.example.Booking.Project.model.shop;
import com.example.Booking.Project.repository.MechanicLoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShopService {

    @Autowired
    private MechanicLoginRepo mechanicLoginRepo;

    // Get all shops
    public List<shop> shopslist() {
        return mechanicLoginRepo.findAll();
    }

    // Save a new shop
    public void shops(String name, String emailId, String password) {
        shop shops = new shop(name, emailId, password);
        mechanicLoginRepo.save(shops);
    }

    // Find shop by email
    public shop getByEmail(String emailId) {
        return mechanicLoginRepo.findByEmailId(emailId);
    }
}
