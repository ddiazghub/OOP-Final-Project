/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.logic;

import java.util.HashMap;
import java.util.Date;

/**
 *
 * @author david
 */
public class Sale {
    private int id;
    private double total;
    private PaymentMethod paymentMethod;
    private Client client;
    private Date date;
    private HashMap<Product, Integer> purchased;

    public Sale(int id, PaymentMethod paymentMethod, Client client, Date date, HashMap<Product, Integer> purchased) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.client = client;
        this.date = date;
        this.purchased = purchased;
        
        for (Product product : this.purchased.keySet()) {
            this.total += this.purchased.get(product) * product.getCost();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public HashMap<Product, Integer> getPurchased() {
        return purchased;
    }
    
    public int getItemCount() {
        int count = 0;
        
        for (Integer value : this.purchased.values())
            count += value;
        
        return count;
    }
}
