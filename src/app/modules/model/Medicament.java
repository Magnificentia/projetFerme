/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.model;

import java.time.Period;

/**
 *
 * @author _Nprime496_
 */
public class Medicament {
    private int idMed;
    private String nomVac;
    private Period periode;
    private int qte;
    private int qtePoule;
    private String description;
    private double prix;

    
    public Medicament( int idvac,String nomVac, String date, String description, double prix) {
        this.idMed=idvac;
        this.nomVac = nomVac;
        this.periode = Period.parse("P"+date+"M");
        this.description = description;
        this.prix = prix;
    }
    
    public Medicament( String nomVac, String date, String description, double prix) {
        this.nomVac = nomVac;
        this.periode = Period.parse("P"+date+"M");
        this.description = description;
        this.prix = prix;
    }

    public Period getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = Period.parse("P"+periode+"M");
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
