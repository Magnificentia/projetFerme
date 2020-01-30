package app.modules.views.medicaments;




import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;
import app.modules.database.DbManager;
import app.modules.model.Medicament;

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
public class MedicViewController extends BaseView<Medicament> implements Initializable, IController {
    
    @FXML
    private TextField rechercher;
    
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
        data=FXCollections.observableArrayList(DbManager.selectMedicaments());
    }
    
    public void createTable()
    {
        table.setPrefWidth(800);
        
        TableColumn<Medicament,String> col_nom=new TableColumn<>("designation");
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomFourn"));
        
        TableColumn<Medicament,String> col_prix=new TableColumn<>("prix");
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        TableColumn<Medicament,Integer> col_duree=new TableColumn<>("durée");
        col_duree.setCellValueFactory(new PropertyValueFactory<>("periode"));
        
        
        
        TableColumn<Medicament,Integer> col_desc=new TableColumn<>("description");
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));//voir comment ajouter une liste de numeros
        

        table.getColumns().addAll(col_nom,col_prix,col_duree,col_desc);
    }
    @FXML
    void Search() {

        data=FXCollections.observableArrayList(DbManager.selectMedicaments());
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Medicament> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        rechercher.textProperty().
        addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(vacc -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (vacc.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (vacc.getDate().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if(vacc.getNomVac().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if(vacc.getPeriode().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (Integer.toString((int) vacc.getPrix()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                

                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Medicament> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }

    public void populateTableVaccin()
    {
        ObservableList<Medicament> liste=FXCollections.observableArrayList(DbManager.selectMedicaments());
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
