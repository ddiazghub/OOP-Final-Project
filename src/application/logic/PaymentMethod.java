/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package application.logic;

/**
 *
 * @author david
 */
public enum PaymentMethod {
    Efectivo(1),
    Debito(2),
    Credito(3);
    
    private int value;

    private PaymentMethod(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }

    public static PaymentMethod get(int value) {
       for (PaymentMethod m : PaymentMethod.values()) {
           if (m.value == value) return m;
       }
       return null;
    }
}
