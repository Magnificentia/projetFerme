/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.views.venteOeuf.options;

import app.Projet;
import app.modules.database.DbManagerNnane;
import app.modules.model.Client;
import app.modules.model.CollecteOeuf;
import app.modules.model.VenteOeuf;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Window;


/**
 *
 * @author _Nprime496_
 */
public class VenteViewOptionscontroller implements Initializable{
    
    

    @FXML
    private JFXComboBox<Client> client;

    @FXML
    private JFXComboBox<CollecteOeuf> collecte;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXTextField qte;

    @FXML
    private JFXTextField prix;

    @FXML
    private JFXButton submitButton;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialize();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @FXML
    public void register(ActionEvent event) throws SQLException {

        Window owner = submitButton.getScene().getWindow();

        if (client.getSelectionModel().isEmpty() || collecte.getSelectionModel().isEmpty()) {
            Projet.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Champ manquant");
            return;
        }
        if (qte.getText().isEmpty()) {
            Projet.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "entrer une quantité");
            return;
        }

        // boolean flag=true;
        boolean flag =DbManagerNnane.saveVenteOeuf(new VenteOeuf(collecte.getSelectionModel().getSelectedItem().getIdCollect(),client.getSelectionModel().getSelectedItem().getIdClient(),date.getValue().toString(),new Integer(prix.getText()),date.getValue().toString(),new Integer(qte.getText())));
        if (!flag) {
            Projet.infoBox("Please enter correct name or information", null, "Failed");
        } else {
            Projet.infoBox("Registered Successful!", null, "SUCCEDED");
        }
    }
    
    public void initialize()
    {

        collecte.setItems(FXCollections.observableArrayList(DbManagerNnane.selectCollecteOeufs()));
        client.setItems(FXCollections.observableArrayList(DbManagerNnane.selectClients()));
        //choixtype.setValue(t.get(0));*/
        
    }
}
