package com.example.Booking.Project.controller;

import com.example.Booking.Project.model.vehicle;
import com.example.Booking.Project.service.VehicleService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("Vehicle/list")
    public List<vehicle> Vehiclelist(){
        return vehicleService.Vehicleslist();
    }

    @PostMapping("/Vehicle/Register")
    public String vehicle(@RequestParam("registernum") String registernum,
                          @RequestParam("owner") String owner,
                          @RequestParam("address") String address,
                          @RequestParam("email") String email,
                          @RequestParam("mobileno") String mobileno,
                          @RequestParam("makeandmodel") String makeandmodel,
                          @RequestParam("fueltype") String fueltype,
                          @RequestParam("Chassisno") String Chassisno,
                          @RequestParam("Engineno") String Engineno){
        vehicleService.vehicles(registernum,owner,address,email,mobileno,makeandmodel,fueltype,Chassisno,Engineno);
        return "Congratulations! "+registernum+" VEhicle Registered Successfully";
    }
}