/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.views.bonjour;

/**
 *
 * @author HP
 */
public class Utilisateur {
    
     private String nom;
    private String user;
    private String password;
    private String type;
    
    public Utilisateur(String user, String password, String type, String nom) {
        this.user = user;
        this.password = password;
        this.type = type;
        this.nom = nom;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
   
}
