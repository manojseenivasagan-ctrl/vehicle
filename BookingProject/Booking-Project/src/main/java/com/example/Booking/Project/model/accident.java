package com.example.Booking.Project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class accident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registernum;
    private String Insurance;
    private String FIR;
    private String location;
    private String accidentdate;
    private String accidenttime;

    public accident() {}

    public accident(String registernum, String Insurance, String FIR, String location,
                   String accidentdate, String accidenttime) {

        this.registernum = registernum;
        this.Insurance = Insurance;
        this.FIR = FIR;
        this.location = location;
        this.accidentdate = accidentdate;
        this.accidenttime = accidenttime;
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

    public String getInsurance() {
        return Insurance;
    }

    public void setInsurance(String insurance) {
        Insurance = insurance;
    }

    public String getFIR() {
        return FIR;
    }

    public void setFIR(String FIR) {
        this.FIR = FIR;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAccidentdate() {
        return accidentdate;
    }

    public void setAccidentdate(String accidentdate) {
        this.accidentdate = accidentdate;
    }

    public String getAccidenttime() {
        return accidenttime;
    }

    public void setAccidenttime(String accidenttime) {
        this.accidenttime = accidenttime;
    }
}
