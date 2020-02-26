/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author _Nprime496_
 */
public class Bande {
    private static String denomination="BANDE";
    
    public static String DEFAULT_DATE="2020-01-01 00:00:00";
    //attributs de la table
    private int idBande;
    private int qte;
    private int age;
    private int race_id;
    private double prix_achat;
    private double prix_vente;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private LocalDate dateDemarrage=LocalDate.parse(Bande.DEFAULT_DATE, formatter);
    private int fourn_id;
    private int bat_id;
    
    //attributs compl�mentaires n�cessaires � la vue
    private String nomBande;
    private String nomRace;
    private String nomFournisseur;
    private String nomBatiment;

    
    public Bande(int idBande) {
        this.idBande = idBande;
    }
    
    public Bande(String nomBande)
    {
        this.nomBande=nomBande;
    }

    public Bande() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getBat_id() {
        return bat_id;
    }

    public void setBat_id(int bat_id) {
        this.bat_id = bat_id;
    }
    
    public String toString()
    {
      return this.nomBande;
    
    }
            
    public static String getNameFromId(int id)
    {
        return denomination+"-"+String.format("%05d",id);
    }
            

    public Bande(String nomBande,int idBande, int qte, int age, int race_id, double prix_achat, String dateDemarrage, int fourn_id, String nomRace, String nomFournisseur, String nomBatiment,int batid) {
        this.nomBande=getNameFromId(idBande);
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
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate d= LocalDate.parse(dateDemarrage, formatter);
        this.dateDemarrage=d;
    }

    public Bande(int qte, int age, int race_id, double prix_achat, String dateDemarrage, int fourn_id, int bat_id) {

        this.qte = qte;
        this.age = age;
        this.race_id = race_id;
        this.prix_achat = prix_achat;
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate d= LocalDate.parse(dateDemarrage, formatter);
        this.dateDemarrage = d;
        this.fourn_id = fourn_id;
        this.bat_id = bat_id;
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



    public LocalDate getDateDemarrage() {
        return dateDemarrage;
    }

    public void setDateDemarrage(String dateDemarrage) {
        this.dateDemarrage = LocalDate.parse(dateDemarrage, formatter);
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

    public double getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(double prix_vente) {
        this.prix_vente = prix_vente;
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