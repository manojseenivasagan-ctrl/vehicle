package com.example.Booking.Project.service;

import com.example.Booking.Project.model.Booking;
import com.example.Booking.Project.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepo bookingRepo;

    public List<Booking> bookedlist() {
        return bookingRepo.findAll();
    }

    public void booking(String name, String mobileno, String emailId, String city, String address, String pincode, String vehicleName, String vehiclemodel, String servicedetails, String servicedate, String servicetime, String status) {
       Booking booking = new Booking(name,mobileno,emailId,city,address,pincode,vehicleName,vehiclemodel,servicedetails,servicedate,servicetime,status);
       bookingRepo.save(booking);
    }

    public boolean updateStatus(Long id, String status) {
        Booking booking = bookingRepo.findById(id).orElse(null);
        if (booking != null) {
            booking.setStatus(status);
            bookingRepo.save(booking);
            return true;
        }
        return false;
    }

}
