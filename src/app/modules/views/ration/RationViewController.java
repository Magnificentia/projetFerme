package app.modules.views.ration;



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
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//putain
public class RationViewController implements Initializable, IController {
        @FXML
    private TableView<Ration> table;

    @FXML
    private TableColumn<?, ?> col_nom;

    @FXML
    private TableColumn<?, ?> col_bande;

    @FXML
    private TableColumn<?, ?> col_aliment;

    @FXML
    private TableColumn<?, ?> col_date;

    @FXML
    private TableColumn<?, ?> col_quantite;

    @FXML
    private TableColumn<?, ?> col_eau;
    
    private ObservableList<Ration> data;
    
    @FXML
    private TextField recherche;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomRation"));
        col_aliment.setCellValueFactory(new PropertyValueFactory<>("nomAli"));
        col_bande.setCellValueFactory(new PropertyValueFactory<>("nomBande"));
        col_eau.setCellValueFactory(new PropertyValueFactory<>("eau"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateRation"));
        this.Search();
        populateTableRation();
        table.setPrefWidth(800);
    }
    
    public void populateTableRation()
    {
        ObservableList<Ration> liste=FXCollections.observableArrayList(DbManagerNnane.selectRations());
        table.setItems(liste);
    }

    
    @FXML
    void Search() {

        data=FXCollections.observableArrayList(DbManagerNnane.selectRations());
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Ration> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        recherche.textProperty().
        addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(utilisateur -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (utilisateur.getNomAli().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (utilisateur.getNomBande().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (utilisateur.getNomRation().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (Integer.toString(utilisateur.getIdration()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Ration> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
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
