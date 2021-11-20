/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.logic;

import javax.swing.JPanel;
import application.views.*;

/**
 *
 * @author david
 */
public class ViewFactory {
    public JPanel get(ApplicationView view) {
        JPanel newView = null;
        
        switch (view) {
            case LOGIN:
                newView = new Login();
                break;
           
            case MAIN_MENU:
                newView = new BaseMenu();
                break;
        }
        
        return newView;
    }
}
