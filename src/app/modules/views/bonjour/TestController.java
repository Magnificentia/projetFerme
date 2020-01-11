package app.modules.views.bonjour;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;

import app.modules.userType;
import app.modules.views.Popup;
import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//putain
public class TestController implements Initializable, IController {
    @FXML
    TableView<?> table;

    @FXML
    VBox modify;
    
    @FXML
    private TableColumn<?,?> col_nom;

    @FXML
    private TableColumn<?,?> col_user;

    @FXML
    private TableColumn<?,?> col_password;

    @FXML
    private TableColumn<?,?> col_type;
    
    @FXML
    private JFXButton buttonSupprimer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_user.setCellValueFactory(new PropertyValueFactory<>("user"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("login"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("typeEm"));
        table.setPrefWidth(800);
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
       boolean bool= Popup.show("Veuillez entrez les informations", "Editer");
    }
    
    
}
