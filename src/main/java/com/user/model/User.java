package com.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class User {
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotNull
    @Column(name="name")
    private String name;
    
    @NotNull
    @Column(name="surname")
    private String surname;

    @NotNull
    @Column(name="address")
    private String address;

    @NotNull
    @Column(name="phone")
    private String phone;

    @NotNull
    @Column(name="locality")
    private String locality;

    @NotNull
    @Column(name="provincie")
    private String province;

    @NotNull
    @Column(name="edad")
    private int edad;

    public User() {

    }

    public User(String id, String name, String surname, String address, String phone, String locality,
                String province, int edad) { 
        super();
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.locality = locality;
        this.province = province;
        this.edad = edad; 
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getEdad() { 
        return edad;
    }

    public void setEdad(int edad) { 
        this.edad = edad;
    }
}
