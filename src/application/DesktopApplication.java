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
    private ArrayList<Profile> profiles;
    private ArrayList<Personel> personel;
    private ArrayList<Client> clients;
    private ArrayList<Purchase> purchases;
    private ArrayList<Sale> sales;
    private ArrayList<Provider> providers;
    private ArrayList<Material> materials;
    private ArrayList<Product> products;
    private Profile currentProfile;
    private HashMap<Purchasable, Integer> stock;
    
    private DesktopApplication() {
        super("Carpintería Peña");
        this.factory = new ViewFactory();
        this.profiles = new ArrayList<>();
        this.personel = new ArrayList<>();
        this.purchases = new ArrayList<>();
        this.sales = new ArrayList<>();
        this.providers = new ArrayList<>();
        this.materials = new ArrayList<>();
        this.products = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.stock = new HashMap<>();
        
        this.currentProfile = null;
        this.personel.add(new Personel(0, 4000000, Helpers.dateFrom(2014, 9, 11), PersonelType.Gerente, "Juan Peña Mesa", Helpers.dateFrom(1985, 2, 10), 3003463, "carpipeña@gmail.com", System.getenv("USERPROFILE") + "\\Documents\\NetBeansProjects\\GUIPeña\\testProfileImages\\360_F_222851624_jfoMGbJxwRi5AWGdPgXKSABMnzCQo9RN.jpg"));
        this.profiles.add(new Profile("carpiadmin", MySecurityManager.getInstance().hash("carpiadmin".toCharArray()) ,this.personel.get(0), ProfileRole.Admin));
        this.providers.add(new Provider(0, "Maderas Barranquilla", "Barranquilla", "Cra 53 # 64 - 25", 3012934, "madebaquilla@hotmail.com"));
        this.providers.add(new Provider(1, "Leña Fernández", "Barranquilla", "Cra 89 # 34 - 95", 3967876, "amfernandez@gmail.com"));
        this.providers.add(new Provider(2, "Ferreteria el Lugar", "Puerto Colombia", "Cra 19 # 24 - 56", 3462453, "ellugarferreteria@gmail.com"));
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public ArrayList<Profile> getProfiles() {
        return profiles;
    }

    public ArrayList<Personel> getPersonel() {
        return personel;
    }

    public ArrayList<Provider> getProviders() {
        return providers;
    }

    public ArrayList<Material> getMaterials() {
        return materials;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public HashMap<Purchasable, Integer> getStock() {
        return stock;
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
