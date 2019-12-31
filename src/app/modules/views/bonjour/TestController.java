package app.modules.views.bonjour;



import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;
import app.modules.model.DAO;
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
public class TestController implements Initializable, IController {
    @FXML
    TableView<Employes> table;

    @FXML
    VBox modify;
    
    @FXML
    private TableColumn<Employes,String> col_nom;

    @FXML
    private TableColumn<Employes,String> col_user;

    @FXML
    private TableColumn<Employes,String> col_password;

    @FXML
    private TableColumn<Employes,String> col_type;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DAO<Employes> dao=new DAO(Employes.class);//grace au DAO statique créé dans le dossier modèle on peut manipuler les tables sans SQL
        ObservableList<Employes> liste=FXCollections.observableArrayList(dao.findAll());
        System.out.println(liste.get(0).getLogin());
        
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_user.setCellValueFactory(new PropertyValueFactory<>("user"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("login"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("typeEm"));
        table.setPrefWidth(800);
        
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
