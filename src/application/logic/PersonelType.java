/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package application.logic;

/**
 *
 * @author david
 */
public enum PersonelType {
    Ebanista(1),
    Vendedor(2),
    JefeControlCalidad(3),
    Contador(4),
    Gerente(5),
    Recepcionista(6);
    
    private int value;

    private PersonelType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }

    public static PersonelType get(int value) {
       for (PersonelType m : PersonelType.values()) {
           if (m.value == value) return m;
       }
       return null;
    }
}
