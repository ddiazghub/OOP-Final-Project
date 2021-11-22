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
import javax.swing.ImageIcon;

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
}
