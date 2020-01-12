package app.modules.views.collecteoeuf;



import app.modules.views.employes.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;
import app.modules.database.DbManagerNnane;
import app.modules.model.CollecteOeuf;
import app.modules.model.Fournisseur;

import app.modules.userType;

import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//putain
public class CollecteOeufViewController implements Initializable, IController {
        @FXML
    private TableView<CollecteOeuf> table;


    @FXML
    private TableColumn<?, ?> col_bande;

    @FXML
    private TableColumn<?, ?> col_typeoeuf;

    @FXML
    private TableColumn<?, ?> col_quantite;

    @FXML
    private TableColumn<?, ?> col_incubation;

    @FXML
    private TableColumn<?, ?> col_prix;

    @FXML
    private TableColumn<?, ?> col_quantitecasse;
    
        @FXML
    private TableColumn<?, ?> col_date;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        

        col_bande.setCellValueFactory(new PropertyValueFactory<>("nomBande"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix_alveole"));
        col_incubation.setCellValueFactory(new PropertyValueFactory<>("incubation"));
        col_quantitecasse.setCellValueFactory(new PropertyValueFactory<>("qteCasse"));
        col_typeoeuf.setCellValueFactory(new PropertyValueFactory<>("nomTypeOeuf"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateCollect"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
        populateTableCollecteOeuf();
        table.setPrefWidth(800);
    }

    public void populateTableCollecteOeuf()
    {
        ObservableList<CollecteOeuf> liste=FXCollections.observableArrayList(DbManagerNnane.selectCollecteOeufs());
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
