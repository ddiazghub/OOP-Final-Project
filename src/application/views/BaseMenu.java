/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package application.views;

import application.DesktopApplication;
import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.BorderLayout;
import application.components.MenuItem;
import application.logic.ListMenuItemData;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.CardLayout;
import application.logic.*;

/**
 *
 * @author david
 */
public class BaseMenu extends javax.swing.JPanel {
    
    private JPanel currentView;
    
    private final ListMenuItemData[] menuItems = {
        new ListMenuItemData("src/res/icons/home.png", "Inicio", "home"),
        new ListMenuItemData("src/res/icons/sale.png", "Compras y Ventas", "sales"),
        new ListMenuItemData("src/res/icons/productStock.png", "Inventario", "stock"),
        new ListMenuItemData("src/res/icons/personel.png", "Personal", "personel"),
        new ListMenuItemData("src/res/icons/client.png", "Clientes", "clients"),
        new ListMenuItemData("src/res/icons/admin.png", "Administrador", "admin"),
        new ListMenuItemData("src/res/icons/quit.png", "Salir", "quit"),
    };

    /**
     * Creates new form Login
     */
    public BaseMenu() {
        initComponents();
        
        this.tabArea.add(new Home(), "home");
        this.tabArea.add(new SalesAndPurchases(), "sales");
        
        for (ListMenuItemData item : this.menuItems) {
            MenuItem newItem = new MenuItem(item.icon, item.text);
            this.listMenu.add(newItem);
            final String card = item.card;
            final CardLayout layout = (CardLayout) this.tabArea.getLayout();
            final JPanel panel = this.tabArea;
            MouseAdapter listener;
            
            if (item.card.equals("quit")) {
                listener = new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent me) { 
                        DesktopApplication.getInstance().changeFrame(ApplicationView.LOGIN);
                    }
                };
            } else {
                listener = new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent me) { 
                        layout.show(panel, card);
                    }
                };
            }
            
            newItem.addMouseListener(listener);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel3 = new javax.swing.JPanel();
        listMenu = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        tabArea = new javax.swing.JPanel();

        jMenuItem1.setText("jMenuItem1");

        setMinimumSize(new java.awt.Dimension(1150, 620));
        setPreferredSize(new java.awt.Dimension(1094, 620));
        setRequestFocusEnabled(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(42, 39, 41));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listMenu.setBackground(new java.awt.Color(42, 39, 41));
        listMenu.setLayout(new javax.swing.BoxLayout(listMenu, javax.swing.BoxLayout.PAGE_AXIS));
        jPanel3.add(listMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 192, 450));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 620));

        jPanel1.setBackground(new java.awt.Color(204, 0, 0));
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 960, 10));

        tabArea.setLayout(new java.awt.CardLayout());
        add(tabArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 960, 610));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel listMenu;
    private javax.swing.JPanel tabArea;
    // End of variables declaration//GEN-END:variables
}
