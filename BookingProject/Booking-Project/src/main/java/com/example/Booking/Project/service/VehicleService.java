package com.example.Booking.Project.service;

import com.example.Booking.Project.model.vehicle;
import com.example.Booking.Project.repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleRepo vehicleRepo;

    public List<vehicle> Vehicleslist() {
        return vehicleRepo.findAll();
    }

    public void vehicles(String registernum, String owner, String address, String email, String mobileno, String makeandmodel, String fueltype, String Chassisno, String Engineno) {
        vehicle Vehicle = new vehicle(registernum,owner,address,email,mobileno,makeandmodel,fueltype,Chassisno,Engineno);
        vehicleRepo.save(Vehicle);
    }


}
