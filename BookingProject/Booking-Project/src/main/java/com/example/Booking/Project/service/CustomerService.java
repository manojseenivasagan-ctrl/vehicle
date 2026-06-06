package com.example.Booking.Project.service;

import com.example.Booking.Project.model.customer;
import com.example.Booking.Project.repository.CustomerLoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerLoginRepo customerLoginRepo;

    public void customers(String name, String emailId, String password) {
        customer customers = new customer(name,emailId,password);
        customerLoginRepo.save(customers);
    }

    public List<customer> customerslist() {
        return customerLoginRepo.findAll();
    }

    public customer getByEmail(String emailId) {
        return customerLoginRepo.findByEmailId(emailId);
    }
}
