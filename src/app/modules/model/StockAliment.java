/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.model;

import app.modules.database.DbManagerNnane;
import app.modules.views.bonjour.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * @author _Nprime496_
 */
public class StockAliment {
    private ObservableList<StockAliment> data;
    
    private int idStock;
    private int qte;
    private String dateArrivage;
    private int ali_id;
    private int employe_id;
    private int fourn_id;
    
    
    private String nomStock;
    private String nomAli;
    private String nomFournisseur;


    public int getEmploye_id() {
        return employe_id;
    }

    public void setEmploye_id(int employe_id) {
        this.employe_id = employe_id;
    }

    public String getNomAli() {
        return nomAli;
    }

    public void setNomAli(String nomAli) {
        this.nomAli = nomAli;
    }
    
    

    public StockAliment(int idStock, int qte, String dateArrivage, int ali_id, int emploeye_id, int fourn_id, String nomStock, String nomAliment, String nomFournisseur) {
        this.idStock = idStock;
        this.qte = qte;
        this.dateArrivage = dateArrivage;
        this.ali_id = ali_id;
        this.employe_id = emploeye_id;
        this.fourn_id = fourn_id;
        this.nomStock = nomStock;
        this.nomAli = nomAliment;
        this.nomFournisseur = nomFournisseur;
    }
    
    

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getDateArrivage() {
        return dateArrivage;
    }

    public void setDateArrivage(String dateArrivage) {
        this.dateArrivage = dateArrivage;
    }

    public int getAli_id() {
        return ali_id;
    }

    public void setAli_id(int ali_id) {
        this.ali_id = ali_id;
    }

    public int getEmploeye_id() {
        return employe_id;
    }

    public void setEmploeye_id(int emploeye_id) {
        this.employe_id = emploeye_id;
    }

    public int getFourn_id() {
        return fourn_id;
    }

    public void setFourn_id(int fourn_id) {
        this.fourn_id = fourn_id;
    }

    public String getNomStock() {
        return nomStock;
    }

    public void setNomStock(String nomStock) {
        this.nomStock = nomStock;
    }

    public String getNomAliment() {
        return nomAli;
    }

    public void setNomAliment(String nomAliment) {
        this.nomAli = nomAliment;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }
    

    
}
