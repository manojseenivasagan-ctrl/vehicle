package com.example.Booking.Project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registernum;
    private String workshop;
    private String location;
    private String RepairDate;
    private String IssueReport;
    private String Diagnosis;
    private String PartsReplaced;
    private int cost;
    private String Status;

    public repair() {}

    public repair(String registernum, String workshop, String location, String RepairDate,
                    String IssueReport, String Diagnosis,String PartsReplaced, int cost, String Status) {

        this.registernum = registernum;
        this.workshop = workshop;
        this.location = location;
        this.RepairDate = RepairDate;
        this.IssueReport = IssueReport;
        this.Diagnosis = Diagnosis;
        this.PartsReplaced = PartsReplaced;
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

    public String getRepairDate() {
        return RepairDate;
    }

    public void setRepairDate(String repairDate) {
        RepairDate = repairDate;
    }

    public String getIssueReport() {
        return IssueReport;
    }

    public void setIssueReport(String issueReport) {
        IssueReport = issueReport;
    }

    public String getDiagnosis() {
        return Diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        Diagnosis = diagnosis;
    }

    public String getPartsReplaced() {
        return PartsReplaced;
    }

    public void setPartsReplaced(String partsReplaced) {
        PartsReplaced = partsReplaced;
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
