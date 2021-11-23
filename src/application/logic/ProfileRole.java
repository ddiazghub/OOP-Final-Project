/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package application.logic;

/**
 *
 * @author david
 */
public enum ProfileRole {
    Usuario(1),
    Admin(2);
    
    private int value;

    private ProfileRole(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }

    public static ProfileRole get(int value) {
       for (ProfileRole m : ProfileRole.values()) {
           if (m.value == value) return m;
       }
       return null;
    }
}
