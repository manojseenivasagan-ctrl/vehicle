package com.example.Booking.Project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class cust {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String registernum;  // <-- changed from EmailId
    private String password;

    // Constructors
    public cust() {}

    public cust(String name, String registernum, String password) {
        this.name = name;
        this.registernum = registernum;
        this.password = password;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getregisternum() {
        return registernum;
    }

    public void setregisternum(String registernum) {
        this.registernum = registernum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
