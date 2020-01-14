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
public class VenteOeuf extends Vente{
    private int collecte_id;
    private String date;

    public VenteOeuf(int collecte_id, int idVente, int client_id, String dateVente, double total_prix, int qte, int employe_id, String nomEmploye, String nomClient) {
        super(idVente, client_id, dateVente, total_prix, qte, employe_id, nomEmploye, nomClient);
        this.collecte_id = collecte_id;
    }
    
    public VenteOeuf(int collecte_id, int client_id, String dateVente, double total_prix,String date, int qte) {  
        super(0, client_id, dateVente, total_prix, qte, 0, "","");
        this.date=date; 
        this.collecte_id = collecte_id;
    }



    public int getCollecte_id() {
        return collecte_id;
    }

    public void setCollecte_id(int collecte_id) {
        this.collecte_id = collecte_id;
    }
    
    
}
