/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package application.logic;

/**
 *
 * @author david
 */
public enum ClientMembershipType {
    Nuevo(1),
    Estandar(2),
    Platino(3);
    
    private int value;

    private ClientMembershipType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }

    public static ClientMembershipType get(int value) {
       for (ClientMembershipType t : ClientMembershipType.values()) {
           if (t.value == value) return t;
       }
       return null;
    }
}
