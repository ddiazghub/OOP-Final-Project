/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.logic;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;

/**
 *
 * @author david
 */
public class Personel extends Person {
    private int id;
    private double salary;
    private Date admissionDate;
    private Date leavingDate;
    private ArrayList<Sale> sales;
    private PersonelType type;
    private Profile profile;
    private String imagePath;

    public Personel(int id, double salary, Date admissionDate, PersonelType type, String name, Date birthDay, int phoneNumber, String mail, String imagePath) {
        super(name, birthDay, phoneNumber, mail);
        this.id = id;
        this.salary = salary;
        this.admissionDate = admissionDate;
        this.type = type;
        this.leavingDate = null;
        this.sales = new ArrayList<Sale>();
        this.profile = null;
        this.imagePath = imagePath;
    }
    
    public Personel(int id, double salary, Date admissionDate, PersonelType type, String name, Date birthDay, int phoneNumber, String mail) {
        super(name, birthDay, phoneNumber, mail);
        this.id = id;
        this.salary = salary;
        this.admissionDate = admissionDate;
        this.type = type;
        this.leavingDate = null;
        this.sales = new ArrayList<Sale>();
        this.profile = null;
        this.imagePath = null;
    }

    public Profile getProfile() {
        return this.profile;
    }
    
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public PersonelType getType() {
        return type;
    }

    public void setType(PersonelType type) {
        this.type = type;
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public Date getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
