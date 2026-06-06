package com.example.Booking.Project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registernum;
    private String workshop;
    private String location;
    private String ServiceDate;
    private String ServiceType;
    private String Odometer;
    private String workdone;
    private int cost;
    private String Status;

    public service() {}

    public service(String registernum, String workshop, String location, String ServiceDate,
                    String ServiceType, String Odometer, String workdone, int cost, String Status) {

        this.registernum = registernum;
        this.workshop = workshop;
        this.location = location;
        this.ServiceDate = ServiceDate;
        this.ServiceType = ServiceType;
        this.Odometer = Odometer;
        this.workdone = workdone;
        this.cost = cost;
        this.Status = Status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegisternum() {
        return registernum;
    }

    public void setRegisternum(String registernum) {
        this.registernum = registernum;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServiceDate() {
        return ServiceDate;
    }

    public void setServiceDate(String serviceDate) {
        ServiceDate = serviceDate;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getOdometer() {
        return Odometer;
    }

    public void setOdometer(String odometer) {
        Odometer = odometer;
    }

    public String getWorkdone() {
        return workdone;
    }

    public void setWorkdone(String workdone) {
        this.workdone = workdone;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
