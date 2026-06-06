package com.example.Booking.Project.controller;
import com.example.Booking.Project.model.Booking;
import com.example.Booking.Project.model.customer;
import com.example.Booking.Project.service.BookingService;
import com.example.Booking.Project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("customer/list")
    public List<customer> customerList(){
        return customerService.customerslist();
    }

    @PostMapping("customers/register")
    public String customers(@RequestParam("name") String name,
                          @RequestParam("emailId") String emailId,
                          @RequestParam("password") String password
    ){
        customerService.customers(name,emailId,password);
        return "Congratulations! "+name+" Registration Successfully";
    }

    @PostMapping("/customer/login")
    public Map<String, Object> loginCustomer(
            @RequestParam("emailId") String emailId,
            @RequestParam("password") String password) {

        customer cust = customerService.getByEmail(emailId);

        Map<String, Object> response = new HashMap<>();

        if (cust != null && cust.getPassword().equals(password)) {
            response.put("status", "success");
            response.put("name", cust.getName());
        } else {
            response.put("status", "fail");
            response.put("message", "Invalid email or password");
        }
        return response;
    }



}

