package app.modules.views.vente.enregistrerVente;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;
import app.modules.database.DbManagerNnane;
import app.modules.model.Vaccin;
import app.modules.model.VenteOeuf;

import app.modules.userType;

import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//putain
public class VenteViewController implements Initializable, IController {

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

        
        /*col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
        //populateTableVaccin();
        table.setPrefWidth(800);*/
    }

    /*public void populateTableVaccin()
    {
        ObservableList<Vaccin> liste=FXCollections.observableArrayList(DbManagerNnane.selectVaccins());
        table.setItems(liste);
    }*/

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
}
