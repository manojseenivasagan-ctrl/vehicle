package com.example.Booking.Project.controller;

import com.example.Booking.Project.model.ConfirmBooking;
import com.example.Booking.Project.service.ConfirmBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ConfirmBookingController {

    @Autowired
    ConfirmBookingService confirmBookingService;

    @GetMapping("/ConfirmBooking/list")
    public List<ConfirmBooking> bookedlist() {
        return confirmBookingService.confirmbookedlist();
    }

    @PostMapping("/ConfirmBooking")
    public String confirmbooking(
            @RequestParam("name") String name,
            @RequestParam("mobileno") String mobileno,
            @RequestParam("emailId") String emailId,
            @RequestParam("city") String city,
            @RequestParam("address") String address,
            @RequestParam("pincode") String pincode,
            @RequestParam("VehicleName") String vehicleName,
            @RequestParam("vehiclemodel") String vehiclemodel,
            @RequestParam("servicedetails") String servicedetails,
            @RequestParam("servicedate") String servicedate,
            @RequestParam("servicetime") String servicetime,
            @RequestParam("shopname") String shopname,
            @RequestParam("shopemailId") String shopemailId,
            @RequestParam("mechanicname") String mechanicname,
            @RequestParam("mechanicmobileno") String mechanicmobileno,
            @RequestParam("status") String status) {

        confirmBookingService.confirmbooking(name, mobileno, emailId, city, address, pincode,
                vehicleName, vehiclemodel, servicedetails, servicedate, servicetime,
                shopname, shopemailId, mechanicname, mechanicmobileno, status);

        return "✅ Congratulations " + name + "! Mechanic booked successfully on " + servicedate + " at " + servicetime + ".";
    }

    @GetMapping("/ConfirmBooking/list/{emailId}")
    public List<ConfirmBooking> getBookingsByEmail(@PathVariable String emailId) {
        return confirmBookingService.getBookingsByEmail(emailId);
    }

    @GetMapping("/shopConfirmBooking/list/{shopemailId}")
    public List<ConfirmBooking> getBookingsByshopEmail(@PathVariable String shopemailId) {
        return confirmBookingService.getBookingsByshopEmail(shopemailId);
    }

    @PostMapping("/ConfirmBooking/update")
    public String updateBookingStatus(@RequestParam("id") Long id,
                                      @RequestParam("status") String status) {
        boolean updated = confirmBookingService.updateStatus(id, status);
        if (updated) {
            return "Booking status updated successfully!";
        } else {
            return "Booking not found!";
        }

    }
}
