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
public class Fournisseur {
   
    
    private int idfourn;
    private String nomFourn;
    private String adresse;
    private int tel;
    private String email="non spécifié";
    private String siteweb="aucun";
    private int typeFourn;

    public Fournisseur(int idfourn, String nomFourn, String adresse, int tel, String email, String siteweb, int typeFourn) {
        this.idfourn = idfourn;
        this.nomFourn = nomFourn;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.siteweb = siteweb;
        this.typeFourn = typeFourn;
    }

    
    
    public int getIdfourn() {
        return idfourn;
    }

    public void setIdfourn(int idfourn) {
        this.idfourn = idfourn;
    }

    public String getNomFourn() {
        return nomFourn;
    }

    public void setNomFourn(String nomFourn) {
        this.nomFourn = nomFourn;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    public int getTypeFourn() {
        return typeFourn;
    }

    public void setTypeFourn(int typeFourn) {
        this.typeFourn = typeFourn;
    }
    
    public String toString()
    {
        return this.nomFourn;
    }
    
    public boolean equals(Object o)
    {
        if (o==null) return false;
        return (((Fournisseur)o).getIdfourn()==this.idfourn);
    }
    
}
