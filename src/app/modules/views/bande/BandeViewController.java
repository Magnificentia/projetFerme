package app.modules.views.bande;



import app.Projet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;
import app.modules.database.DbManagerNnane;
import app.modules.model.Bande;

import app.modules.model.StockAliment;

import app.modules.userType;
//import app.modules.views.bonjour.Formulaire;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


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

    //Popup
   
    private ImageView image;
    private VBox paneElements;
    private Boolean Confirmation;
    private JFXButton btn1;
    private JFXButton btn2;
    private JFXTextField textQuantite;
    private JFXTextField textAge;        
    private JFXComboBox<String> comboRace ;        
    private JFXComboBox<String> comboBatiment;
    private  JFXComboBox<String> comboFournisseur; 
    
    
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
        table.getItems().clear();//importaant sinon les changements ne vont pas être éffectués lorsqu'on fait de simples modificatioobs
        ObservableList<Bande> liste=FXCollections.observableArrayList(DbManagerNnane.selectBandes());
        table.setItems(liste);
    }

    @FXML
    public void handleDelete(ActionEvent event) throws SQLException {

        Bande mat=table.getSelectionModel().getSelectedItem();

        if (mat!=null) {
            if(Projet.showAlert(Alert.AlertType.CONFIRMATION, null, "Form Error!",
                "voulez-vous supprimer cet utilisateur?"))
            {
                System.out.println("suppression");
                DbManagerNnane.suppBande(mat);
                populateTableBande();
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
