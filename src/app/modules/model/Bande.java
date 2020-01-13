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
public class Bande {
    
    //attributs de la table
    private int idBande;
    private int qte;
    private int age;
    private int race_id;
    private double prix_achat;
    private String dateDemarrage;
    private int fourn_id;
    private int bat_id;
    
    //attributs complémentaires nécessaires à la vue
    private String nomBande;
    private String nomRace;
    private String nomFournisseur;
    private String nomBatiment;

    public Bande(int idBande) {
        this.idBande = idBande;
    }
    
    

    public Bande(String nomBande,int idBande, int qte, int age, int race_id, double prix_achat, String dateDemarrage, int fourn_id, String nomRace, String nomFournisseur, String nomBatiment,int batid) {
        this.nomBande=nomBande;
        this.idBande = idBande;
        this.qte = qte;
        this.age = age;
        this.race_id = race_id;
        this.prix_achat = prix_achat;

        this.fourn_id = fourn_id;
        this.nomRace = nomRace;
        this.nomFournisseur = nomFournisseur;
        this.nomBatiment = nomBatiment;
        this.bat_id=batid;
    }

    public String getNomBande() {
        return nomBande;
    }

    public void setNomBande(String nomBande) {
        this.nomBande = nomBande;
    }

    
    
    public int getIdBande() {
        return idBande;
    }

    public void setIdBande(int idBande) {
        this.idBande = idBande;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRace_id() {
        return race_id;
    }

    public void setRace_id(int race_id) {
        this.race_id = race_id;
    }

    public double getPrix_achat() {
        return prix_achat;
    }

    public void setPrix_achat(double prix_achat) {
        this.prix_achat = prix_achat;
    }



    public String getDateDemarrage() {
        return dateDemarrage;
    }

    public void setDateDemarrage(String dateDemarrage) {
        this.dateDemarrage = dateDemarrage;
    }

    public int getFourn_id() {
        return fourn_id;
    }

    public void setFourn_id(int fourn_id) {
        this.fourn_id = fourn_id;
    }

    public String getNomRace() {
        return nomRace;
    }

    public void setNomRace(String nomRace) {
        this.nomRace = nomRace;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public String getNomBatiment() {
        return nomBatiment;
    }

    public void setNomBatiment(String nomBatiment) {
        this.nomBatiment = nomBatiment;
    }
    
    
    
}
