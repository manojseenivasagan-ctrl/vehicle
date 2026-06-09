package com.example.Booking.Project.controller;

import com.example.Booking.Project.model.mechanic;
import com.example.Booking.Project.service.MechanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class MechanicController {

    @Autowired
    MechanicService mechanicService;

    @GetMapping("Mechanic/list")
    public List<mechanic> mechanicList(){
        return mechanicService.mechanicList();
    }

    @PostMapping("/Mechanic/details")
    public String mechanic(@RequestParam("name") String name,
                           @RequestParam("mobileno") String mobileno,
                           @RequestParam("emailId") String emailId,
                           @RequestParam("city") String city,
                           @RequestParam("address") String address,
                           @RequestParam("zipcode") String zipcode,
                           @RequestParam("count") Integer count,
                           @RequestParam("VehicleName") String VehicleName,
                           @RequestParam("opentime") String opentime,
                           @RequestParam("closetime") String closetime){

        mechanicService.mechanics(name, mobileno, emailId, city, address, zipcode, count, VehicleName, opentime, closetime);
        return "Congratulations " + name + "! You Created New Shop Profile Successfully";
    }

    // New API to return shop email from shop name
    @GetMapping("/Mechanic/email/{name}")
    public String getShopEmail(@PathVariable String name) {
        mechanic m = mechanicService.getByName(name);
        if (m != null) {
            return m.getEmailId();
        }
        return "Shop not found";
    }
}
