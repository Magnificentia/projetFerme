package app.modules.views.commande;



import app.Projet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;
import app.modules.database.DbManager;
import app.modules.model.Client;

import app.modules.userType;
import app.modules.views.BaseView;
import java.io.IOException;

import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//putain
public class CommandeViewController extends BaseView<Client> implements Initializable, IController {
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        populateTableVenteOeuf();
        table.setPrefWidth(800);
    }
    
    public void loadData()
    {
        data=FXCollections.observableArrayList(DbManager.selectClients());
    }
    
    public void createTable()
    {
        table.setPrefWidth(800);
        
        /*TableColumn<Bande,String> col_nom=new TableColumn<>("nom");
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomBande"));
        
        TableColumn<Bande,String> col_achat=new TableColumn<>("prix d'achat");
        col_achat.setCellValueFactory(new PropertyValueFactory<>("prix_achat"));
        
        TableColumn<Bande,Integer> col_age=new TableColumn<>("age");
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        
        TableColumn<Bande,String> col_date=new TableColumn<>("date");
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateDemarrage"));
        
        TableColumn<Bande,Integer> col_quantite=new TableColumn<>("quantité");
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
        
        TableColumn<Bande,String> col_batiment=new TableColumn<>("batiment");
        col_batiment.setCellValueFactory(new PropertyValueFactory<>("nomBatiment"));
        
        TableColumn<Bande,String> col_fournisseur=new TableColumn<>("fournisseur");
        col_fournisseur.setCellValueFactory(new PropertyValueFactory<>("nomFournisseur"));
        
        TableColumn<Bande,String> col_race=new TableColumn<>("race"); 
        col_race.setCellValueFactory(new PropertyValueFactory<>("nomRace"));
        
        table.getColumns().addAll(col_nom,col_achat,col_age,col_date,col_quantite,col_batiment,col_fournisseur,col_race);*/
    }

    public void populateTableVenteOeuf()
    {
        //ObservableList<VenteOeuf> liste=FXCollections.observableArrayList(DbManagerNnane.selectVenteOeuf());
        //table.setItems(liste);
    }
    
    
    @Override
    public Map<Node,List<userType>> getNodeRoles() {
        Map nodeRoles=new HashMap<Node,List<userType>>();
        table.setEditable(false);
        List<userType> liste=new ArrayList<>();
        //liste.add(userType._ADMIN_);
        liste.add(userType._SELLER_);
        nodeRoles.put((Node)table, liste);
        System.err.println(nodeRoles.get(table));
        System.err.println(nodeRoles);
        System.err.println(nodeRoles.keySet());
        return nodeRoles;
    }
    
    
    @FXML
    public void showAddVenteOeufWindow(ActionEvent event) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("options/venteOeufOptionsView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            //dialogStage.getIcons().add(new Image("file:resources/images/icon2.jpg"));
            dialogStage.setTitle("Ajouter une nouvelle vente");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(Projet.getMainStage());
            Scene scene = new Scene(page,600,500);
            dialogStage.setScene(scene);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            populateTableVenteOeuf();

            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
