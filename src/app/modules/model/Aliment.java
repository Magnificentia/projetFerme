/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.model;

/**
 *
 * @author _Nprime496_
 */
public class Aliment {
    private int idali;
    private String nomAli;
    private String description;
    private double prix;

    public Aliment(int idali, String nomAli, String description, double prix) {
        this.idali = idali;
        this.nomAli = nomAli;
        this.description = description;
        this.prix = prix;
    }

    public Aliment(int ali_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    public int getIdali() {
        return idali;
    }

    public void setIdali(int idali) {
        this.idali = idali;
    }

    public String getNomAli() {
        return nomAli;
    }

    public void setNomAli(String nomAli) {
        this.nomAli = nomAli;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    public String toString()
    {
        return this.nomAli;
    }
    
    
}
