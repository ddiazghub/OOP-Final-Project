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
public class Product extends Purchasable {
    private ProductTheme theme;
    private WoodType wood;

    public Product(int id, String name, double cost, String description, ProductTheme theme, WoodType wood) {
        super(id, name, cost, description);
        this.theme = theme;
        this.wood = wood;
    }

    public ProductTheme getTheme() {
        return theme;
    }

    public void setTheme(ProductTheme theme) {
        this.theme = theme;
    }

    public WoodType getWood() {
        return wood;
    }

    public void setWood(WoodType wood) {
        this.wood = wood;
    }
    
    
}
