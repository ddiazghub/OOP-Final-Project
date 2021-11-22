/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package application.views;

import application.DesktopApplication;
import application.components.PurchaseListItem;
import application.components.PurchasedListItem;
import application.components.SalesListItem;
import application.logic.Helpers;
import application.logic.Material;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author david
 */
public class SalesAndPurchasesMenu extends javax.swing.JPanel {

    private Purchase purchase;
    private Sale sale;
    private ArrayList<Purchasable> items;
    
    /**
     * Creates new form Sales
     */
    public SalesAndPurchasesMenu() {
        initComponents();
        this.items = new ArrayList<>();
       this.getAllPurchases();
       this.getAllSales();
       
       for (Provider provider : DesktopApplication.getInstance().getProviders()) {
           this.providerSelect.addItem(provider);
       }
    }

    public void getAllPurchases() {
        this.purchasesPanel.removeAll();
        final SalesAndPurchasesMenu menu = this;
        
        for (Purchase p : DesktopApplication.getInstance().getPurchases()) {
            PurchaseListItem item = new PurchaseListItem(p);
            this.purchasesPanel.add(item);

            item.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    menu.purchaseListItemMousePressed(evt);
                }
            });
        }
    }
    
    public void getAllSales() {
        this.salesPanel.removeAll();
        final SalesAndPurchasesMenu menu = this;
        
        for (Sale p : DesktopApplication.getInstance().getSales()) {
            SalesListItem item = new SalesListItem(p);
            this.salesPanel.add(item);

            item.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    menu.saleListItemMousePressed(evt);
                }
            });
        }
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
        this.refreshPurchase();
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
        this.refreshSale();
    }
    
    public void refreshPurchase() {
        this.idLabel.setText(Integer.toString(this.purchase.getId()));
        this.providerIdLabel.setText(Integer.toString(this.purchase.getProvider().getId()));
        this.purchaseTotalLabel.setText(Helpers.formatDate(this.purchase.getDate()));
        this.providerNameLabel.setText(this.purchase.getProvider().getName());
        this.purchaseTotalLabel.setText(Double.toString(this.purchase.getTotal()));
        this.purchasedItemsPanel.removeAll();
        
        for (Purchasable item : this.purchase.getPurchased().keySet())
            this.purchasedItemsPanel.add(new PurchasedListItem(item, this.purchase.getPurchased().get((Material) item), false));
    }
    
    public void refreshSale() {
        this.idLabel.setText(Integer.toString(this.purchase.getId()));
        this.providerIdLabel.setText(Integer.toString(this.purchase.getProvider().getId()));
        this.purchaseTotalLabel.setText(Helpers.formatDate(this.purchase.getDate()));
        this.providerNameLabel.setText(this.purchase.getProvider().getName());
        this.purchaseTotalLabel.setText(Double.toString(this.purchase.getTotal()));
        this.purchasedItemsPanel.removeAll();
        
        for (Purchasable item : this.purchase.getPurchased().keySet())
            this.purchasedItemsPanel.add(new PurchasedListItem(item, this.purchase.getPurchased().get((Material) item), false));
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
    
    public void purchaseListItemMousePressed(MouseEvent evt) {
        this.setPurchase(((PurchaseListItem) evt.getComponent()).getPurchase());
        ((CardLayout) this.purchasesTab.getLayout()).show(this.purchasesTab, "see");
    }
    
    public void saleListItemMousePressed(MouseEvent evt) {
        this.setSale(((SalesListItem) evt.getComponent()).getSale());
        ((CardLayout) this.salesTab.getLayout()).show(this.salesTab, "see");
    }
    
    
    public void purchasedListItem1MousePressed(MouseEvent evt) {
        int option = JOptionPane.showConfirmDialog(this, "Desea eliminar el material de la lista?", "Eliminar elemento", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            this.purchasedItemsPanel1.remove(evt.getComponent());
            this.purchaseTotalLabel1.setText(Double.toString(this.purchaseTotalFromList(this.purchasedItemsPanel1)));
        }
    }
    
    public void saleListItem1MousePressed(MouseEvent evt) {
        int option = JOptionPane.showConfirmDialog(this, "Desea eliminar el material de la lista?", "Eliminar elemento", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            this.purchasedItemsPanel1.remove(evt.getComponent());
            this.purchaseTotalLabel1.setText(Double.toString(this.purchaseTotalFromList(this.purchasedItemsPanel1)));
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        purchasesTab = new javax.swing.JPanel();
        purchases = new javax.swing.JPanel();
        editButton2 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        searchTextField1 = new javax.swing.JTextField();
        addPurchaseButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        purchasesPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        label7 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel14 = new javax.swing.JPanel();
        label8 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel15 = new javax.swing.JPanel();
        label9 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel16 = new javax.swing.JPanel();
        label10 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel17 = new javax.swing.JPanel();
        label11 = new javax.swing.JLabel();
        seePurchases = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        providerNameLabel = new javax.swing.JLabel();
        purchaseTotalLabel = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        providerIdLabel = new javax.swing.JLabel();
        returnButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        label12 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jPanel19 = new javax.swing.JPanel();
        label13 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jPanel20 = new javax.swing.JPanel();
        label14 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jPanel21 = new javax.swing.JPanel();
        label15 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jPanel22 = new javax.swing.JPanel();
        label16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        purchasedItemsPanel = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        addPurchase = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        returnButton1 = new javax.swing.JButton();
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
        purchasedItemsPanel1 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        providerSelect = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        purchaseTotalLabel1 = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        dateChooser = new com.toedter.calendar.JDateChooser();
        confirmButton = new javax.swing.JButton();
        addItemButton = new javax.swing.JButton();
        salesTab = new javax.swing.JPanel();
        sales = new javax.swing.JPanel();
        editButton3 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        searchTextField2 = new javax.swing.JTextField();
        addSaleButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        salesPanel = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        label22 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        jPanel29 = new javax.swing.JPanel();
        label23 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        jPanel30 = new javax.swing.JPanel();
        label24 = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        jPanel31 = new javax.swing.JPanel();
        label25 = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        jPanel32 = new javax.swing.JPanel();
        label26 = new javax.swing.JLabel();
        seeSales = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        deleteButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        providerNameLabel1 = new javax.swing.JLabel();
        purchaseTotalLabel2 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        providerIdLabel1 = new javax.swing.JLabel();
        returnButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        idLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        label27 = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        jPanel34 = new javax.swing.JPanel();
        label28 = new javax.swing.JLabel();
        jSeparator22 = new javax.swing.JSeparator();
        jPanel35 = new javax.swing.JPanel();
        label29 = new javax.swing.JLabel();
        jSeparator23 = new javax.swing.JSeparator();
        jPanel36 = new javax.swing.JPanel();
        label30 = new javax.swing.JLabel();
        jSeparator24 = new javax.swing.JSeparator();
        jPanel37 = new javax.swing.JPanel();
        label31 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        purchasedItemsPanel2 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        dateLabel1 = new javax.swing.JLabel();
        addSale = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        returnButton3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        label32 = new javax.swing.JLabel();
        jSeparator25 = new javax.swing.JSeparator();
        jPanel39 = new javax.swing.JPanel();
        label33 = new javax.swing.JLabel();
        jSeparator26 = new javax.swing.JSeparator();
        jPanel40 = new javax.swing.JPanel();
        label34 = new javax.swing.JLabel();
        jSeparator27 = new javax.swing.JSeparator();
        jPanel41 = new javax.swing.JPanel();
        label35 = new javax.swing.JLabel();
        jSeparator28 = new javax.swing.JSeparator();
        jPanel42 = new javax.swing.JPanel();
        label36 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        purchasedItemsPanel3 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        providerSelect1 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        purchaseTotalLabel3 = new javax.swing.JLabel();
        cancelButton1 = new javax.swing.JButton();
        dateChooser1 = new com.toedter.calendar.JDateChooser();
        confirmButton1 = new javax.swing.JButton();
        addItemButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(960, 610));
        setLayout(new java.awt.BorderLayout());

        purchasesTab.setLayout(new java.awt.CardLayout());

        purchases.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editButton2.setBackground(new java.awt.Color(42, 39, 41));
        editButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        editButton2.setForeground(new java.awt.Color(204, 204, 204));
        editButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icons/search.png"))); // NOI18N
        editButton2.setText("Buscar");
        editButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                editButton2MouseReleased(evt);
            }
        });
        editButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButton2ActionPerformed(evt);
            }
        });
        purchases.add(editButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 110, 50));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buscar Por...", "Nombre de Usuario", "ID", "Nombre de Personal" }));
        purchases.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 170, 30));

        searchTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchTextField1.setToolTipText("");
        searchTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextField1ActionPerformed(evt);
            }
        });
        purchases.add(searchTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 350, 30));

        addPurchaseButton.setBackground(new java.awt.Color(42, 39, 41));
        addPurchaseButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addPurchaseButton.setForeground(new java.awt.Color(204, 204, 204));
        addPurchaseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icons/add.png"))); // NOI18N
        addPurchaseButton.setText("Añadir");
        addPurchaseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addPurchaseButtonMouseReleased(evt);
            }
        });
        addPurchaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPurchaseButtonActionPerformed(evt);
            }
        });
        purchases.add(addPurchaseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 110, 110, 50));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Gestionar Compras");
        purchases.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 350, 40));

        purchasesPanel.setBackground(new java.awt.Color(153, 153, 153));
        purchasesPanel.setLayout(new javax.swing.BoxLayout(purchasesPanel, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane2.setViewportView(purchasesPanel);

        purchases.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 800, 280));

        jPanel3.setBackground(new java.awt.Color(204, 0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(799, 50));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jPanel13.setBackground(new java.awt.Color(204, 0, 0));
        jPanel13.setMaximumSize(new java.awt.Dimension(120, 50));
        jPanel13.setMinimumSize(new java.awt.Dimension(120, 50));
        jPanel13.setPreferredSize(new java.awt.Dimension(120, 50));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label7.setForeground(new java.awt.Color(204, 204, 204));
        label7.setText("ID");
        jPanel13.add(label7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel3.add(jPanel13);

        jSeparator5.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator5);

        jPanel14.setBackground(new java.awt.Color(204, 0, 0));
        jPanel14.setMaximumSize(new java.awt.Dimension(160, 50));
        jPanel14.setMinimumSize(new java.awt.Dimension(160, 50));
        jPanel14.setPreferredSize(new java.awt.Dimension(160, 50));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label8.setForeground(new java.awt.Color(204, 204, 204));
        label8.setText("Fecha");
        jPanel14.add(label8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel3.add(jPanel14);

        jSeparator6.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator6);

        jPanel15.setBackground(new java.awt.Color(204, 0, 0));
        jPanel15.setMaximumSize(new java.awt.Dimension(180, 50));
        jPanel15.setMinimumSize(new java.awt.Dimension(180, 50));
        jPanel15.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label9.setForeground(new java.awt.Color(204, 204, 204));
        label9.setText("Proveedor");
        jPanel15.add(label9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel3.add(jPanel15);

        jSeparator7.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator7);

        jPanel16.setBackground(new java.awt.Color(204, 0, 0));
        jPanel16.setMaximumSize(new java.awt.Dimension(180, 50));
        jPanel16.setMinimumSize(new java.awt.Dimension(180, 50));
        jPanel16.setName(""); // NOI18N
        jPanel16.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label10.setForeground(new java.awt.Color(204, 204, 204));
        label10.setText("Número Elementos");
        jPanel16.add(label10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jPanel3.add(jPanel16);

        jSeparator8.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator8);

        jPanel17.setBackground(new java.awt.Color(204, 0, 0));
        jPanel17.setMaximumSize(new java.awt.Dimension(140, 50));
        jPanel17.setMinimumSize(new java.awt.Dimension(140, 50));
        jPanel17.setPreferredSize(new java.awt.Dimension(140, 50));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label11.setForeground(new java.awt.Color(204, 204, 204));
        label11.setText("Total");
        jPanel17.add(label11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel3.add(jPanel17);

        purchases.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));

        purchasesTab.add(purchases, "purchases");

        seePurchases.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Ver Compra");
        seePurchases.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 240, 40));

        deleteButton.setBackground(new java.awt.Color(42, 39, 41));
        deleteButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(204, 204, 204));
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icons/delete.png"))); // NOI18N
        deleteButton.setText("Eliminar");
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                deleteButtonMouseReleased(evt);
            }
        });
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        seePurchases.add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 500, 160, 50));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nombre Proveedor:");
        seePurchases.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 160, 20));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setText("Materiales Comprados:");
        seePurchases.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 160, 20));

        providerNameLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        providerNameLabel.setText("NULL");
        seePurchases.add(providerNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 460, 20));

        purchaseTotalLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        purchaseTotalLabel.setText("NULL");
        seePurchases.add(purchaseTotalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 480, 210, 20));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("ID Proveedor:");
        seePurchases.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 160, 20));

        providerIdLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        providerIdLabel.setText("NULL");
        seePurchases.add(providerIdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 460, 20));

        returnButton.setBackground(new java.awt.Color(42, 39, 41));
        returnButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        returnButton.setForeground(new java.awt.Color(204, 204, 204));
        returnButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icons/return.png"))); // NOI18N
        returnButton.setText("Volver");
        returnButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                returnButtonMouseReleased(evt);
            }
        });
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });
        seePurchases.add(returnButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 160, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Total Compra:");
        seePurchases.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, 150, 20));

        idLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idLabel.setText("NULL");
        seePurchases.add(idLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 460, 20));

        jPanel4.setBackground(new java.awt.Color(204, 0, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(799, 50));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        jPanel18.setBackground(new java.awt.Color(204, 0, 0));
        jPanel18.setMaximumSize(new java.awt.Dimension(120, 50));
        jPanel18.setMinimumSize(new java.awt.Dimension(120, 50));
        jPanel18.setPreferredSize(new java.awt.Dimension(120, 50));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label12.setForeground(new java.awt.Color(204, 204, 204));
        label12.setText("ID");
        jPanel18.add(label12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel4.add(jPanel18);

        jSeparator9.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator9);

        jPanel19.setBackground(new java.awt.Color(204, 0, 0));
        jPanel19.setMaximumSize(new java.awt.Dimension(160, 50));
        jPanel19.setMinimumSize(new java.awt.Dimension(160, 50));
        jPanel19.setPreferredSize(new java.awt.Dimension(160, 50));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label13.setForeground(new java.awt.Color(204, 204, 204));
        label13.setText("Nombre");
        jPanel19.add(label13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel4.add(jPanel19);

        jSeparator10.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator10);

        jPanel20.setBackground(new java.awt.Color(204, 0, 0));
        jPanel20.setMaximumSize(new java.awt.Dimension(180, 50));
        jPanel20.setMinimumSize(new java.awt.Dimension(180, 50));
        jPanel20.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label14.setForeground(new java.awt.Color(204, 204, 204));
        label14.setText("Precio Unitario");
        jPanel20.add(label14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel4.add(jPanel20);

        jSeparator11.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator11);

        jPanel21.setBackground(new java.awt.Color(204, 0, 0));
        jPanel21.setMaximumSize(new java.awt.Dimension(180, 50));
        jPanel21.setMinimumSize(new java.awt.Dimension(180, 50));
        jPanel21.setName(""); // NOI18N
        jPanel21.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label15.setForeground(new java.awt.Color(204, 204, 204));
        label15.setText("Cantidad");
        jPanel21.add(label15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel4.add(jPanel21);

        jSeparator12.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator12);

        jPanel22.setBackground(new java.awt.Color(204, 0, 0));
        jPanel22.setMaximumSize(new java.awt.Dimension(140, 50));
        jPanel22.setMinimumSize(new java.awt.Dimension(140, 50));
        jPanel22.setPreferredSize(new java.awt.Dimension(140, 50));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label16.setForeground(new java.awt.Color(204, 204, 204));
        label16.setText("Total");
        jPanel22.add(label16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel4.add(jPanel22);

        seePurchases.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        purchasedItemsPanel.setBackground(new java.awt.Color(153, 153, 153));
        purchasedItemsPanel.setLayout(new javax.swing.BoxLayout(purchasedItemsPanel, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane3.setViewportView(purchasedItemsPanel);

        seePurchases.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 800, 170));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText("Fecha de Compra:");
        seePurchases.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 160, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("ID:");
        seePurchases.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 150, 20));

        dateLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dateLabel.setText("NULL");
        seePurchases.add(dateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 470, 20));

        purchasesTab.add(seePurchases, "see");

        addPurchase.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Añadir Compra");
        addPurchase.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 240, 40));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText("Materiales Comprados:");
        addPurchase.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 160, 20));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Proveedor");
        addPurchase.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 160, 20));

        returnButton1.setBackground(new java.awt.Color(42, 39, 41));
        returnButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        returnButton1.setForeground(new java.awt.Color(204, 204, 204));
        returnButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icons/return.png"))); // NOI18N
        returnButton1.setText("Volver");
        returnButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                returnButton1MouseReleased(evt);
            }
        });
        returnButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButton1ActionPerformed(evt);
            }
        });
        addPurchase.add(returnButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 160, 50));

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
        label21.setText("Total");
        jPanel27.add(label21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel5.add(jPanel27);

        addPurchase.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        purchasedItemsPanel1.setBackground(new java.awt.Color(153, 153, 153));
        purchasedItemsPanel1.setLayout(new javax.swing.BoxLayout(purchasedItemsPanel1, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane4.setViewportView(purchasedItemsPanel1);

        addPurchase.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 800, 230));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("Fecha de Compra:");
        addPurchase.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 160, 20));

        providerSelect.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addPurchase.add(providerSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 210, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Total Compra:");
        addPurchase.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, 150, 20));

        purchaseTotalLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        purchaseTotalLabel1.setText("0");
        addPurchase.add(purchaseTotalLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 510, 210, 20));

        cancelButton.setBackground(new java.awt.Color(42, 39, 41));
        cancelButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(204, 204, 204));
        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        addPurchase.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 520, 160, 50));
        addPurchase.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 220, 30));

        confirmButton.setBackground(new java.awt.Color(42, 39, 41));
        confirmButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        confirmButton.setForeground(new java.awt.Color(204, 204, 204));
        confirmButton.setText("Añadir");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });
        addPurchase.add(confirmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 520, 160, 50));

        addItemButton.setBackground(new java.awt.Color(42, 39, 41));
        addItemButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addItemButton.setForeground(new java.awt.Color(204, 204, 204));
        addItemButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icons/add.png"))); // NOI18N
        addItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemButtonActionPerformed(evt);
            }
        });
        addPurchase.add(addItemButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, 40, 40));

        purchasesTab.add(addPurchase, "add");

        jTabbedPane1.addTab("Compras", purchasesTab);

        salesTab.setLayout(new java.awt.CardLayout());

        sales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editButton3.setBackground(new java.awt.Color(42, 39, 41));
        editButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        editButton3.setForeground(new java.awt.Color(204, 204, 204));
        editButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icons/search.png"))); // NOI18N
        editButton3.setText("Buscar");
        editButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                editButton3MouseReleased(evt);
            }
        });
        editButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButton3ActionPerformed(evt);
            }
        });
        sales.add(editButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 110, 50));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buscar Por...", "Nombre de Usuario", "ID", "Nombre de Personal" }));
        sales.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 170, 30));

        searchTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchTextField2.setToolTipText("");
        searchTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextField2ActionPerformed(evt);
            }
        });
        sales.add(searchTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 350, 30));

        addSaleButton.setBackground(new java.awt.Color(42, 39, 41));
        addSaleButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addSaleButton.setForeground(new java.awt.Color(204, 204, 204));
        addSaleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icons/add.png"))); // NOI18N
        addSaleButton.setText("Añadir");
        addSaleButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addSaleButtonMouseReleased(evt);
            }
        });
        addSaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSaleButtonActionPerformed(evt);
            }
        });
        sales.add(addSaleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 110, 110, 50));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setText("Gestionar Compras");
        sales.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 350, 40));

        salesPanel.setBackground(new java.awt.Color(153, 153, 153));
        salesPanel.setLayout(new javax.swing.BoxLayout(salesPanel, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane5.setViewportView(salesPanel);

        sales.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 800, 280));

        jPanel6.setBackground(new java.awt.Color(204, 0, 0));
        jPanel6.setPreferredSize(new java.awt.Dimension(799, 50));
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        jPanel28.setBackground(new java.awt.Color(204, 0, 0));
        jPanel28.setMaximumSize(new java.awt.Dimension(120, 50));
        jPanel28.setMinimumSize(new java.awt.Dimension(120, 50));
        jPanel28.setPreferredSize(new java.awt.Dimension(120, 50));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label22.setForeground(new java.awt.Color(204, 204, 204));
        label22.setText("ID");
        jPanel28.add(label22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel6.add(jPanel28);

        jSeparator17.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator17.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel6.add(jSeparator17);

        jPanel29.setBackground(new java.awt.Color(204, 0, 0));
        jPanel29.setMaximumSize(new java.awt.Dimension(160, 50));
        jPanel29.setMinimumSize(new java.awt.Dimension(160, 50));
        jPanel29.setPreferredSize(new java.awt.Dimension(160, 50));
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label23.setForeground(new java.awt.Color(204, 204, 204));
        label23.setText("Fecha");
        jPanel29.add(label23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel6.add(jPanel29);

        jSeparator18.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator18.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel6.add(jSeparator18);

        jPanel30.setBackground(new java.awt.Color(204, 0, 0));
        jPanel30.setMaximumSize(new java.awt.Dimension(180, 50));
        jPanel30.setMinimumSize(new java.awt.Dimension(180, 50));
        jPanel30.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label24.setForeground(new java.awt.Color(204, 204, 204));
        label24.setText("ID Cliente");
        jPanel30.add(label24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel6.add(jPanel30);

        jSeparator19.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator19.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel6.add(jSeparator19);

        jPanel31.setBackground(new java.awt.Color(204, 0, 0));
        jPanel31.setMaximumSize(new java.awt.Dimension(180, 50));
        jPanel31.setMinimumSize(new java.awt.Dimension(180, 50));
        jPanel31.setName(""); // NOI18N
        jPanel31.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label25.setForeground(new java.awt.Color(204, 204, 204));
        label25.setText("Método de Pago");
        jPanel31.add(label25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jPanel6.add(jPanel31);

        jSeparator20.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator20.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel6.add(jSeparator20);

        jPanel32.setBackground(new java.awt.Color(204, 0, 0));
        jPanel32.setMaximumSize(new java.awt.Dimension(140, 50));
        jPanel32.setMinimumSize(new java.awt.Dimension(140, 50));
        jPanel32.setPreferredSize(new java.awt.Dimension(140, 50));
        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label26.setForeground(new java.awt.Color(204, 204, 204));
        label26.setText("Total");
        jPanel32.add(label26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel6.add(jPanel32);

        sales.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));

        salesTab.add(sales, "purchases");

        seeSales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setText("Ver Compra");
        seeSales.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 240, 40));

        deleteButton1.setBackground(new java.awt.Color(42, 39, 41));
        deleteButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        deleteButton1.setForeground(new java.awt.Color(204, 204, 204));
        deleteButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icons/delete.png"))); // NOI18N
        deleteButton1.setText("Eliminar");
        deleteButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                deleteButton1MouseReleased(evt);
            }
        });
        deleteButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButton1ActionPerformed(evt);
            }
        });
        seeSales.add(deleteButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 500, 160, 50));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Nombre Proveedor:");
        seeSales.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 160, 20));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText("Materiales Comprados:");
        seeSales.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 160, 20));

        providerNameLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        providerNameLabel1.setText("NULL");
        seeSales.add(providerNameLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 460, 20));

        purchaseTotalLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        purchaseTotalLabel2.setText("NULL");
        seeSales.add(purchaseTotalLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 480, 210, 20));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setText("ID Proveedor:");
        seeSales.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 160, 20));

        providerIdLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        providerIdLabel1.setText("NULL");
        seeSales.add(providerIdLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 460, 20));

        returnButton2.setBackground(new java.awt.Color(42, 39, 41));
        returnButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        returnButton2.setForeground(new java.awt.Color(204, 204, 204));
        returnButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icons/return.png"))); // NOI18N
        returnButton2.setText("Volver");
        returnButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                returnButton2MouseReleased(evt);
            }
        });
        returnButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButton2ActionPerformed(evt);
            }
        });
        seeSales.add(returnButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 160, 50));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Total Compra:");
        seeSales.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, 150, 20));

        idLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idLabel1.setText("NULL");
        seeSales.add(idLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 460, 20));

        jPanel7.setBackground(new java.awt.Color(204, 0, 0));
        jPanel7.setPreferredSize(new java.awt.Dimension(799, 50));
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.LINE_AXIS));

        jPanel33.setBackground(new java.awt.Color(204, 0, 0));
        jPanel33.setMaximumSize(new java.awt.Dimension(120, 50));
        jPanel33.setMinimumSize(new java.awt.Dimension(120, 50));
        jPanel33.setPreferredSize(new java.awt.Dimension(120, 50));
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label27.setForeground(new java.awt.Color(204, 204, 204));
        label27.setText("ID");
        jPanel33.add(label27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel7.add(jPanel33);

        jSeparator21.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator21.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel7.add(jSeparator21);

        jPanel34.setBackground(new java.awt.Color(204, 0, 0));
        jPanel34.setMaximumSize(new java.awt.Dimension(160, 50));
        jPanel34.setMinimumSize(new java.awt.Dimension(160, 50));
        jPanel34.setPreferredSize(new java.awt.Dimension(160, 50));
        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label28.setForeground(new java.awt.Color(204, 204, 204));
        label28.setText("Nombre");
        jPanel34.add(label28, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel7.add(jPanel34);

        jSeparator22.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator22.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel7.add(jSeparator22);

        jPanel35.setBackground(new java.awt.Color(204, 0, 0));
        jPanel35.setMaximumSize(new java.awt.Dimension(180, 50));
        jPanel35.setMinimumSize(new java.awt.Dimension(180, 50));
        jPanel35.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label29.setForeground(new java.awt.Color(204, 204, 204));
        label29.setText("Precio Unitario");
        jPanel35.add(label29, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel7.add(jPanel35);

        jSeparator23.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator23.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel7.add(jSeparator23);

        jPanel36.setBackground(new java.awt.Color(204, 0, 0));
        jPanel36.setMaximumSize(new java.awt.Dimension(180, 50));
        jPanel36.setMinimumSize(new java.awt.Dimension(180, 50));
        jPanel36.setName(""); // NOI18N
        jPanel36.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label30.setForeground(new java.awt.Color(204, 204, 204));
        label30.setText("Cantidad");
        jPanel36.add(label30, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel7.add(jPanel36);

        jSeparator24.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator24.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel7.add(jSeparator24);

        jPanel37.setBackground(new java.awt.Color(204, 0, 0));
        jPanel37.setMaximumSize(new java.awt.Dimension(140, 50));
        jPanel37.setMinimumSize(new java.awt.Dimension(140, 50));
        jPanel37.setPreferredSize(new java.awt.Dimension(140, 50));
        jPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label31.setForeground(new java.awt.Color(204, 204, 204));
        label31.setText("Total");
        jPanel37.add(label31, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel7.add(jPanel37);

        seeSales.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        purchasedItemsPanel2.setBackground(new java.awt.Color(153, 153, 153));
        purchasedItemsPanel2.setLayout(new javax.swing.BoxLayout(purchasedItemsPanel2, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane6.setViewportView(purchasedItemsPanel2);

        seeSales.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 800, 170));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Fecha de Compra:");
        seeSales.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 160, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("ID:");
        seeSales.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 150, 20));

        dateLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dateLabel1.setText("NULL");
        seeSales.add(dateLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 470, 20));

        salesTab.add(seeSales, "see");

        addSale.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setText("Añadir Venta");
        addSale.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 240, 40));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setText("Productos Comprados:");
        addSale.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 220, 20));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Cliente");
        addSale.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 160, 20));

        returnButton3.setBackground(new java.awt.Color(42, 39, 41));
        returnButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        returnButton3.setForeground(new java.awt.Color(204, 204, 204));
        returnButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icons/return.png"))); // NOI18N
        returnButton3.setText("Volver");
        returnButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                returnButton3MouseReleased(evt);
            }
        });
        returnButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButton3ActionPerformed(evt);
            }
        });
        addSale.add(returnButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 40, 160, 50));

        jPanel8.setBackground(new java.awt.Color(204, 0, 0));
        jPanel8.setPreferredSize(new java.awt.Dimension(799, 50));
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.LINE_AXIS));

        jPanel38.setBackground(new java.awt.Color(204, 0, 0));
        jPanel38.setMaximumSize(new java.awt.Dimension(120, 50));
        jPanel38.setMinimumSize(new java.awt.Dimension(120, 50));
        jPanel38.setPreferredSize(new java.awt.Dimension(120, 50));
        jPanel38.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label32.setForeground(new java.awt.Color(204, 204, 204));
        label32.setText("ID");
        jPanel38.add(label32, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel8.add(jPanel38);

        jSeparator25.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator25.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator25);

        jPanel39.setBackground(new java.awt.Color(204, 0, 0));
        jPanel39.setMaximumSize(new java.awt.Dimension(160, 50));
        jPanel39.setMinimumSize(new java.awt.Dimension(160, 50));
        jPanel39.setPreferredSize(new java.awt.Dimension(160, 50));
        jPanel39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label33.setForeground(new java.awt.Color(204, 204, 204));
        label33.setText("Nombre");
        jPanel39.add(label33, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel8.add(jPanel39);

        jSeparator26.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator26.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator26);

        jPanel40.setBackground(new java.awt.Color(204, 0, 0));
        jPanel40.setMaximumSize(new java.awt.Dimension(180, 50));
        jPanel40.setMinimumSize(new java.awt.Dimension(180, 50));
        jPanel40.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel40.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label34.setForeground(new java.awt.Color(204, 204, 204));
        label34.setText("Precio Unitario");
        jPanel40.add(label34, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel8.add(jPanel40);

        jSeparator27.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator27.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator27);

        jPanel41.setBackground(new java.awt.Color(204, 0, 0));
        jPanel41.setMaximumSize(new java.awt.Dimension(180, 50));
        jPanel41.setMinimumSize(new java.awt.Dimension(180, 50));
        jPanel41.setName(""); // NOI18N
        jPanel41.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel41.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label35.setForeground(new java.awt.Color(204, 204, 204));
        label35.setText("Cantidad");
        jPanel41.add(label35, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel8.add(jPanel41);

        jSeparator28.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator28.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator28);

        jPanel42.setBackground(new java.awt.Color(204, 0, 0));
        jPanel42.setMaximumSize(new java.awt.Dimension(140, 50));
        jPanel42.setMinimumSize(new java.awt.Dimension(140, 50));
        jPanel42.setPreferredSize(new java.awt.Dimension(140, 50));
        jPanel42.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label36.setForeground(new java.awt.Color(204, 204, 204));
        label36.setText("Total");
        jPanel42.add(label36, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel8.add(jPanel42);

        addSale.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        purchasedItemsPanel3.setBackground(new java.awt.Color(153, 153, 153));
        purchasedItemsPanel3.setLayout(new javax.swing.BoxLayout(purchasedItemsPanel3, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane7.setViewportView(purchasedItemsPanel3);

        addSale.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 800, 230));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setText("Fecha de Venta:");
        addSale.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 160, 20));

        providerSelect1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addSale.add(providerSelect1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 210, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Total Compra:");
        addSale.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, 150, 20));

        purchaseTotalLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        purchaseTotalLabel3.setText("0");
        addSale.add(purchaseTotalLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 510, 210, 20));

        cancelButton1.setBackground(new java.awt.Color(42, 39, 41));
        cancelButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cancelButton1.setForeground(new java.awt.Color(204, 204, 204));
        cancelButton1.setText("Cancelar");
        cancelButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButton1ActionPerformed(evt);
            }
        });
        addSale.add(cancelButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 520, 160, 50));
        addSale.add(dateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 220, 30));

        confirmButton1.setBackground(new java.awt.Color(42, 39, 41));
        confirmButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        confirmButton1.setForeground(new java.awt.Color(204, 204, 204));
        confirmButton1.setText("Añadir");
        confirmButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButton1ActionPerformed(evt);
            }
        });
        addSale.add(confirmButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 520, 160, 50));

        addItemButton1.setBackground(new java.awt.Color(42, 39, 41));
        addItemButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addItemButton1.setForeground(new java.awt.Color(204, 204, 204));
        addItemButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icons/add.png"))); // NOI18N
        addItemButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemButton1ActionPerformed(evt);
            }
        });
        addSale.add(addItemButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, 40, 40));

        salesTab.add(addSale, "add");

        jTabbedPane1.addTab("Ventas", salesTab);

        add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void addPurchaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPurchaseButtonActionPerformed
        this.items.clear();
        ((CardLayout) this.purchasesTab.getLayout()).show(this.purchasesTab, "add");
    }//GEN-LAST:event_addPurchaseButtonActionPerformed

    private void addPurchaseButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPurchaseButtonMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_addPurchaseButtonMouseReleased

    private void searchTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextField1ActionPerformed

    private void editButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editButton2ActionPerformed

    private void editButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButton2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_editButton2MouseReleased

    private void deleteButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseReleased

    }//GEN-LAST:event_deleteButtonMouseReleased

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        DesktopApplication.getInstance().getPurchases().remove(this.purchase);
        this.purchase = null;
        ((CardLayout) this.purchasesTab.getLayout()).show(this.purchasesTab, "purchases");
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void returnButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnButtonMouseReleased

    }//GEN-LAST:event_returnButtonMouseReleased

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        ((CardLayout) this.purchasesTab.getLayout()).show(this.purchasesTab, "purchases");
    }//GEN-LAST:event_returnButtonActionPerformed

    private void returnButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnButton1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_returnButton1MouseReleased

    private void returnButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_returnButton1ActionPerformed

    private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
        JTextField idField = new JTextField();
        JTextField quantityField = new JTextField();
        Object[] message = {
            "ID del Material:", idField,
            "Cantidad:", quantityField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Añadir Material", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Material material = null;
            int id = Integer.parseInt(idField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            
            for (Material m : DesktopApplication.getInstance().getMaterials()) {
                if (m.getId() == id) {
                    PurchasedListItem item = new PurchasedListItem(m, quantity, true);
                    this.purchasedItemsPanel1.add(item);
                    final SalesAndPurchasesMenu menu = this;
                    
                    item.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent evt) {
                            menu.purchasedListItem1MousePressed(evt);
                        }
                    });
                    
                    this.purchaseTotalLabel1.setText(Double.toString(this.purchaseTotalFromList(this.purchasedItemsPanel1)));
                    break;
                }
            }
            
            if (material == null)
                JOptionPane.showMessageDialog(this, "El material no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addItemButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        ((CardLayout) this.purchasesTab.getLayout()).show(this.purchasesTab, "purchases");
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        ArrayList<Purchase> purchases = DesktopApplication.getInstance().getPurchases();
        purchases.add(new Purchase(purchases.size(), this.dateChooser.getDate(), (Provider) this.providerSelect.getSelectedItem(), this.purchaseListToMap(this.purchasedItemsPanel1)));
        javax.swing.JOptionPane.showMessageDialog(null, "Se ha añadido la compra");
        this.getAllPurchases();
        ((CardLayout) this.purchasesTab.getLayout()).show(this.purchasesTab, "purchases");
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void editButton3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButton3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_editButton3MouseReleased

    private void editButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editButton3ActionPerformed

    private void searchTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextField2ActionPerformed

    private void addSaleButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSaleButtonMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_addSaleButtonMouseReleased

    private void addSaleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSaleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addSaleButtonActionPerformed

    private void deleteButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButton1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteButton1MouseReleased

    private void deleteButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteButton1ActionPerformed

    private void returnButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnButton2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_returnButton2MouseReleased

    private void returnButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_returnButton2ActionPerformed

    private void returnButton3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnButton3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_returnButton3MouseReleased

    private void returnButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_returnButton3ActionPerformed

    private void cancelButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelButton1ActionPerformed

    private void confirmButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmButton1ActionPerformed

    private void addItemButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addItemButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addItemButton;
    private javax.swing.JButton addItemButton1;
    private javax.swing.JPanel addPurchase;
    private javax.swing.JButton addPurchaseButton;
    private javax.swing.JPanel addSale;
    private javax.swing.JButton addSaleButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton cancelButton1;
    private javax.swing.JButton confirmButton;
    private javax.swing.JButton confirmButton1;
    private com.toedter.calendar.JDateChooser dateChooser;
    private com.toedter.calendar.JDateChooser dateChooser1;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel dateLabel1;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton deleteButton1;
    private javax.swing.JButton editButton2;
    private javax.swing.JButton editButton3;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel idLabel1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator28;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel label10;
    private javax.swing.JLabel label11;
    private javax.swing.JLabel label12;
    private javax.swing.JLabel label13;
    private javax.swing.JLabel label14;
    private javax.swing.JLabel label15;
    private javax.swing.JLabel label16;
    private javax.swing.JLabel label17;
    private javax.swing.JLabel label18;
    private javax.swing.JLabel label19;
    private javax.swing.JLabel label20;
    private javax.swing.JLabel label21;
    private javax.swing.JLabel label22;
    private javax.swing.JLabel label23;
    private javax.swing.JLabel label24;
    private javax.swing.JLabel label25;
    private javax.swing.JLabel label26;
    private javax.swing.JLabel label27;
    private javax.swing.JLabel label28;
    private javax.swing.JLabel label29;
    private javax.swing.JLabel label30;
    private javax.swing.JLabel label31;
    private javax.swing.JLabel label32;
    private javax.swing.JLabel label33;
    private javax.swing.JLabel label34;
    private javax.swing.JLabel label35;
    private javax.swing.JLabel label36;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label8;
    private javax.swing.JLabel label9;
    private javax.swing.JLabel providerIdLabel;
    private javax.swing.JLabel providerIdLabel1;
    private javax.swing.JLabel providerNameLabel;
    private javax.swing.JLabel providerNameLabel1;
    private javax.swing.JComboBox<Provider
    > providerSelect;
    private javax.swing.JComboBox<Provider
    > providerSelect1;
    private javax.swing.JLabel purchaseTotalLabel;
    private javax.swing.JLabel purchaseTotalLabel1;
    private javax.swing.JLabel purchaseTotalLabel2;
    private javax.swing.JLabel purchaseTotalLabel3;
    private javax.swing.JPanel purchasedItemsPanel;
    private javax.swing.JPanel purchasedItemsPanel1;
    private javax.swing.JPanel purchasedItemsPanel2;
    private javax.swing.JPanel purchasedItemsPanel3;
    private javax.swing.JPanel purchases;
    private javax.swing.JPanel purchasesPanel;
    private javax.swing.JPanel purchasesTab;
    private javax.swing.JButton returnButton;
    private javax.swing.JButton returnButton1;
    private javax.swing.JButton returnButton2;
    private javax.swing.JButton returnButton3;
    private javax.swing.JPanel sales;
    private javax.swing.JPanel salesPanel;
    private javax.swing.JPanel salesTab;
    private javax.swing.JTextField searchTextField1;
    private javax.swing.JTextField searchTextField2;
    private javax.swing.JPanel seePurchases;
    private javax.swing.JPanel seeSales;
    // End of variables declaration//GEN-END:variables
}
