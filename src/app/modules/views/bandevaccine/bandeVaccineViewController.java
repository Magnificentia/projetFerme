package app.modules.views.bandevaccine;



import app.modules.views.bonjour.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;
import app.modules.database.DbManagerNnane;
import app.modules.model.Aliment;
import app.modules.model.BandeVaccine;

import app.modules.userType;

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
public class bandeVaccineViewController implements Initializable, IController {
        @FXML
    private TableView<BandeVaccine> table;

    @FXML
    private TableColumn<?, ?> col_nom;

    @FXML
    private TableColumn<?, ?> col_vaccin;

    @FXML
    private TableColumn<?, ?> col_bande;

    @FXML
    private TableColumn<?, ?> col_date;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomVaccination"));
        col_bande.setCellValueFactory(new PropertyValueFactory<>("nomBande"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("datevac"));
        col_vaccin.setCellValueFactory(new PropertyValueFactory<>("nomvac"));
        populateTableBandeVaccine();
        table.setPrefWidth(800);
    }


    public void populateTableBandeVaccine()
    {
        System.out.println("LISSSSSSSSSSSSSSSSTE");
        ObservableList<BandeVaccine> liste=FXCollections.observableArrayList(DbManagerNnane.selectBandeVaccine());
        System.out.println(liste.get(0).getNomVaccinaton());
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
