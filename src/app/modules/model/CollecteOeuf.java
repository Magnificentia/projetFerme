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
public class CollecteOeuf {
    private int idCollect;
    private int qte;
    private String dateCollect;
    private int incubation;
    private int bande_id;
    private double prix_alveole;
    private int qteCasse;
    private int typeOeuf;
    
    private String nomBande;
    private String nomTypeOeuf;

    public CollecteOeuf(int idCollect, int qte, String dateCollect, int incubation, int bande_id, double prix_alveole, int qteCasse, int typeOeuf, String nomBande, String nomTypeOeuf) {
        this.idCollect = idCollect;
        this.qte = qte;
        this.dateCollect = dateCollect;
        this.incubation = incubation;
        this.bande_id = bande_id;
        this.prix_alveole = prix_alveole;
        this.qteCasse = qteCasse;
        this.typeOeuf = typeOeuf;
        this.nomBande = nomBande;
        this.nomTypeOeuf = nomTypeOeuf;
    }
    
    

    public int getIdCollect() {
        return idCollect;
    }

    public void setIdCollect(int idCollect) {
        this.idCollect = idCollect;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getDateCollect() {
        return dateCollect;
    }

    public void setDateCollect(String dateCollect) {
        this.dateCollect = dateCollect;
    }

    public int getIncubation() {
        return incubation;
    }

    public void setIncubation(int incubation) {
        this.incubation = incubation;
    }

    public int getBande_id() {
        return bande_id;
    }

    public void setBande_id(int bande_id) {
        this.bande_id = bande_id;
    }

    public double getPrix_alveole() {
        return prix_alveole;
    }

    public void setPrix_alveole(double prix_alveole) {
        this.prix_alveole = prix_alveole;
    }

    public int getQteCasse() {
        return qteCasse;
    }

    public void setQteCasse(int qteCasse) {
        this.qteCasse = qteCasse;
    }

    public int getTypeOeuf() {
        return typeOeuf;
    }

    public void setTypeOeuf(int typeOeuf) {
        this.typeOeuf = typeOeuf;
    }

    public String getNomBande() {
        return nomBande;
    }

    public void setNomBande(String nomBande) {
        this.nomBande = nomBande;
    }

    public String getNomTypeOeuf() {
        return nomTypeOeuf;
    }

    public void setNomTypeOeuf(String nomTypeOeuf) {
        this.nomTypeOeuf = nomTypeOeuf;
    }
    
    
}