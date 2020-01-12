package app.modules.views.client;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;
import app.modules.database.DbManagerNnane;
import app.modules.model.Client;
import app.modules.model.CollecteOeuf;

import app.modules.userType;
import app.modules.model.Popup;
import com.jfoenix.controls.JFXButton;


import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//putain
public class ClientViewController implements Initializable, IController {
   
        @FXML
    private TableView<Client> table;

    @FXML
    private TableColumn<?, ?> col_nom;

    @FXML
    private TableColumn<?, ?> col_adresse;

    @FXML
    private TableColumn<?, ?> col_tel;

    @FXML
    private JFXButton buttonSupprimer;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        
        populateTableClient();
        table.setPrefWidth(800);
    }

    
    public void populateTableClient()
    {
        ObservableList<Client> liste=FXCollections.observableArrayList(DbManagerNnane.selectClients());
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
    void ButtonSupprimerOnClick(ActionEvent event) {
       boolean bool= Popup.show("Veuillez entrez les informations", "Editer",null);
    }
    
    
}
