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
public class BandeVaccine {
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    
    private int idvaccine;
    private int bande_id;
    private int vaccin_id;
    private LocalDate dateVac=LocalDate.parse(Bande.DEFAULT_DATE, formatter);;
    
    private String nomBande;
    private String nomvac;
    private String nomVaccination;

    public LocalDate getDateVac() {
        return dateVac;
    }

    public void setDateVac(String dateVac) {
        this.dateVac = LocalDate.parse(dateVac, formatter);;
    }

    public String getNomVaccination() {
        return nomVaccination;
    }

    public void setNomVaccination(String nomVaccination) {
        this.nomVaccination = nomVaccination;
    }

    public BandeVaccine(int idvaccine, int bande_id, int vaccin_id, String datevac, String nomBande, String nomvac, String nomVaccinaton) {
        this.idvaccine = idvaccine;
        this.bande_id = bande_id;
        this.vaccin_id = vaccin_id;
        this.dateVac = LocalDate.parse(datevac, formatter);;
        this.nomBande = nomBande;
        this.nomvac = nomvac;
        this.nomVaccination = nomVaccinaton;
    }

    
    public int getIdvaccine() {
        return idvaccine;
    }

    public void setIdvaccine(int idvaccine) {
        this.idvaccine = idvaccine;
    }

    public int getBande_id() {
        return bande_id;
    }

    public void setBande_id(int bande_id) {
        this.bande_id = bande_id;
    }

    public int getVaccin_id() {
        return vaccin_id;
    }

    public void setVaccin_id(int vaccin_id) {
        this.vaccin_id = vaccin_id;
    }

    public LocalDate getDatevac() {
        return dateVac;
    }

    public void setDatevac(String datevac) {
        this.dateVac =LocalDate.parse(datevac, formatter);;
    }

    public String getNomBande() {
        return nomBande;
    }

    public void setNomBande(String nomBande) {
        this.nomBande = nomBande;
    }

    public String getNomvac() {
        return nomvac;
    }

    public void setNomvac(String nomvac) {
        this.nomvac = nomvac;
    }

    public String getNomVaccinaton() {
        return nomVaccination;
    }

    public void setNomVaccinaton(String nomVaccinaton) {
        this.nomVaccination = nomVaccinaton;
    }
    
    
    
}
