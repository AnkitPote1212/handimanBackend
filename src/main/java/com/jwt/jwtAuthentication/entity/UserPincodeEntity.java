package com.jwt.jwtAuthentication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_pincode")
public class UserPincodeEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private HandimanUserEntity user;

    @ManyToOne
    @JoinColumn(name = "pincode_id")
    private PostalCode pincode;

    // Constructors, getters, and setters

    public UserPincodeEntity() {
        // Default constructor
    }

    public UserPincodeEntity(HandimanUserEntity user, PostalCode pincode) {
        this.user = user;
        this.pincode = pincode;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HandimanUserEntity getUser() {
        return user;
    }

    public void setUser(HandimanUserEntity user) {
        this.user = user;
    }

    public PostalCode getPincode() {
        return pincode;
    }

    public void setPincode(PostalCode pincode) {
        this.pincode = pincode;
    }
    
}
