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
public class Vente {
    
    private int idVente;
    private int client_id;

    private String dateVente;
    private double total_prix;
    private int qte;
    private int employe_id;

    
    private String nomEmploye;
    private String nomClient;

    public Vente(int idVente, int client_id, String dateVente, double total_prix, int qte, int employe_id, String nomEmploye, String nomClient) {
        this.idVente = idVente;
        this.client_id = client_id;
        this.dateVente = dateVente;
        this.total_prix = total_prix;
        this.qte = qte;
        this.employe_id = employe_id;
        this.nomEmploye = nomEmploye;
        this.nomClient = nomClient;
    }
    
    

    public String getNomEmploye() {
        return nomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
    
    


    

    public int getIdVente() {
        return idVente;
    }

    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }


    public String getDateVente() {
        return dateVente;
    }

    public void setDateVente(String dateVente) {
        this.dateVente = dateVente;
    }

    public double getTotal_prix() {
        return total_prix;
    }

    public void setTotal_prix(double total_prix) {
        this.total_prix = total_prix;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getEmploye_id() {
        return employe_id;
    }

    public void setEmploye_id(int employe_id) {
        this.employe_id = employe_id;
    }
    
    
    
    
    
}
