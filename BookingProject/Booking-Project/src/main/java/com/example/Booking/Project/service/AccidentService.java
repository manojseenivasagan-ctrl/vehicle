package com.example.Booking.Project.service;

import com.example.Booking.Project.model.ConfirmBooking;
import com.example.Booking.Project.model.accident;
import com.example.Booking.Project.repository.AccidentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AccidentService {

    @Autowired
    AccidentRepo accidentRepo;

    public List<accident> Accidentlist() {
        return accidentRepo.findAll();
    }

    public void accidents(String registernum, String Insurance, String FIR, String location, String accidentdate, String accidenttime) {
        accident Accident = new accident(registernum,Insurance,FIR,location,accidentdate,accidenttime);
        accidentRepo.save(Accident);
    }

    public List<accident> getaccidentByregisternum(String registernum) {
        return accidentRepo.findByregisternum(registernum);
    }

}
