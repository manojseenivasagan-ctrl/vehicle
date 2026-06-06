package com.example.Booking.Project.controller;

import com.example.Booking.Project.model.repair;
import com.example.Booking.Project.model.service;
import com.example.Booking.Project.service.ServiceService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @GetMapping("Vehicle/Service/list")
    public List<service> Servicelist(){
        return serviceService.Servicelist();
    }

    @PostMapping("/Vehicle/Service/Register")
    public String service(@RequestParam("registernum") String registernum,
                          @RequestParam("workshop") String workshop,
                          @RequestParam("location") String location,
                          @RequestParam("ServiceDate") String ServiceDate,
                          @RequestParam("ServiceType") String ServiceType,
                          @RequestParam("Odometer") String Odometer,
                          @RequestParam("workdone") String workdone,
                          @RequestParam("cost") int cost,
                          @RequestParam("Status") String Status){
        serviceService.services(registernum,workshop,location,ServiceDate,ServiceType,Odometer, workdone, cost, Status);
        return "Congratulations! "+registernum+" Vehicle Service Details Updated Successfully";
    }

    @GetMapping("/Vehicle/Service/list/{registernum}")
    public List<service> getserviceByregisternum(@PathVariable String registernum) {
        return serviceService.getserviceByregisternum(registernum);
    }
}