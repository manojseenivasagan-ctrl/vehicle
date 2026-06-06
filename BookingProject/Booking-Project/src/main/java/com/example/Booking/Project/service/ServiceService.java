package com.example.Booking.Project.service;

import com.example.Booking.Project.model.accident;
import com.example.Booking.Project.model.service;
import com.example.Booking.Project.repository.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ServiceService {

    @Autowired
    ServiceRepo serviceRepo;

    public List<service> Servicelist() {
        return serviceRepo.findAll();
    }

    public void services(String registernum, String workshop, String location, String ServiceDate, String ServiceType, String Odometer, String workdone, int cost, String Status) {
        service Service = new service(registernum,workshop,location,ServiceDate,ServiceType,Odometer, workdone,cost, Status);
        serviceRepo.save(Service);
    }

    public List<service> getserviceByregisternum(String registernum) {
        return serviceRepo.findByregisternum(registernum);
    }

}
