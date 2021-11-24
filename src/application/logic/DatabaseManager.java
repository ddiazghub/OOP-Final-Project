/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.logic;

import application.DesktopApplication;
import java.security.SecureRandom;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author david
 */
public class DatabaseManager {
    public static byte[] salt;
    private static DatabaseManager instance;
    private Connection connection;
    private HashMap<String, String> queries;
    private DatabaseManager() {
        this.queries = new HashMap<>();
        this.queries.put("Clients", "SELECT c.\"Id\", c.\"Name\", c.\"Birthday\", c.\"Email\", c.\"PhoneNumber\", m.\"Name\" AS \"Type\", c.\"StreetAddress\", c.\"City\" FROM \"Clients\" c INNER JOIN \"ClientMembershipTypes\" m ON c.\"MemberShipTypeId\" = m.\"Id\"");
        this.queries.put("Personel", "SELECT p.\"Id\", p.\"Name\", p.\"Birthday\", p.\"Email\", p.\"PhoneNumber\", p.\"Salary\", p.\"AdmissionDate\", t.\"Name\" AS \"Type\", p.\"ProfileId\", p.\"ProfileImagePath\" FROM \"Personel\" p INNER JOIN \"PersonelTypes\" t ON p.\"PersonelTypeId\" = t.\"Id\"");
        this.queries.put("Profiles", "SELECT \"Id\", \"Username\", \"PasswordHash\", \"PersonelId\", \"RoleId\" FROM public.\"Profiles\"");
        this.queries.put("Products", "SELECT * FROM \"Products\"");
        this.queries.put("Materials", "SELECT * FROM \"Materials\"");
        this.queries.put("Providers", "SELECT * FROM public.\"Providers\"");
        this.queries.put("Purchases", "SELECT * FROM public.\"Purchases\"");
        this.queries.put("Sales", "SELECT * FROM public.\"Sales\"");
        this.queries.put("MaterialStock", "SELECT * FROM public.\"MaterialStock\"");
        this.queries.put("ProductStock", "SELECT * FROM public.\"ProductStock\"");
    }
    
    public void close() {
        try {
            this.connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void load() {
        try {
            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            props.setProperty("user", System.getenv("DB_USERNAME"));
            props.setProperty("password", System.getenv("DB_PASSWORD"));
            
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/penaDatabase", props);
            
            ArrayList<Personel> personel = selectPersonel();
            if (personel.isEmpty()) {
                Personel manager = new Personel(1, 4000000, Helpers.dateFrom(2014, 9, 11), PersonelType.Gerente, "Juan Diego Peña Mesa", Helpers.dateFrom(1985, 2, 10), 3003463, "carpipeña@gmail.com", System.getenv("USERPROFILE") + "\\Documents\\NetBeansProjects\\GUIPeña\\testProfileImages\\360_F_222851624_jfoMGbJxwRi5AWGdPgXKSABMnzCQo9RN.jpg");
                insertPersonel(manager);
            }
            
            if (selectProfiles().isEmpty())
                insertProfile(new Profile("carpiadmin", MySecurityManager.getInstance().hash("carpiadmin".toCharArray()), personel.get(0), ProfileRole.Admin));

            if (getSalt() == null) {
                SecureRandom random = new SecureRandom();
                salt = new byte[16];
                random.nextBytes(salt);
                System.out.println(Arrays.toString(salt));
                setSalt(salt);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            JOptionPane.showMessageDialog(DesktopApplication.getInstance(), "No se ha podido establecer la conexión a la base de datos." + e.getClass().getName()+": "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void removeProfile(int id) {
        removeById("Profiles", id);
    }
    
    public static void removeClient(int id) {
        removeById("Clients", id);
    }
    
    public static void removePersonel(int id) {
        removeById("Personel", id);
    }
    
    public static void removePurchase(int id) {
        removeById("Purchases", id);
    }
    
    public static void removeSale(int id) {
        removeById("Sales", id);
    }
    
    public static void removeById(String table, int id) {
        try {
            PreparedStatement st = instance.connection.prepareStatement("DELETE FROM public.\"?\"\n" + "WHERE \"Id\"=?");
            st.setString(1, table);
            st.setInt(2, id);
            ResultSet rs = st.executeQuery();
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static byte[] getSalt() {
        byte[] salt = null;
        
        try {
            Statement st = instance.connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM public.\"PasswordSalt\"");
            
            if (rs.next())
                salt = rs.getBytes("Salt");
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return salt;
    }
    
    public static void setSalt(byte[] salt) {
        try {
            PreparedStatement st = instance.connection.prepareStatement("INSERT INTO public.\"PasswordSalt\"(\n" +
"	\"Salt\")\n" +
"	VALUES (?)");
            st.setBytes(1, salt);
            ResultSet rs = st.executeQuery();
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static ArrayList<Client> selectClients() {
        ArrayList<Client> clients = null;
        
        try {
            clients = new ArrayList<>();
            Statement st = instance.connection.createStatement();
            ResultSet rs = st.executeQuery(instance.queries.get("Clients"));
            
            while (rs.next())
                clients.add(new Client(rs.getInt("Id"), rs.getString("StreetAddress"), rs.getString("City"), rs.getString("Name"), rs.getTimestamp("Birthday"), rs.getInt("PhoneNumber"), rs.getString("Email")));
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return clients;
    }
    
    public static Client selectClient(int id) {
        try {
            PreparedStatement st = instance.connection.prepareStatement(instance.queries.get("Clients") + " WHERE c.\"Id\" = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            Client client = null;
            
            if (rs.next())
                client = new Client(rs.getInt("Id"), rs.getString("StreetAddress"), rs.getString("City"), rs.getString("Name"), rs.getTimestamp("Birthday"), rs.getInt("PhoneNumber"), rs.getString("Email"));
            
            rs.close();
            st.close();
            return client;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static void insertClient(Client client) {
        try {
            PreparedStatement st = instance.connection.prepareStatement("INSERT INTO public.\"Clients\"(\n" +
            "	\"Name\", \"Birthday\", \"Email\", \"PhoneNumber\", \"MemberShipTypeId\", \"StreetAddress\", \"City\")\n" +
            "	VALUES (?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, client.getName());
            st.setTimestamp(2, Timestamp.from(client.getBirthDay().toInstant()));
            st.setString(3, client.getEmail());
            st.setInt(4, client.getPhoneNumber());
            st.setInt(5, ClientMembershipType.Nuevo.getValue());
            st.setString(6, client.getStreetAddress());
            st.setString(7, client.getCity());
            ResultSet rs = st.executeQuery();
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Personel> selectPersonel() {
        ArrayList<Personel> personel = null;
        
        try {
            personel = new ArrayList<>();
            Statement st = instance.connection.createStatement();
            ResultSet rs = st.executeQuery(instance.queries.get("Personel"));
            
            while (rs.next())
                personel.add(new Personel(rs.getInt("Id"), rs.getDouble("Salary"), rs.getTimestamp("AdmissionDate"), PersonelType.valueOf(rs.getString("Type")), rs.getString("Name"), rs.getTimestamp("Birthday"), rs.getInt("PhoneNumber"), rs.getString("Email"), rs.getString("ProfileImagePath")));
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return personel;
    }
    
    public static Personel selectPersonel(int id) {
        try {
            PreparedStatement st = instance.connection.prepareStatement(instance.queries.get("Personel") + " WHERE p.\"Id\" = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            Personel personel = null;
            if (rs.next())
                personel = new Personel(rs.getInt("Id"), rs.getDouble("Salary"), rs.getTimestamp("AdmissionDate"), PersonelType.valueOf(rs.getString("Type")), rs.getString("Name"), rs.getTimestamp("Birthday"), rs.getInt("PhoneNumber"), rs.getString("Email"), rs.getString("ProfileImagePath"));
            
            rs.close();
            st.close();
            return personel;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static void insertPersonel(Personel personel) {
        try {
            PreparedStatement st = instance.connection.prepareStatement("INSERT INTO public.\"Personel\"(\n" +
"	\"Name\", \"Birthday\", \"Email\", \"PhoneNumber\", \"Salary\", \"AdmissionDate\", \"PersonelTypeId\", \"ProfileId\", \"ProfileImagePath\")\n" +
"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, personel.getName());
            st.setTimestamp(2, Timestamp.from(personel.getBirthDay().toInstant()));
            st.setString(3, personel.getEmail());
            st.setInt(4, personel.getPhoneNumber());
            st.setDouble(5, personel.getSalary());
            st.setTimestamp(6, Timestamp.from(personel.getAdmissionDate().toInstant()));
            st.setInt(7, personel.getType().getValue());
            if (personel.hasProfile()) st.setInt(8, personel.getProfile().getId());
            else st.setNull(8, Types.BIGINT);
            st.setString(9, personel.getImagePath());
            ResultSet rs = st.executeQuery();
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updatePersonel(Personel personel) {
        try {
            PreparedStatement st = instance.connection.prepareStatement("UPDATE public.\"Personel\"\n" +
"	SET \"Name\"=?, \"Birthday\"=?, \"Email\"=?, \"PhoneNumber\"=?, \"Salary\"=?, \"AdmissionDate\"=?, \"PersonelTypeId\"=?, \"ProfileId\"=?, \"ProfileImagePath\"=?\n" +
"	WHERE \"Id\" = ?");
            st.setString(1, personel.getName());
            st.setTimestamp(2, Timestamp.from(personel.getBirthDay().toInstant()));
            st.setString(3, personel.getEmail());
            st.setInt(4, personel.getPhoneNumber());
            st.setDouble(5, personel.getSalary());
            st.setTimestamp(6, Timestamp.from(personel.getAdmissionDate().toInstant()));
            st.setInt(7, personel.getType().getValue());
            if (personel.hasProfile()) st.setInt(8, personel.getProfile().getId());
            else st.setNull(8, Types.BIGINT);
            st.setString(9, personel.getImagePath());
            st.setInt(10, personel.getId());
            ResultSet rs = st.executeQuery();
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static ArrayList<Profile> selectProfiles() {
        ArrayList<Profile> profiles = null;
        
        try {
            profiles = new ArrayList<>();
            Statement st = instance.connection.createStatement();
            ResultSet rs = st.executeQuery(instance.queries.get("Profiles"));
            
            while (rs.next())
                profiles.add(new Profile(rs.getString("Username"), rs.getBytes("PasswordHash"), selectPersonel(rs.getInt("PersonelId")), ProfileRole.get(rs.getInt("RoleId"))));
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return profiles;
    }
    
    public static ArrayList<Profile> selectAdmins() {
        ArrayList<Profile> profiles = null;
        
        try {
            profiles = new ArrayList<>();
            Statement st = instance.connection.createStatement();
            ResultSet rs = st.executeQuery(instance.queries.get("Profiles" + " WHERE \"RoleId\" = 2"));
            
            while (rs.next())
                profiles.add(new Profile(rs.getString("Username"), rs.getBytes("PasswordHash"), selectPersonel(rs.getInt("PersonelId")), ProfileRole.get(rs.getInt("RoleId"))));
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return profiles;
    }
    
    public static Profile selectProfile (int id) {
        try {
            PreparedStatement st = instance.connection.prepareStatement(instance.queries.get("Profiles") + " WHERE \"Id\" = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            Profile profile = null;
            if (rs.next())
                profile = new Profile(rs.getString("Username"), rs.getBytes("PasswordHash"), selectPersonel(rs.getInt("PersonelId")), ProfileRole.get(rs.getInt("RoleId")));
            
            rs.close();
            st.close();
            return profile;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static Profile selectProfile (String username, char[] password) {
        try {
            PreparedStatement st = instance.connection.prepareStatement(instance.queries.get("Profiles") + " WHERE \"Username\" = ? AND \"PasswordHash\" = ?");
            st.setString(1, username);
            st.setBytes(2, MySecurityManager.getInstance().hash(password));
            ResultSet rs = st.executeQuery();
            
            Profile profile = null;
            if (rs.next())
                profile = new Profile(rs.getString("Username"), rs.getBytes("PasswordHash"), selectPersonel(rs.getInt("PersonelId")), ProfileRole.get(rs.getInt("RoleId")));
            
            rs.close();
            st.close();
            return profile;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static void insertProfile(Profile profile) {
        try {
            PreparedStatement st = instance.connection.prepareStatement("INSERT INTO public.\"Profiles\"(\n" +
"	\"Id\", \"Username\", \"PasswordHash\", \"PersonelId\", \"RoleId\")\n" +
"	VALUES (?, ?, ?, ?, ?)");
            st.setInt(1, profile.getPersonel().getId());
            st.setString(2, profile.getUsername());
            st.setBytes(3, profile.getPasswordHash());
            st.setInt(4, profile.getPersonel().getId());
            st.setDouble(5, profile.getRole().getValue());
            ResultSet rs = st.executeQuery();
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateProfile(Profile profile) {
        try {
            PreparedStatement st = instance.connection.prepareStatement("UPDATE public.\"Profiles\"\n" +
"	SET \"Username\"=?, \"PasswordHash\"=?, \"PersonelId\"=?, \"RoleId\"=?\n" +
"	WHERE \"Id\"=?");
            st.setInt(5, profile.getPersonel().getId());
            st.setString(1, profile.getUsername());
            st.setBytes(2, profile.getPasswordHash());
            st.setInt(3, profile.getPersonel().getId());
            st.setDouble(4, profile.getRole().getValue());
            ResultSet rs = st.executeQuery();
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Product> selectProducts() {
        ArrayList<Product> products = null;
        
        try {
            products = new ArrayList<>();
            Statement st = instance.connection.createStatement();
            ResultSet rs = st.executeQuery(instance.queries.get("Products"));
            
            while (rs.next())
                products.add(new Product(rs.getInt("Id"), rs.getString("Name"), rs.getDouble("Cost"), rs.getString("Description")));
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }
    
    public static Product selectProduct(int id) {
        Product product = null;
        
        try {
            PreparedStatement st = instance.connection.prepareStatement(instance.queries.get("Products") + " WHERE \"Id\" = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            if (rs.next())
                product = new Product(rs.getInt("Id"), rs.getString("Name"), rs.getDouble("Cost"), rs.getString("Description"));
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return product;
    }
    
    public static void insertProduct(Product product) {
        try {
            PreparedStatement st = instance.connection.prepareStatement("INSERT INTO public.\"Products\"(\n" +
"	\"Name\", \"Cost\", \"Description\")\n" +
"	VALUES (?, ?, ?); INSERT INTO public.\"ProductStock\"(\n" +
"	\"ProductId\", \"Quantity\")\n" +
"	VALUES ((SELECT MAX(\"Id\") FROM \"Products\"), 0);");
            st.setString(1, product.getName());
            st.setDouble(2, product.getCost());
            st.setString(3, product.getDescription());
            ResultSet rs = st.executeQuery();
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Material> selectMaterials() {
        ArrayList<Material> materials = null;
        
        try {
            materials = new ArrayList<>();
            Statement st = instance.connection.createStatement();
            ResultSet rs = st.executeQuery(instance.queries.get("Materials"));
            
            while (rs.next())
                materials.add(new Material(rs.getInt("Id"), rs.getString("Name"), rs.getDouble("Cost"), rs.getString("Description")));
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return materials;
    }
    
    public static Material selectMaterial(int id) {
        Material material = null;
        
        try {
            PreparedStatement st = instance.connection.prepareStatement(instance.queries.get("Materials") + " WHERE \"Id\" = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            if (rs.next())
                material = new Material(rs.getInt("Id"), rs.getString("Name"), rs.getDouble("Cost"), rs.getString("Description"));
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return material;
    }
    
    public static void insertMaterial(Material material) {
        try {
            PreparedStatement st = instance.connection.prepareStatement("INSERT INTO public.\"Materials\"(\n" +
"	\"Name\", \"Cost\", \"Description\")\n" +
"	VALUES (?, ?, ?); INSERT INTO public.\"MaterialStock\"(\n" +
"	\"MaterialId\", \"Quantity\")\n" +
"	VALUES ((SELECT MAX(\"Id\") FROM \"Materials\"), 0)");
            st.setString(1, material.getName());
            st.setDouble(2, material.getCost());
            st.setString(3, material.getDescription());
            ResultSet rs = st.executeQuery();
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static ArrayList<Provider> selectProviders() {
        ArrayList<Provider> providers = null;
        
        try {
            providers = new ArrayList<>();
            Statement st = instance.connection.createStatement();
            ResultSet rs = st.executeQuery(instance.queries.get("Providers"));
            
            while (rs.next())
                providers.add(new Provider(rs.getInt("Id"), rs.getString("Name"), rs.getString("City"), rs.getString("StreetAddress"), rs.getInt("PhoneNumber"), rs.getString("Email")));
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return providers;
    }
    
    public static Provider selectProvider(int id) {
        Provider provider = null;
        
        try {
            PreparedStatement st = instance.connection.prepareStatement(instance.queries.get("Providers") + " WHERE \"Id\" = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            if (rs.next())
                provider = new Provider(rs.getInt("Id"), rs.getString("Name"), rs.getString("City"), rs.getString("StreetAddress"), rs.getInt("PhoneNumber"), rs.getString("Email"));
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return provider;
    }
    
    public static ArrayList<Purchase> selectPurchases() {
        ArrayList<Purchase> purchases = null;
        
        try {
            purchases = new ArrayList<>();
            Statement st = instance.connection.createStatement();
            ResultSet rs = st.executeQuery(instance.queries.get("Purchases"));
            
            while (rs.next())
                purchases.add(new Purchase(rs.getInt("Id"), rs.getTimestamp("Date"), selectProvider(rs.getInt("ProviderId")), Helpers.getMaterialsFromJson(Helpers.json(rs.getString("Purchased")))));
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return purchases;
    }
    
    public static Purchase selectPurchase(int id) {
        Purchase purchase = null;
        
        try {
            PreparedStatement st = instance.connection.prepareStatement(instance.queries.get("Purchases") + " WHERE \"Id\" = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            if (rs.next())
                purchase = new Purchase(rs.getInt("Id"), rs.getTimestamp("Date"), selectProvider(rs.getInt("ProviderId")), Helpers.getMaterialsFromJson(Helpers.json(rs.getString("Purchased"))));
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return purchase;
    }
    
    public static void insertPurchase(Purchase purchase) {
        try {
            PreparedStatement st = instance.connection.prepareStatement("INSERT INTO public.\"Purchases\"(\n" +
"	\"Total\", \"ProviderId\", \"Purchased\", \"Date\")\n" +
"	VALUES (?, ?, ?::JSON, ?)");
            st.setDouble(1, purchase.getTotal());
            st.setInt(2, purchase.getProvider().getId());
            st.setObject(3, Helpers.getJsonFromMap(purchase.getPurchasedIds()).toString());
            st.setTimestamp(4, Timestamp.from(purchase.getDate().toInstant()));
            ResultSet rs = st.executeQuery();
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Sale> selectSales() {
        ArrayList<Sale> sales = null;
        
        try {
            sales = new ArrayList<>();
            Statement st = instance.connection.createStatement();
            ResultSet rs = st.executeQuery(instance.queries.get("Sales"));
            
            while (rs.next())
                sales.add(new Sale(rs.getInt("Id"), PaymentMethod.get(rs.getInt("PaymentMethodId")), selectClient(rs.getInt("ClientId")), rs.getTimestamp("Date"), Helpers.getProductsFromJson(Helpers.json(rs.getString("Purchased")))));
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return sales;
    }
    
    public static Sale selectSale(int id) {
        Sale sale = null;
        
        try {
            PreparedStatement st = instance.connection.prepareStatement(instance.queries.get("Purchases") + " WHERE \"Id\" = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            if (rs.next())
                sale = new Sale(rs.getInt("Id"), PaymentMethod.get(rs.getInt("PaymentMethodId")), selectClient(rs.getInt("ClientId")), rs.getTimestamp("Date"), Helpers.getProductsFromJson(Helpers.json(rs.getString("Purchased"))));
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return sale;
    }
    
    public static void insertSale(Sale sale) {
        try {
            PreparedStatement st = instance.connection.prepareStatement("INSERT INTO public.\"Sales\"(\n" +
"	\"Total\", \"PaymentMethodId\", \"ClientId\", \"Date\", \"Purchased\")\n" +
"	VALUES (?, ?, ?, ?, ?::JSON)");
            st.setDouble(1, sale.getTotal());
            st.setInt(2, sale.getPaymentMethod().getValue());
            st.setInt(3, sale.getClient().getId());
            st.setTimestamp(4, Timestamp.from(sale.getDate().toInstant()));
            st.setObject(5, Helpers.getJsonFromMap(sale.getPurchasedIds()).toString());
            ResultSet rs = st.executeQuery();
            rs.close();
            
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static HashMap<Purchasable, Integer> selectStock() {
        HashMap<Purchasable, Integer> stock = null;
        
        try {
            stock = new HashMap<>();
            Statement st = instance.connection.createStatement();
            ResultSet rs = st.executeQuery(instance.queries.get("MaterialStock"));
            
            while (rs.next())
                stock.put(selectMaterial(rs.getInt("MaterialId")), rs.getInt("Quantity"));
            
            rs.close();
            st.close();
            
            st = instance.connection.createStatement();
            rs = st.executeQuery(instance.queries.get("ProductStock"));
            
            while (rs.next())
                stock.put(selectProduct(rs.getInt("ProductId")), rs.getInt("Quantity"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return stock;
    }
    
    public static void updateMaterialStock(int id, int quantity) throws SQLException {
        try {
            PreparedStatement st = instance.connection.prepareStatement("UPDATE public.\"MaterialStock\"\n" +
"	SET \"Quantity\"=?\n" +
"	WHERE \"MaterialId\"=?");
            st.setInt(2, id);
            st.setInt(1, quantity);
            ResultSet rs = st.executeQuery();
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public static void updateProductStock(int id, int quantity) throws SQLException {
        try {
            PreparedStatement st = instance.connection.prepareStatement("UPDATE public.\"ProductStock\"\n" +
"	SET \"Quantity\"=?\n" +
"	WHERE \"ProductId\"=?");
            st.setInt(2, id);
            st.setInt(1, quantity);
            ResultSet rs = st.executeQuery();
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public static DatabaseManager getInstance() {
        if (instance == null)
        {
            instance = new DatabaseManager();
            instance.load();
        }
        
        return instance;
    }
}
