package app.modules.views.ration;



import app.Projet;
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
import app.modules.views.Form;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;

import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    
              this.Search();
        
    }
    
    @FXML
    void Search() {

        data=FXCollections.observableArrayList(DbManagerNnane.selectRations());
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Ration> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        recherche.textProperty().
        addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(rat -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (rat.getNomAli().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (rat.getDateRation().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (rat.getNomBande().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (Integer.toString(rat.getIdration()).toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
                else if (rat.getNomRation().toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
                else if (Integer.toString((int) rat.getQte()).toLowerCase().contains(lowerCaseFilter)) {
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

    public void populateTableRation()
    {
        ObservableList<Ration> liste=FXCollections.observableArrayList(DbManagerNnane.selectRations());
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
    public void showAddRationWindow(ActionEvent event) {
        //automatisation de la cr�ation de formulaires
        FormRation formulaire=new FormRation();
        formulaire.show();
    }
    
}



class FormRation extends Form
{
    //voici un exemple cr�ation de formulaire

    public FormRation() {
        //on sp�cifie les diff�rents champs
        super();
        List<Node> liste=new ArrayList();
        liste.add(new JFXTextField());
        JFXComboBox fournisseur=new JFXComboBox();
        fournisseur.setItems(FXCollections.observableArrayList(DbManagerNnane.selectFournisseurs()));
        liste.add(fournisseur);
        this.addFields(liste);
    }    

    @Override
    public void onValidateClick() {
        //on sp�cifie l'action � faire pour chaque bouton
        System.out.println("appel de la bonne fonction");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}