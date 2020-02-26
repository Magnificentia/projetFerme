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
public class VenteBande extends Vente{

    private int bande_id;

    public VenteBande(int bande_id, int idVente, int client_id, String dateVente, double total_prix, int qte, int employe_id, String nomEmploye, String nomClient) {
        super(idVente, client_id, dateVente, total_prix, qte, employe_id, nomEmploye, nomClient);
        this.bande_id = bande_id;
    }
    
    public VenteBande(int bande_id, int client_id, String dateVente, double total_prix, int qte, int employe_id, String nomEmploye, String nomClient) {
        super(client_id, dateVente, total_prix, qte, employe_id, nomEmploye, nomClient);
        this.bande_id = bande_id;
    }
    
    public VenteBande(int bande_id, int client_id, String dateVente, double total_prix, int qte, int employe_id) {
        super(client_id, dateVente, total_prix, qte, employe_id);
        this.bande_id = bande_id;
    }


    public int getBande_id() {
        return bande_id;
    }

    public void setBande_id(int bande_id) {
        this.bande_id = bande_id;
    }

}
