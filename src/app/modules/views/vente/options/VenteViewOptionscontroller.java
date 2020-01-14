/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.views.aliment.options;

import app.modules.views.bande.options.*;
import app.modules.views.stockAliment.options.*;
import app.Main;
import app.modules.database.DbManagerNnane;
import app.modules.model.Aliment;
import app.modules.model.Bande;
import app.modules.model.Batiment;
import app.modules.model.Fournisseur;
import app.modules.model.Race;
import app.modules.model.StockAliment;
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
    private JFXComboBox<Race> race;
       
           @FXML
    private JFXTextField age;

    @FXML
    private JFXTextField qte;

    @FXML
    private JFXTextField achat;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXComboBox<Fournisseur> fournisseur;

    @FXML
    private JFXComboBox<Batiment> batiment;

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

        if (fournisseur.getSelectionModel().isEmpty()) {
            Main.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Entrer un fournisseur");
            return;
        }
        if (qte.getText().isEmpty()) {
            Main.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "entrer une quantité");
            return;
        }
        if (batiment.getSelectionModel().isEmpty()) {
            Main.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Entrer un fournisseur");
            return;
        }
        // boolean flag=true;
        boolean flag =DbManagerNnane.saveBande(new Bande(new Integer(qte.getText()),new Integer(age.getText()),race.getSelectionModel().getSelectedItem().getIdrace(),new Integer(achat.getText()),date.getValue().toString(),fournisseur.getSelectionModel().getSelectedItem().getIdfourn(),batiment.getSelectionModel().getSelectedItem().getIdbat()));
        if (!flag) {
            Main.infoBox("Please enter correct name or information", null, "Failed");
        } else {
            Main.infoBox("Registered Successful!", null, "SUCCEDED");
        }
    }
    
    public void initialize()
    {

        fournisseur.setItems(FXCollections.observableArrayList(DbManagerNnane.selectFournisseurs()));
        batiment.setItems(FXCollections.observableArrayList(DbManagerNnane.selectBatiments()));
        race.setItems(FXCollections.observableArrayList(DbManagerNnane.selectRaces()));
        //choixtype.setValue(t.get(0));
        
    }
}
