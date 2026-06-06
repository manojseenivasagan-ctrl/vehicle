package com.example.Booking.Project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ConfirmBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String mobileno;
    private String emailId;
    private String city;
    private String address;
    private String pincode;
    private String vehicleName;
    private String vehiclemodel;
    private String servicedetails;
    private String servicedate;
    private String servicetime;
    private String shopname;
    private String shopemailId;
    private String mechanicname;
    private String mechanicmobileno;
    private String status;

    // Constructors
    public ConfirmBooking() {

    }

    public ConfirmBooking(String name, String mobileno, String emailId, String city, String address,
                          String pincode, String vehicleName, String vehiclemodel, String servicedetails,
                          String servicedate, String servicetime, String shopname, String shopemailId, String mechanicname,
                          String mechanicmobileno, String status) {

        this.name = name;
        this.mobileno = mobileno;
        this.emailId = emailId;
        this.city = city;
        this.address = address;
        this.pincode = pincode;
        this.vehicleName = vehicleName;
        this.vehiclemodel = vehiclemodel;
        this.servicedetails = servicedetails;
        this.servicedate = servicedate;
        this.servicetime = servicetime;
        this.shopname = shopname;
        this.shopemailId = shopemailId;
        this.mechanicname = mechanicname;
        this.mechanicmobileno = mechanicmobileno;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMobileno() {
        return mobileno;
    }
    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getVehicleName() {
        return vehicleName;
    }
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehiclemodel() {
        return vehiclemodel;
    }
    public void setVehiclemodel(String vehiclemodel) {
        this.vehiclemodel = vehiclemodel;
    }

    public String getServicedetails() {
        return servicedetails;
    }
    public void setServicedetails(String servicedetails) {
        this.servicedetails = servicedetails;
    }

    public String getServicedate() {
        return servicedate;
    }
    public void setServicedate(String servicedate) {
        this.servicedate = servicedate;
    }

    public String getServicetime() {
        return servicetime;
    }
    public void setServicetime(String servicetime) {
        this.servicetime = servicetime;
    }

    public String getShopname() {
        return shopname;
    }
    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopemailId() {
        return shopemailId;
    }

    public void setShopemailId(String shopemailId) {
        this.shopemailId = shopemailId;
    }

    public String getMechanicname() {
        return mechanicname;
    }
    public void setMechanicname(String mechanicname) {
        this.mechanicname = mechanicname;
    }

    public String getMechanicmobileno() {
        return mechanicmobileno;
    }
    public void setMechanicmobileno(String mechanicmobileno) {
        this.mechanicmobileno = mechanicmobileno;
    }
}
