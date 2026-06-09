package com.example.Booking.Project.controller;

import com.example.Booking.Project.model.accident;
import com.example.Booking.Project.model.repair;
import com.example.Booking.Project.service.RepairService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class RepairController {

    @Autowired
    RepairService repairService;

    @GetMapping("Vehicle/Repair/list")
    public List<repair> Repairlist(){
        return repairService.Repairlist();
    }

    @PostMapping("/Vehicle/Repair/Register")
    public String repair(@RequestParam("registernum") String registernum,
                         @RequestParam("workshop") String workshop,   // FIXED
                         @RequestParam("location") String location,
                         @RequestParam("RepairDate") String RepairDate,
                         @RequestParam("IssueReport") String IssueReport,
                         @RequestParam("Diagnosis") String Diagnosis,
                         @RequestParam("PartsReplaced") String PartsReplaced,
                         @RequestParam("cost") int cost,
                         @RequestParam("Status") String Status){

        repairService.repairs(registernum,workshop,location,RepairDate,IssueReport,Diagnosis,PartsReplaced,cost,Status
        );

        return "Congratulations! " + registernum + " Vehicle Repair Details Updated Successfully";
    }

    @GetMapping("/Vehicle/Repair/list/{registernum}")
    public List<repair> getrepairByregisternum(@PathVariable String registernum) {
        return repairService.getrepairByregisternum(registernum);
    }
}
