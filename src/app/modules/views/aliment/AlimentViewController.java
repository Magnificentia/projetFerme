package app.modules.views.aliment;



import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;
import app.modules.database.DbManagerNnane;
import app.modules.model.Aliment;
import app.modules.model.Bande;
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
public class AlimentViewController implements Initializable, IController {
    @FXML
    private TableView<Aliment> table;

    @FXML
    private TableColumn<?, ?> col_nom;

    @FXML
    private TableColumn<?, ?> col_prix;

    @FXML
    private TableColumn<?, ?> col_unite;

    @FXML
    private TableColumn<?, ?> col_description;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomAli"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        populateTableAliment();
        table.setPrefWidth(800);
    }

    public void populateTableAliment()
    {
        ObservableList<Aliment> liste=FXCollections.observableArrayList(DbManagerNnane.selectAliments());
        table.setItems(liste);
    }

    @FXML
    public void handleDelete(ActionEvent event) throws SQLException {

        Aliment mat=table.getSelectionModel().getSelectedItem();

        if (mat!=null) {
            if(Main.showAlert(Alert.AlertType.CONFIRMATION, null, "Form Error!",
                "voulez-vous supprimer cet aliment ?"))
            {
                DbManagerNnane.suppAliment(mat);
                populateTableAliment();
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
