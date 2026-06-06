package com.example.Booking.Project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registernum;
    private String owner;
    private String address;
    private String emailId;
    private String mobileNo;
    private String makeandmodel;
    private String fueltype;
    private String chassisNo;
    private String engineNo;

    public vehicle() {}

    public vehicle(String registernum, String owner, String address, String emailId,
                   String mobileNo, String makeandmodel, String fueltype,
                   String chassisNo, String engineNo) {

        this.registernum = registernum;
        this.owner = owner;
        this.address = address;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
        this.makeandmodel = makeandmodel;
        this.fueltype = fueltype;
        this.chassisNo = chassisNo;
        this.engineNo = engineNo;
    }

    // Getters & Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRegisternum() { return registernum; }
    public void setRegisternum(String registernum) { this.registernum = registernum; }

    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getMakeandmodel() { return makeandmodel; }
    public void setMakeandmodel(String makeandmodel) { this.makeandmodel = makeandmodel; }

    public String getFueltype() { return fueltype; }
    public void setFueltype(String fueltype) { this.fueltype = fueltype; }

    public String getChassisNo() { return chassisNo; }
    public void setChassisNo(String chassisNo) { this.chassisNo = chassisNo; }

    public String getEngineNo() { return engineNo; }
    public void setEngineNo(String engineNo) { this.engineNo = engineNo; }
}
