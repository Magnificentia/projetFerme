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
import app.modules.views.bonjour.Utilisateur;
import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//putain
public class StockAlimentViewController implements Initializable, IController {

    @FXML
    private TextField recherche;
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
    
    
    private ObservableList<StockAliment> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomStock"));
        col_aliment.setCellValueFactory(new PropertyValueFactory<>("nomAli"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateArrivage"));
        col_fournisseur.setCellValueFactory(new PropertyValueFactory<>("nomFournisseur"));
        col_qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
        this.Search();
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
    
        @FXML
    void Search() {

        data=FXCollections.observableArrayList(DbManagerNnane.selectStockAliment());
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<StockAliment> filteredData = new FilteredList<>(data, p -> true);
        
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
                } else if (utilisateur.getNomFournisseur().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (utilisateur.getNomStock().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (Integer.toString(utilisateur.getQte()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<StockAliment> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }
    
    @FXML
    public void showAddStockAlimentrWindow(ActionEvent event) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("options/addStockAlimentOptions.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            //dialogStage.getIcons().add(new Image("file:resources/images/icon2.jpg"));
            dialogStage.setTitle("Ajouter un nouveau stock");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(Main.getMainStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            populateTableRation();

            //
        } catch (IOException e) {
            e.printStackTrace();
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
