/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;

/**
 *
 * @author aashgar
 */
public class Singleton {
   private static Singleton instance = null;
   private String data;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if(instance == null)
            instance = new Singleton();
        return instance;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
   
   
}
