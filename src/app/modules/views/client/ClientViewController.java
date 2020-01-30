package app.modules.views.client;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;
import app.modules.database.DbManager;
import app.modules.model.Client;

import app.modules.userType;
import app.modules.views.BaseView;




import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
//putain
public class ClientViewController extends BaseView<Client> implements Initializable, IController {
   
    @FXML
    private TextField recherche;

    @FXML
    private AnchorPane anchor;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        anchor.getChildren().add(item);
        
        populateTableClient();
        table.setPrefWidth(800);

        this.Search();
        
        
    }

    public void loadData()
    {
        data=FXCollections.observableArrayList(DbManager.selectClients());
    }
    
    public void createTable()
    {
        table.setPrefWidth(800);
        
        TableColumn<Client,String> col_nom=new TableColumn<>("nom");
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        
        TableColumn<Client,String> col_adresse=new TableColumn<>("adresse");
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        
        TableColumn<Client,Integer> col_tel=new TableColumn<>("tel");
        col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        
        
        table.getColumns().addAll(col_nom,col_adresse,col_tel);
    }

    public void populateTableClient()
    {
        ObservableList<Client> liste=FXCollections.observableArrayList(DbManager.selectClients());
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
    
    @FXML
    void Search() {

        data=FXCollections.observableArrayList(DbManager.selectClients());
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Client> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        recherche.textProperty().
        addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(client -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (client.getAdresse().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (client.getNomClient().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (Integer.toString(client.getTel()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Client> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }

    
    
     private void btnYes_clicked()
    {
        
    }
           
    
     
    private void btnNo_clicked()
    {
        
    }
    
}
