package app.modules.views.vaccin;



import app.modules.views.aliment.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;
import app.modules.database.DbManagerNnane;
import app.modules.model.Aliment;
import app.modules.model.Bande;
import app.modules.model.Vaccin;

import app.modules.userType;

import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//putain
public class VaccinViewController implements Initializable, IController {
    @FXML
    private TableView<Vaccin> table;

    @FXML
    private TableColumn<?, ?> col_nom;

    @FXML
    private TableColumn<?, ?> col_periode;

    @FXML
    private TableColumn<?, ?> col_description;

    @FXML
    private TableColumn<?, ?> col_prix;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomVac"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
        populateTableVaccin();
        table.setPrefWidth(800);
    }

    public void populateTableVaccin()
    {
        ObservableList<Vaccin> liste=FXCollections.observableArrayList(DbManagerNnane.selectVaccins());
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
