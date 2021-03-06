/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.logic;

import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

/**
 *
 * @author david
 */
public class MySecurityManager {
    private static MySecurityManager instance;
    
    private SecretKeyFactory factory;
    
    private MySecurityManager() {
        try {
            this.factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
        }
    }
    
    public byte[] hash(char[] password) {
        KeySpec spec = new PBEKeySpec(password, DatabaseManager.getSalt(), 65536, 128);
        byte[] hash = {};
        
        try {
            hash = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        
        return hash;
    }
    
    public byte[] hash1(char[] password) {
        KeySpec spec = new PBEKeySpec(password, new byte[] {'0'}, 65536, 128);
        byte[] hash = {};
        
        try {
            hash = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        
        return hash;
    }
    
    public Boolean comparePasswords(char[] password, char[] confirmation) {
        return Arrays.equals(password, confirmation);
    }
    
    public Boolean verify(char[] password, byte[] hash) {
        return Arrays.equals(this.hash(password), hash);
    }
    
    public static MySecurityManager getInstance() {
        if (instance == null) {
            instance = new MySecurityManager();
        }
        
        return instance;
    }
}
