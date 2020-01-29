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
public class Medicament {
    private int idMed;
    private String nomVac;
    private String periode;
    private int qte;
    private int qtePoule;
    private String description;
    private double prix;

    
    public Medicament(int idVac, String nomVac, String date, String description, double prix) {
        this.idMed = idVac;
        this.nomVac = nomVac;
        this.periode = date;
        this.description = description;
        this.prix = prix;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }
    

    
    public int getIdVac() {
        return idMed;
    }

    public void setIdVac(int idVac) {
        this.idMed = idVac;
    }

    public String getNomVac() {
        return nomVac;
    }

    public void setNomVac(String nomVac) {
        this.nomVac = nomVac;
    }

    public String getDate() {
        return periode;
    }

    public void setDate(String date) {
        this.periode = date;
    }

    public int getQteVac() {
        return qte;
    }

    public void setQteVac(int qteVac) {
        this.qte = qteVac;
    }

    public int getQtePoule() {
        return qtePoule;
    }

    public void setQtePoule(int qtePoule) {
        this.qtePoule = qtePoule;
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
    
    
    
    
    
}
