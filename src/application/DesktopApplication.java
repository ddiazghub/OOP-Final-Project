/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import application.views.Login;
import application.views.BaseMenu;
import application.logic.ViewFactory;
import application.logic.ApplicationView;

/**
 *
 * @author david
 */
public class DesktopApplication extends JFrame {
    private static DesktopApplication instance;
    private JPanel currentView;
    private ViewFactory factory;
    
    private DesktopApplication() {
        super("Carpintería Peña");
        this.factory = new ViewFactory();
    }
    
    public void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
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
