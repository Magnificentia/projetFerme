package app.modules.views.venteOeuf;



import app.Projet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;
import app.modules.database.DbManagerNnane;
import app.modules.model.Vaccin;
import app.modules.model.VenteOeuf;

import app.modules.userType;
import java.io.IOException;

import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//putain
public class VenteOeufViewController implements Initializable, IController {
    
    @FXML
    private TableView<VenteOeuf> table;

    @FXML
    private TableColumn<?, ?> col_client;

    @FXML
    private TableColumn<?, ?> col_bande;

    @FXML
    private TableColumn<?, ?> col_date;

    @FXML
    private TableColumn<?, ?> col_quantite;

    @FXML
    private TableColumn<?, ?> col_prix;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        col_bande.setCellValueFactory(new PropertyValueFactory<>("bande"));
        col_client.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateVente"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("total_prix"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
        populateTableVenteOeuf();
        table.setPrefWidth(800);
    }

    public void populateTableVenteOeuf()
    {
        ObservableList<VenteOeuf> liste=FXCollections.observableArrayList(DbManagerNnane.selectVenteOeuf());
        table.setItems(liste);
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
