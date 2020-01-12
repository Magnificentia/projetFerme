package app.modules.views.employes;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;
import app.modules.database.DbManagerNnane;
import app.modules.model.CollecteOeuf;
import app.modules.model.Employes;

import app.modules.userType;

import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//putain
public class EmployesViewController implements Initializable, IController {

    @FXML
    private TableView<Employes> table;

    @FXML
    private TableColumn<?, ?> col_nom;

    @FXML
    private TableColumn<?, ?> col_user;

    @FXML
    private TableColumn<?, ?> col_password;

    @FXML
    private TableColumn<?, ?> col_type;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_user.setCellValueFactory(new PropertyValueFactory<>("user"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("login"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("typeEm"));
        populateTableEmployes();
        table.setPrefWidth(800);
    }

    public void populateTableEmployes()
    {
        ObservableList<Employes> liste=FXCollections.observableArrayList(DbManagerNnane.selectEmployes());
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
