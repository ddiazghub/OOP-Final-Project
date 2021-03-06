/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package application.components;

import application.logic.Client;
import application.logic.Personel;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author david
 */
public class ClientListItem extends javax.swing.JPanel {

    private Client client;
    private JPanel[] panels;
    /**
     * Creates new form ProfileListItem
     */
    public ClientListItem(Client client) {
        initComponents();
        
        this.panels = new JPanel[] {
            emailPanel,
            idPanel,
            clientTypePane,
            phoneNumberPanel,
            usernamePanel
        };
        
        this.setClient(client);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
        this.refresh();
    }
    
    
    public void refresh() {
        this.idLabel.setText(Integer.toString(client.getId()));
        this.nameLabel.setText(client.getName());
        this.emailLabel.setText(client.getEmail());
        this.clientTypeLabel.setText(client.getType().toString());
        this.phoneNumberLabel.setText(Integer.toString(client.getPhoneNumber()));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idPanel = new javax.swing.JPanel();
        idLabel = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        usernamePanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        emailPanel = new javax.swing.JPanel();
        emailLabel = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        clientTypePane = new javax.swing.JPanel();
        clientTypeLabel = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        phoneNumberPanel = new javax.swing.JPanel();
        phoneNumberLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(797, 30));
        setMinimumSize(new java.awt.Dimension(797, 30));
        setPreferredSize(new java.awt.Dimension(797, 30));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        idPanel.setBackground(new java.awt.Color(42, 39, 41));
        idPanel.setMaximumSize(new java.awt.Dimension(120, 30));
        idPanel.setMinimumSize(new java.awt.Dimension(120, 30));
        idPanel.setPreferredSize(new java.awt.Dimension(120, 30));
        idPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        idLabel.setForeground(new java.awt.Color(204, 204, 204));
        idLabel.setText("ID");
        idPanel.add(idLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        jSeparator10.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);

        usernamePanel.setBackground(new java.awt.Color(42, 39, 41));
        usernamePanel.setMaximumSize(new java.awt.Dimension(160, 30));
        usernamePanel.setMinimumSize(new java.awt.Dimension(160, 30));
        usernamePanel.setPreferredSize(new java.awt.Dimension(160, 30));
        usernamePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(204, 204, 204));
        nameLabel.setText("Nombre de Usuario");
        usernamePanel.add(nameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, -1));

        jSeparator9.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        emailPanel.setBackground(new java.awt.Color(42, 39, 41));
        emailPanel.setMaximumSize(new java.awt.Dimension(180, 30));
        emailPanel.setMinimumSize(new java.awt.Dimension(180, 30));
        emailPanel.setPreferredSize(new java.awt.Dimension(180, 30));
        emailPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        emailLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(204, 204, 204));
        emailLabel.setText("Correo Electr??nico");
        emailPanel.add(emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, -1));

        jSeparator11.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        clientTypePane.setBackground(new java.awt.Color(42, 39, 41));
        clientTypePane.setMaximumSize(new java.awt.Dimension(180, 30));
        clientTypePane.setMinimumSize(new java.awt.Dimension(180, 30));
        clientTypePane.setName(""); // NOI18N
        clientTypePane.setPreferredSize(new java.awt.Dimension(180, 30));
        clientTypePane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        clientTypeLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        clientTypeLabel.setForeground(new java.awt.Color(204, 204, 204));
        clientTypeLabel.setText("Cargo");
        clientTypePane.add(clientTypeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, -1));

        jSeparator12.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);

        phoneNumberPanel.setBackground(new java.awt.Color(42, 39, 41));
        phoneNumberPanel.setMaximumSize(new java.awt.Dimension(140, 30));
        phoneNumberPanel.setMinimumSize(new java.awt.Dimension(140, 30));
        phoneNumberPanel.setPreferredSize(new java.awt.Dimension(140, 30));
        phoneNumberPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        phoneNumberLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        phoneNumberLabel.setForeground(new java.awt.Color(204, 204, 204));
        phoneNumberLabel.setText("N??mero Telef??nico");
        phoneNumberPanel.add(phoneNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 797, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(idPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(usernamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(emailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(clientTypePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(phoneNumberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(idPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(usernamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(clientTypePane, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(phoneNumberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        this.setBackground(new Color(86, 82, 85));
        
        for (JPanel panel : this.panels)
            panel.setBackground(new Color(86, 82, 85));
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        this.setBackground(new Color(42, 39, 41));
        
        for (JPanel panel : this.panels)
            panel.setBackground(new Color(42, 39, 41));
    }//GEN-LAST:event_formMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clientTypeLabel;
    private javax.swing.JPanel clientTypePane;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JPanel emailPanel;
    private javax.swing.JLabel idLabel;
    private javax.swing.JPanel idPanel;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JPanel phoneNumberPanel;
    private javax.swing.JPanel usernamePanel;
    // End of variables declaration//GEN-END:variables
}
