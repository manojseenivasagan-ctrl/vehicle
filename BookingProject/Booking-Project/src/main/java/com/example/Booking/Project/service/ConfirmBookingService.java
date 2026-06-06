package com.example.Booking.Project.service;

import com.example.Booking.Project.model.Booking;
import com.example.Booking.Project.model.ConfirmBooking;
import com.example.Booking.Project.model.customer;
import com.example.Booking.Project.repository.ConfirmBookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfirmBookingService {

    @Autowired
    ConfirmBookingRepo confirmBookingRepo;

    public void confirmbooking(String name, String mobileno, String emailId, String city,
                               String address, String pincode, String vehicleName,
                               String vehiclemodel, String servicedetails, String servicedate,
                               String servicetime, String shopname, String shopemailId, String mechanicname,
                               String mechanicmobileno, String status) {

        ConfirmBooking confirmBooking = new ConfirmBooking(name, mobileno, emailId, city,address, pincode, vehicleName, vehiclemodel, servicedetails, servicedate,servicetime, shopname, shopemailId, mechanicname, mechanicmobileno, status);

        confirmBookingRepo.save(confirmBooking);
    }

    public List<ConfirmBooking> confirmbookedlist() {
        return confirmBookingRepo.findAll();
    }

    public List<ConfirmBooking> getBookingsByEmail(String emailId) {
        return confirmBookingRepo.findByEmailId(emailId);
    }

    public List<ConfirmBooking> getBookingsByshopEmail(String shopemailId) {
        return confirmBookingRepo.findByshopemailId(shopemailId);
    }


    public boolean updateStatus(Long id, String status) {
        ConfirmBooking confirmBooking = confirmBookingRepo.findById(id).orElse(null);
        if (confirmBooking != null) {
            confirmBooking.setStatus(status);
            confirmBookingRepo.save(confirmBooking);
            return true;
        }
        return false;
    }

}
