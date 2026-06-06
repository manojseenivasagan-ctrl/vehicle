package com.example.Booking.Project.service;

import com.example.Booking.Project.model.cust;
import com.example.Booking.Project.repository.CustRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustService {

    @Autowired
    CustRepo custRepo;

    public void custs(String name, String registernum, String password) {
        cust custs = new cust(name,registernum,password);
        custRepo.save(custs);
    }

    public List<cust> custslist() {
        return custRepo.findAll();
    }

    public cust getByfindByregisternum(String registernum) {
        return custRepo.findByregisternum(registernum);
    }
}
