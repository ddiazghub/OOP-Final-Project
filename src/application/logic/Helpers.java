/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.logic;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author david
 */
public class Helpers {
    public static String formatDate(Date date) {
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault()).toString();
    }
    
    public static Date dateFrom(int year, int month, int day) {
        return Date.from(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    public static int tryParseInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor numérico Entero", "Error", JOptionPane.ERROR_MESSAGE);
            throw new NumberFormatException();
        }
    }
    
    public static double tryParseDouble(String string) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor numérico", "Error", JOptionPane.ERROR_MESSAGE);
            throw new NumberFormatException();
        }
    }
    
    public static ImageIcon readIconFromFile(File file) {
        return new ImageIcon(scalePortraitImage(readImageFromFile(file)));
    }
    
    public static Image readImageFromFile(File file) {
        Image image = null;
        
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return image;
    }
    
    public static Image scalePortraitImage(Image image) {
        double scale = determineImageScale(image.getWidth(null), image.getHeight(null), 330, 360);
        return image.getScaledInstance((int) (image.getWidth(null) * scale), (int) (image.getWidth(null) * scale), Image.SCALE_SMOOTH);
    }
    
    public static double determineImageScale(int sourceWidth, int sourceHeight, int targetWidth, int targetHeight) {
        double scalex = (double) targetWidth / sourceWidth;
        double scaley = (double) targetHeight / sourceHeight;
        return Math.min(scalex, scaley);
    }
    
    public static JSONObject getJsonFromMap(Map<Integer, Integer> map) {
        JSONObject jsonData = new JSONObject();
        try {
            for (Integer key : map.keySet()) {
                jsonData.put(key.toString(), map.get(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return jsonData;
    }
    
    public static HashMap<Product, Integer> getProductsFromJson(JSONObject json) {
        HashMap<Product, Integer> map = new HashMap<>();
        Iterator<String> keys = json.keys();
        
        try {
            while(keys.hasNext()) {
                String key = keys.next();
                map.put(DatabaseManager.selectProduct(Integer.parseInt(key)), (Integer) json.get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return map;
    }
    
    public static HashMap<Material, Integer> getMaterialsFromJson(JSONObject json) {
        HashMap<Material, Integer> map = new HashMap<>();
        Iterator<String> keys = json.keys();
        
        try {
            while(keys.hasNext()) {
                String key = keys.next();
                map.put(DatabaseManager.selectMaterial(Integer.parseInt(key)), (Integer) json.get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return map;
    }
    
    public static JSONObject json(String jsonString) {
        try {
            return new JSONObject(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
