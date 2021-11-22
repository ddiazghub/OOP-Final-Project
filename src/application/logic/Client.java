/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.logic;

import java.time.Instant;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Client extends Person {
    int id;
    private ClientMembershipType type;
    private ArrayList<Sale> purchases;
    private String streetAddress;
    private String City;
    private ArrayList<Float> availableDiscounts;

    public Client(int id, String streetAddress, String City, String name, Date birthDay, int phoneNumber, String mail) {
        super(name, birthDay, phoneNumber, mail);
        this.id = id;
        this.streetAddress = streetAddress;
        this.City = City;
        this.type = ClientMembershipType.Nuevo;
        this.purchases = new ArrayList<Sale>();
        this.availableDiscounts = new ArrayList<Float>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClientMembershipType getType() {
        return type;
    }

    public void setType(ClientMembershipType type) {
        this.type = type;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public ArrayList<Sale> getPurchases() {
        return purchases;
    }

    public ArrayList<Float> getAvailableDiscounts() {
        return availableDiscounts;
    }
}
