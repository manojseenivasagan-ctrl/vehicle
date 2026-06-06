package com.example.Booking.Project.service;

import com.example.Booking.Project.model.accident;
import com.example.Booking.Project.model.repair;
import com.example.Booking.Project.repository.RepairRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RepairService {

    @Autowired
    RepairRepo repairRepo;

    public List<repair> Repairlist() {
        return repairRepo.findAll();
    }

    public void repairs(String registernum, String workshop, String location, String RepairDate, String IssueReport, String Diagnosis, String PartsReplaced, int cost,String Status) {
        repair Repair = new repair(registernum,workshop,location,RepairDate,IssueReport,Diagnosis,PartsReplaced,cost,Status);
        repairRepo.save(Repair);
    }

    public List<repair> getrepairByregisternum(String registernum) {
        return repairRepo.findByregisternum(registernum);
    }

}
