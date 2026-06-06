package com.example.Booking.Project.controller;
import com.example.Booking.Project.model.cust;
import com.example.Booking.Project.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CustController {

    @Autowired
    CustService custService;

    @GetMapping("cust/list")
    public List<cust> custList(){
        return custService.custslist();
    }

    @PostMapping("custs/register")
    public String cust(@RequestParam("name") String name,
                            @RequestParam("registernum") String registernum,
                            @RequestParam("password") String password
    ){
        custService.custs(name,registernum,password);
        return "Congratulations! "+name+" Registration Successfully";
    }

    @PostMapping("/cust/login")
    public Map<String, Object> loginCustomer(
            @RequestParam("registernum") String registernum,
            @RequestParam("password") String password) {

        cust custs = custService.getByfindByregisternum(registernum);

        Map<String, Object> response = new HashMap<>();

        if (custs != null && custs.getPassword().equals(password)) {
            response.put("status", "success");
            response.put("name", custs.getName());
        } else {
            response.put("status", "fail");
            response.put("message", "Invalid email or password");
        }
        return response;
    }
}
