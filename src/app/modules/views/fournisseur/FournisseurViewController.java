package app.modules.views.fournisseur;



import app.modules.views.client.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;
import app.modules.database.DbManagerNnane;
import app.modules.model.CollecteOeuf;
import app.modules.model.Fournisseur;
import app.modules.model.StockAliment;

import app.modules.userType;
import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//putain
public class FournisseurViewController implements Initializable, IController {
    @FXML
    private TableView<Fournisseur> table;
    
     @FXML
    private TextField rechercher;

    @FXML
    private TableColumn<?, ?> col_nom;

    @FXML
    private TableColumn<?, ?> col_adresse;

    @FXML
    private TableColumn<?, ?> col_tel;

    @FXML
    private TableColumn<?, ?> col_email;

    @FXML
    private TableColumn<?, ?> col_site;

    @FXML
    private TableColumn<?, ?> col_type;
    
    private ObservableList<Fournisseur> data;

    @FXML
    private JFXButton buttonSupprimer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {       
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomFourn"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_site.setCellValueFactory(new PropertyValueFactory<>("siteweb"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("typeFourn"));
        populateTableFournisseur();
        table.setPrefWidth(800);
        System.out.println("FOURNISSEUR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        
        
         this.Search();
        
    }
    
    @FXML
    void Search() {

        data=FXCollections.observableArrayList(DbManagerNnane.selectFournisseurs());
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Fournisseur> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(four -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (four.getAdresse().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (four.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (Integer.toString(four.getTel()).toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
                else if (Integer.toString(four.getTypeFourn()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Fournisseur> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }


    public void populateTableFournisseur()
    {
        ObservableList<Fournisseur> liste=FXCollections.observableArrayList(DbManagerNnane.selectFournisseurs());
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
