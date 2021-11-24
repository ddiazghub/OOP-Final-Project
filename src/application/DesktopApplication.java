/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import application.views.Login;
import application.views.MainMenu;
import application.logic.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author david
 */
public class DesktopApplication extends JFrame {
    private static DesktopApplication instance;
    private JPanel currentView;
    private ViewFactory factory;
    private Profile currentProfile;
    private DatabaseManager db;
    
    private DesktopApplication() {
        super("Carpintería Peña");
        this.factory = new ViewFactory();
        
        this.currentProfile = null;
    }

    public void startDb() {
        this.db = DatabaseManager.getInstance();
    }

    public Profile getCurrentProfile() {
        return currentProfile;
    }

    public void setCurrentProfile(Profile currentProfile) {
        this.currentProfile = currentProfile;
    }
    
    public void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                DatabaseManager.getInstance().close();
                System.exit(0);//cierra aplicacion
            }
        });
        this.startDb();
    }
    
    public void changeFrame(ApplicationView view) {
        if (this.currentView != null)
            this.remove(this.currentView);
        
        JPanel newView = this.factory.get(view);
        this.add(newView, BorderLayout.CENTER);
        this.currentView = newView;
        this.revalidate();
        this.repaint();
        this.pack();
    }
    
    public static void main(String args[]) {
        instance = new DesktopApplication();
        instance.init();
        instance.changeFrame(ApplicationView.LOGIN);
    }
    
    public static DesktopApplication getInstance() {
        if (instance == null) {
            instance = new DesktopApplication();
        }
        
        return instance;
    }
}
