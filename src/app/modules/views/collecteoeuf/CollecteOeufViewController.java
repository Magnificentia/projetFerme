package app.modules.views.collecteoeuf;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;
import app.modules.database.DbManager;
import app.modules.model.CollecteOeuf;

import app.modules.userType;
import app.modules.views.BaseView;

import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
//putain
public class CollecteOeufViewController extends BaseView<CollecteOeuf> implements Initializable, IController {


    
    @FXML
    private TextField recherche;
    
    @FXML
    private AnchorPane anchor;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

       createTable();
       anchor.getChildren().add(item);
        
       table.setPrefWidth(800);
       this.Search();
        
    }
    
    public void loadData()
    {
        data=FXCollections.observableArrayList(DbManager.selectCollecteOeufs());
    }
    
    public void createTable()
    {
        table.setPrefWidth(800);
        
        TableColumn<CollecteOeuf,String> col_date=new TableColumn<>("date");
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        TableColumn<CollecteOeuf,String> col_heure=new TableColumn<>("heure");
        col_date.setCellValueFactory(new PropertyValueFactory<>("heure"));
        
        TableColumn<CollecteOeuf,Integer> col_bande=new TableColumn<>("bande");
        col_bande.setCellValueFactory(new PropertyValueFactory<>("nomBande"));
        
        TableColumn<CollecteOeuf,String> col_qte=new TableColumn<>("quantité");
        col_qte.setCellValueFactory(new PropertyValueFactory<>("qte"));

        table.getColumns().addAll(col_date,col_heure,col_bande,col_qte);
    }
    
    @FXML
    void Search() {

        data=FXCollections.observableArrayList(DbManager.selectCollecteOeufs());
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<CollecteOeuf> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        recherche.textProperty().
        addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(collecte -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (collecte.getNomBande().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (collecte.getNomTypeOeuf().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (Integer.toString(collecte.getIncubation()).toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
                else if (Integer.toString(collecte.getQte()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<CollecteOeuf> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }

    public void populateTableCollecteOeuf()
    {
        ObservableList<CollecteOeuf> liste=FXCollections.observableArrayList(DbManager.selectCollecteOeufs());
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
