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
public class Batiment {
    
    private int idbat;
    private double surface;
    private String nomBat;

    public Batiment(int idbat, double surface, String nomBat) {
        this.idbat = idbat;
        this.surface = surface;
        this.nomBat = nomBat;
    }
    
    

    public int getIdbat() {
        return idbat;
    }

    public void setIdbat(int idbat) {
        this.idbat = idbat;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public String getNomBat() {
        return nomBat;
    }

    public void setNomBat(String nomBat) {
        this.nomBat = nomBat;
    }
    public String toString()
    {
        return this.nomBat;
    }
    
    public boolean equals(Object o)
    {
        if (o==null) return false;
        return ((Batiment)o).getIdbat()==this.idbat;
    }
}