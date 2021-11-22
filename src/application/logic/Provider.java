/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.logic;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Provider {
    private int id;
    private String name;
    private String city;
    private String streetAddress;
    private int phoneNumber;
    private String email;
    private ArrayList<Purchase> sales;

    public Provider(int id, String name, String city, String streetAddress, int phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.streetAddress = streetAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.sales = new ArrayList<Purchase>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Purchase> getSales() {
        return sales;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
    
}
