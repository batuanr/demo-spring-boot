package com.example.demospring_boot.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;


@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @ManyToOne
    @JoinColumn(name = "address_id")
//    @JsonManagedReference
    private Address address;
    private String image;
    public Customer() {}

    public Customer(Long id, String name, String email, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;

    }

    public Customer(Long id, String name, String email, Address address, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.image = image;
    }

    public Customer(String name, String email, Address address, String image) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.image = image;
    }

    public Customer(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
