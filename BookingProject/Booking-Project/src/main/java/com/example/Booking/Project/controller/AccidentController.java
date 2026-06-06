package com.example.Booking.Project.controller;


import com.example.Booking.Project.model.accident;
import com.example.Booking.Project.service.AccidentService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AccidentController {

    @Autowired
    AccidentService accidentService;

    @GetMapping("Vehicle/Accident/list")
    public List<accident> Accidentlist(){
        return accidentService.Accidentlist();
    }

    @PostMapping("/Vehicle/Accident/Register")
    public String vehicle(@RequestParam("registernum") String registernum,
                          @RequestParam("Insurance") String Insurance,
                          @RequestParam("FIR") String FIR,
                          @RequestParam("location") String location,
                          @RequestParam("accidentdate") String accidentdate,
                          @RequestParam("accidenttime") String accidenttime){
        accidentService.accidents(registernum,Insurance,FIR,location,accidentdate,accidenttime);
        return "Congratulations! "+registernum+" Vehicle Accident Details Updated Successfully";
    }

    @GetMapping("/Vehicle/Accident/list/{registernum}")
    public List<accident> getaccidentByregisternum(@PathVariable String registernum) {
        return accidentService.getaccidentByregisternum(registernum);
    }

}