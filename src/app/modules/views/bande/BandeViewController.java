package app.modules.views.bande;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;
import app.modules.database.DbManagerNnane;
import app.modules.model.Bande;

import app.modules.userType;

import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


//putain
public class BandeViewController implements Initializable, IController {

    
    @FXML
    private TableView<Bande> table;

    @FXML
    private TableColumn<?, ?> col_nom;

    @FXML
    private TableColumn<?, ?> col_quantite;

    @FXML
    private TableColumn<?, ?> col_age;

    @FXML
    private TableColumn<?, ?> col_race;

    @FXML
    private TableColumn<?, ?> col_date;

    @FXML
    private TableColumn<?, ?> col_achat;

    @FXML
    private TableColumn<?, ?> col_fournisseur;

    @FXML
    private TableColumn<?, ?> col_batiment;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomBande"));
        col_achat.setCellValueFactory(new PropertyValueFactory<>("prix_achat"));
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateDemarrage"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
        col_batiment.setCellValueFactory(new PropertyValueFactory<>("nomBatiment"));
        col_fournisseur.setCellValueFactory(new PropertyValueFactory<>("nomFournisseur"));
        col_race.setCellValueFactory(new PropertyValueFactory<>("nomRace"));
        populateTableBande();
        table.setPrefWidth(800);
    }

    public void populateTableBande()
    {
        ObservableList<Bande> liste=FXCollections.observableArrayList(DbManagerNnane.selectBandes());
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
}
