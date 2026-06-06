package com.example.Booking.Project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerEmail;  // ✅ This is the correct name
    private String mobile;
    private String vehicle;
    private String serviceDetails;
    private String serviceDate;
    private String serviceTime;
    private String shopName;
    private String shopemail;
    private String mechanicName;
    private int rating;
    private String feedback;



    public Feedback() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getMechanicName() { return mechanicName; }
    public void setMechanicName(String mechanicName) { this.mechanicName = mechanicName; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    // Optional additional fields
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getVehicle() { return vehicle; }
    public void setVehicle(String vehicle) { this.vehicle = vehicle; }

    public String getServiceDetails() { return serviceDetails; }
    public void setServiceDetails(String serviceDetails) { this.serviceDetails = serviceDetails; }

    public String getServiceDate() { return serviceDate; }
    public void setServiceDate(String serviceDate) { this.serviceDate = serviceDate; }

    public String getServiceTime() { return serviceTime; }
    public void setServiceTime(String serviceTime) { this.serviceTime = serviceTime; }

    public String getShopName() { return shopName; }
    public void setShopName(String shopName) { this.shopName = shopName; }

    public String getShopemail() {
        return shopemail;
    }

    public void setShopemail(String shopemail) {
        this.shopemail = shopemail;
    }

    public Feedback(String customerEmail, String mechanicName, int rating, String feedback, String customerName, String mobile, String vehicle, String serviceDetails, String serviceDate, String serviceTime, String shopName, String shopemail) {
        this.customerEmail = customerEmail;
        this.mechanicName = mechanicName;
        this.rating = rating;
        this.feedback = feedback;
        this.customerName = customerName;
        this.mobile = mobile;
        this.vehicle = vehicle;
        this.serviceDetails = serviceDetails;
        this.serviceDate = serviceDate;
        this.serviceTime = serviceTime;
        this.shopName = shopName;
        this.shopemail = shopemail;
    }


}
