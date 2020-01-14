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
public class Client {
    private int idClient;
    private String adresse;
    private int tel;
    private String nomClient;

    public Client(int idClient, String adresse, int tel, String nomClient) {
        this.idClient = idClient;
        this.adresse = adresse;
        this.tel = tel;
        this.nomClient = nomClient;
    }
    
    

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
    
    public String toString()
    {
        return this.nomClient;
    }
    
    
}
