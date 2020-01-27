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
public class Race {
    private int idrace;
    private String nom;
    private String description;
    private double prix_race;

    public Race(int idrace, String nom, String description) {
        this.idrace = idrace;
        this.nom = nom;
        this.description = description;

    }
    
    

    public int getIdrace() {
        return idrace;
    }

    public void setIdrace(int idrace) {
        this.idrace = idrace;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix_race() {
        return prix_race;
    }

    public void setPrix_race(double prix_race) {
        this.prix_race = prix_race;
    }
    
    public String toString()
    {
        return this.nom;
    }
    
    public boolean equals(Object o)
    {
        if (o==null) return false;
        return ((Race)o).getIdrace()==this.idrace;
    }
    
}
