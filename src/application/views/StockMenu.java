/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package application.views;

import application.DesktopApplication;
import application.components.PurchaseListItem;
import application.components.PurchasedListItem;
import application.components.SalesListItem;
import application.components.StockListItem;
import application.logic.Client;
import application.logic.Helpers;
import application.logic.Material;
import application.logic.PaymentMethod;
import application.logic.Product;
import application.logic.Purchasable;
import application.logic.Provider;
import application.logic.Purchase;
import application.logic.Sale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author david
 */
public class StockMenu extends javax.swing.JPanel {

    private Purchase purchase;
    private Client saleClient;
    private Sale sale;
    
    /**
     * Creates new form Sales
     */
    public StockMenu() {
        initComponents();
       this.getAllProducts();
       
       this.saleClient = null;
       final StockMenu menu = this;
       
       new Thread() {
           @Override
           public void run() {
               menu.getAllProducts();
               menu.invalidate();
               menu.validate();
               menu.repaint();
               
               try { 
                   this.sleep(500);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }.start();
    }

    public void getAllProducts() {
        this.productsPanel.removeAll();
        final StockMenu menu = this;
        
        for (Product p : DesktopApplication.getInstance().getProducts()) {
            StockListItem item = new StockListItem(p, DesktopApplication.getInstance().getStock().get(p));
            this.productsPanel.add(item);

            item.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    menu.purchaseListItemMousePressed(evt);
                }
            });
        }
        
        for (Material m : DesktopApplication.getInstance().getMaterials()) {
            StockListItem item = new StockListItem(m, DesktopApplication.getInstance().getStock().get(m));
            this.productsPanel.add(item);

            item.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    menu.purchaseListItemMousePressed(evt);
                }
            });
        }
        
        this.invalidate();
        this.revalidate();
        this.repaint();
    }
    
    public double purchaseTotalFromList(JPanel list) {
        double total = 0;
        
        for (Component c : list.getComponents()) {
            PurchasedListItem item = (PurchasedListItem) c;
            total += item.getItem().getCost() * item.getQuantity();
        }
        
        return total;
    }
    
    public HashMap<Material, Integer> purchaseListToMap(JPanel list) {
        HashMap<Material, Integer> map = new HashMap<>();
        
        for (Component c : list.getComponents()) {
            PurchasedListItem item = (PurchasedListItem) c;
            map.put((Material) item.getItem(), item.getQuantity());
        }
        
        return map;
    }
    
    public HashMap<Product, Integer> saleListToMap(JPanel list) {
        HashMap<Product, Integer> map = new HashMap<>();
        
        for (Component c : list.getComponents()) {
            PurchasedListItem item = (PurchasedListItem) c;
            map.put((Product) item.getItem(), item.getQuantity());
        }
        
        return map;
    }
    
    public void purchaseListItemMousePressed(MouseEvent evt) {
        Purchasable item = ((StockListItem) evt.getComponent()).getItem();
        JTextField quantityField = new JTextField();
        Object[] message = {
            "Cantidad:", quantityField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Añadir", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int quantity = Helpers.tryParseInt(quantityField.getText());
            HashMap<Purchasable, Integer> stock = DesktopApplication.getInstance().getStock();
            try {   
                stock.put(item, stock.get(item) + quantity);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "El elemento no existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        this.getAllProducts();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stock = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        label17 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jPanel24 = new javax.swing.JPanel();
        label18 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jPanel25 = new javax.swing.JPanel();
        label19 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jPanel26 = new javax.swing.JPanel();
        label20 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jPanel27 = new javax.swing.JPanel();
        label21 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        productsPanel = new javax.swing.JPanel();
        addItemButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(960, 610));
        setLayout(new java.awt.BorderLayout());

        stock.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Inventario");
        stock.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 240, 40));

        jPanel5.setBackground(new java.awt.Color(204, 0, 0));
        jPanel5.setPreferredSize(new java.awt.Dimension(799, 50));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jPanel23.setBackground(new java.awt.Color(204, 0, 0));
        jPanel23.setMaximumSize(new java.awt.Dimension(120, 50));
        jPanel23.setMinimumSize(new java.awt.Dimension(120, 50));
        jPanel23.setPreferredSize(new java.awt.Dimension(120, 50));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label17.setForeground(new java.awt.Color(204, 204, 204));
        label17.setText("ID");
        jPanel23.add(label17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel5.add(jPanel23);

        jSeparator13.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel5.add(jSeparator13);

        jPanel24.setBackground(new java.awt.Color(204, 0, 0));
        jPanel24.setMaximumSize(new java.awt.Dimension(160, 50));
        jPanel24.setMinimumSize(new java.awt.Dimension(160, 50));
        jPanel24.setPreferredSize(new java.awt.Dimension(160, 50));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label18.setForeground(new java.awt.Color(204, 204, 204));
        label18.setText("Nombre");
        jPanel24.add(label18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel5.add(jPanel24);

        jSeparator14.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel5.add(jSeparator14);

        jPanel25.setBackground(new java.awt.Color(204, 0, 0));
        jPanel25.setMaximumSize(new java.awt.Dimension(180, 50));
        jPanel25.setMinimumSize(new java.awt.Dimension(180, 50));
        jPanel25.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label19.setForeground(new java.awt.Color(204, 204, 204));
        label19.setText("Precio Unitario");
        jPanel25.add(label19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel5.add(jPanel25);

        jSeparator15.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator15.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel5.add(jSeparator15);

        jPanel26.setBackground(new java.awt.Color(204, 0, 0));
        jPanel26.setMaximumSize(new java.awt.Dimension(180, 50));
        jPanel26.setMinimumSize(new java.awt.Dimension(180, 50));
        jPanel26.setName(""); // NOI18N
        jPanel26.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label20.setForeground(new java.awt.Color(204, 204, 204));
        label20.setText("Cantidad");
        jPanel26.add(label20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel5.add(jPanel26);

        jSeparator16.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel5.add(jSeparator16);

        jPanel27.setBackground(new java.awt.Color(204, 0, 0));
        jPanel27.setMaximumSize(new java.awt.Dimension(140, 50));
        jPanel27.setMinimumSize(new java.awt.Dimension(140, 50));
        jPanel27.setPreferredSize(new java.awt.Dimension(140, 50));
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label21.setForeground(new java.awt.Color(204, 204, 204));
        label21.setText("Tipo");
        jPanel27.add(label21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel5.add(jPanel27);

        stock.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        productsPanel.setBackground(new java.awt.Color(153, 153, 153));
        productsPanel.setLayout(new javax.swing.BoxLayout(productsPanel, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane4.setViewportView(productsPanel);

        stock.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 800, 290));

        addItemButton.setBackground(new java.awt.Color(42, 39, 41));
        addItemButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addItemButton.setForeground(new java.awt.Color(204, 204, 204));
        addItemButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icons/add.png"))); // NOI18N
        addItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemButtonActionPerformed(evt);
            }
        });
        stock.add(addItemButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 40, 40));

        add(stock, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
        JTextField nameField = new JTextField();
        JTextField costField = new JTextField();
        JTextField descriptionField = new JTextField();
        JComboBox typeSelect = new JComboBox(new String[] {"Material", "Producto"});
        typeSelect.setSelectedIndex(0);
        
        Object[] message = {
            "Nombre:", nameField,
            "Costo:", costField,
            "Descripción:", descriptionField,
            "Tipo", typeSelect
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Añadir Material/Producto", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            ArrayList<Material> materials = DesktopApplication.getInstance().getMaterials();
            ArrayList<Product> products = DesktopApplication.getInstance().getProducts();
            HashMap<Purchasable, Integer> stock = DesktopApplication.getInstance().getStock();
            
            if (((String)typeSelect.getSelectedItem()).equals("Material")) {
                Material m = new Material(materials.size(), nameField.getText(), Helpers.tryParseDouble(costField.getText()), descriptionField.getText());
                materials.add(m);
                stock.put(m, 0);
            } else {
                Product m = new Product(products.size(), nameField.getText(), Helpers.tryParseDouble(costField.getText()), descriptionField.getText());
                products.add(m);
                stock.put(m, 0);
            }
        }
        
        this.getAllProducts();
    }//GEN-LAST:event_addItemButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addItemButton;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JLabel label17;
    private javax.swing.JLabel label18;
    private javax.swing.JLabel label19;
    private javax.swing.JLabel label20;
    private javax.swing.JLabel label21;
    private javax.swing.JPanel productsPanel;
    private javax.swing.JPanel stock;
    // End of variables declaration//GEN-END:variables
}
