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
public class Purchase {
    private int id;
    private double total;
    private Date date;
    private Provider provider;
    private final HashMap<Material, Integer> purchased;

    public Purchase(int id, Date date, Provider provider, HashMap<Material, Integer> purchased) {
        this.id = id;
        this.date = date;
        this.provider = provider;
        this.purchased = purchased;
        this.total = 0;
        
        for (Material material : this.purchased.keySet()) {
            this.total += this.purchased.get(material) * material.getCost();
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
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public HashMap<Material, Integer> getPurchased() {
        return purchased;
    }
    
    public int getItemCount() {
        int count = 0;
        
        for (Integer value : this.purchased.values())
            count += value;
        
        return count;
    }
}
