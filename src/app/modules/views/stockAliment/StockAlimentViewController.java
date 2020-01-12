package app.modules.views.stockAliment;



import app.Main;
import app.modules.views.ration.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;
import app.modules.database.DbManagerNnane;
import app.modules.model.Aliment;
import app.modules.model.Ration;
import app.modules.model.StockAliment;

import app.modules.userType;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//putain
public class StockAlimentViewController implements Initializable, IController {

    
    @FXML
    private TableView<StockAliment> table;

    @FXML
    private TableColumn<?, ?> col_nom;

    @FXML
    private TableColumn<?, ?> col_qte;

    @FXML
    private TableColumn<?, ?> col_date;

    @FXML
    private TableColumn<?, ?> col_aliment;

    @FXML
    private TableColumn<?, ?> col_fournisseur;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomStock"));
        col_aliment.setCellValueFactory(new PropertyValueFactory<>("nomAli"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateArrivage"));
        col_fournisseur.setCellValueFactory(new PropertyValueFactory<>("nomFournisseur"));
        col_qte.setCellValueFactory(new PropertyValueFactory<>("qte"));


        populateTableRation();
        table.setPrefWidth(800);
    }
    
    public void populateTableRation()
    {
        ObservableList<StockAliment> liste=FXCollections.observableArrayList(DbManagerNnane.selectStockAliment());
        table.setItems(liste);
    }
    
    @FXML
    public void handleDelete(ActionEvent event) throws SQLException {

        StockAliment mat=table.getSelectionModel().getSelectedItem();

        if (mat!=null) {
            if(Main.showAlert(Alert.AlertType.CONFIRMATION, null, "Form Error!",
                "voulez-vous supprimer cet utilisateur?"))
            {
                System.out.println("suppression");
                DbManagerNnane.suppStockAliment(mat);
                populateTableRation();
            }
            return;
        }
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
