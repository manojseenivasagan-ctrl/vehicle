package com.example.Booking.Project.controller;

import com.example.Booking.Project.model.shop;
import com.example.Booking.Project.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("shop/list")
    public List<shop> shopsList() {
        return shopService.shopslist();
    }

    @PostMapping("shop/register")
    public String shops(
            @RequestParam("name") String name,
            @RequestParam("emailId") String emailId,
            @RequestParam("password") String password
    ) {
        shopService.shops(name, emailId, password);
        return "Congratulations! " + name + " registered successfully!";
    }

    @PostMapping("/shop/login")
    public Map<String, Object> loginShop(
            @RequestParam("emailId") String emailId,
            @RequestParam("password") String password) {

        shop shops = shopService.getByEmail(emailId);

        Map<String, Object> response = new HashMap<>();

        if (shops != null && shops.getPassword().equals(password)) {
            response.put("status", "success");
            response.put("name", shops.getName());
        } else {
            response.put("status", "fail");
            response.put("message", "Invalid email or password");
        }
        return response;
    }
}
