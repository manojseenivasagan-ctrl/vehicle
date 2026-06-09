package com.example.Booking.Project.controller;

import com.example.Booking.Project.model.Booking;
import com.example.Booking.Project.service.BookingService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("Booking/list")
    public List<Booking> bookedlist(){
        return bookingService.bookedlist();
    }

    @PostMapping("/Booking/book")
    public String booking(@RequestParam("name") String name,
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
                          @RequestParam("status") String status){
        bookingService.booking(name,mobileno,emailId,city,address,pincode,vehicleName,vehiclemodel,servicedetails,servicedate,servicetime,status);
        return "Congratulations! "+name+" Booked Mechanic Successfully on "+ servicedate +" - "+ servicetime;
    }

    @PostMapping("/Booking/update")
    public String updateBookingStatus(@RequestParam("id") Long id,
                                      @RequestParam("status") String status) {
        boolean updated = bookingService.updateStatus(id, status);
        if (updated) {
            return "Booking status updated successfully!";
        } else {
            return "Booking not found!";
        }
    }



}
