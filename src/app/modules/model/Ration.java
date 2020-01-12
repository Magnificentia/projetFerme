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
public class Ration {
    private int idration;
    private int ali_id;
    private int bande_id;
    private String dateRation;
    private double qte;
    private double eau;
    
    
    private String nomAli;
    private String nomRation;
    private String nomBande;

    public Ration(int idration, int ali_id, int bande_id, String dateRation, double qte, double eau, String nomAli, String nomRation, String nomBande) {
        this.idration = idration;
        this.ali_id = ali_id;
        this.bande_id = bande_id;
        this.dateRation = dateRation;
        this.qte = qte;
        this.eau = eau;
        this.nomAli = nomAli;
        this.nomRation = nomRation;
        this.nomBande = nomBande;
    }

    
    
    public int getIdration() {
        return idration;
    }

    public void setIdration(int idration) {
        this.idration = idration;
    }

    public int getAli_id() {
        return ali_id;
    }

    public void setAli_id(int ali_id) {
        this.ali_id = ali_id;
    }

    public int getBande_id() {
        return bande_id;
    }

    public void setBande_id(int bande_id) {
        this.bande_id = bande_id;
    }

    public String getDateRation() {
        return dateRation;
    }

    public void setDateRation(String dateRation) {
        this.dateRation = dateRation;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public double getEau() {
        return eau;
    }

    public void setEau(double eau) {
        this.eau = eau;
    }

    public String getNomAli() {
        return nomAli;
    }

    public void setNomAli(String nomAli) {
        this.nomAli = nomAli;
    }

    public String getNomRation() {
        return nomRation;
    }

    public void setNomRation(String nomRation) {
        this.nomRation = nomRation;
    }

    public String getNomBande() {
        return nomBande;
    }

    public void setNomBande(String nomBande) {
        this.nomBande = nomBande;
    }
    
    
}
