package app.modules.views.ration;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;

import app.modules.database.DbManager;
import app.modules.model.Aliment;
import app.modules.model.Bande;

import app.modules.model.Ration;

import app.modules.userType;
import app.modules.views.Form;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//putain
public class RationViewController implements Initializable, IController {
    
       @FXML
    private ComboBox<Bande> choice_Bande;

    @FXML
    private ComboBox<Aliment> choice_aliment;
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
        
        /*configuration choice_bande*/
        
        Bande element=new Bande(0);
        element.setNomBande("choisir aucun");
        choice_Bande.setItems(FXCollections.observableArrayList(DbManager.selectBandes()));
        choice_Bande.getItems().add(0, element);
        choice_Bande.setPromptText("Choisir Bande");
        choice_Bande.setValue(element);
        choice_Bande.setButtonCell(new ListCell<Bande>() {
        @Override
        protected void updateItem(Bande item, boolean empty) {
            super.updateItem(item, empty) ;
            if (empty || item == element) {
                setText("Choisir Bande");
            } 
            else {
                setText(item.getNomBande());
            }
        }
        });
        
        /*configuration choice aliment*/
        
        Aliment aliment=new Aliment(0,"choisir aucun","choisir aucun",0.0);
        choice_aliment.setItems(FXCollections.observableArrayList(DbManager.selectAliments()));
        choice_aliment.getItems().add(0, aliment);
        choice_aliment.setPromptText("Choisir aliment");
        choice_aliment.setValue(aliment);
        choice_aliment.setButtonCell(new ListCell<Aliment>() {
        @Override
        protected void updateItem(Aliment item, boolean empty) {
            super.updateItem(item, empty) ;
            if (empty || item == aliment) {
                setText("Choisir aliment");
            } 
            else {
                setText(item.getNomAli());
            }
        }
        });
        
        /*configuration table*/
        
        //col_nom.setCellValueFactory(new PropertyValueFactory<>("nomRation"));
        col_aliment.setCellValueFactory(new PropertyValueFactory<>("nomAli"));
        col_bande.setCellValueFactory(new PropertyValueFactory<>("nomBande"));
        col_eau.setCellValueFactory(new PropertyValueFactory<>("eau"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateRation"));
        populateTableRation();
        table.setPrefWidth(1100);
        //data=FXCollections.observableArrayList(DbManagerNnane.selectRations());
        //this.Search();
        
    }
     @FXML
    void OnClickChoisirAliment(ActionEvent event) {

        SearchComboBox();
       
    }

    @FXML
    void OnClickChoisirBande(ActionEvent event) {
        
        SearchComboBox();
      
    }
    void SearchComboBox()
    {
        if( choice_Bande.getValue().getNomBande().equals("choisir aucun") && choice_aliment.getValue().getNomAli().equals("choisir aucun"))
        {
            populateTableRation();
        }    
        else
        {
            data=FXCollections.observableArrayList(DbManager.selectRations());
            int i=0;
            while(i<data.size())
            {   
                if(choice_Bande.getValue().getNomBande().equals("choisir aucun") || choice_aliment.getValue().getNomAli().equals("choisir aucun"))
                {    
                  if((data.get(i).getNomBande().equals(choice_Bande.getValue().getNomBande())) || data.get(i).getNomAli().equals(choice_aliment.getValue().getNomAli()))
                  {
                              i++;
                  }
                  else
                  {
                      data.remove(i);
                  } 
                  
                }
                else
                {
                    System.out.println("data="+data+"choicebande="+choice_Bande);
                    
                  if(data.get(i).getNomBande().equals(choice_Bande.getValue().getNomBande()) && data.get(i).getNomAli().equals(choice_aliment.getValue().getNomAli()) )
                   {
                       i++;
                   }
                   else
                  {   
                    data.remove(i);
                  }  
                }
                
            }
            
            table.setItems(data);
        }
    }
    
    @FXML
    void Search(ActionEvent event){
        
        System.out.println("test sur RECHERCHE");

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Ration> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        recherche.textProperty().
        addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(ration -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                  /*if(choice_Bande.getValue().toString().toLowerCase().contains(lowerCaseFilter)){
                
                 return true;
                }*/
                
                if (ration.getNomAli().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                else if (ration.getDateRation().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
               
                else if (Integer.toString((int) ration.getQte()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else{

                return false;
                }// Does not match.
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

        data=FXCollections.observableArrayList(DbManager.selectRations());
        table.setItems(data);

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
        //automatisation de la création de formulaires
        FormRation formulaire=new FormRation();
        formulaire.show();
    }
    
}



class FormRation extends Form
{
    //voici un exemple création de formulaire

    public FormRation() {
        //on spécifie les différents champs
        super();
        List<Node> liste=new ArrayList();
        liste.add(new JFXTextField());
        JFXComboBox fournisseur=new JFXComboBox();
        fournisseur.setItems(FXCollections.observableArrayList(DbManager.selectFournisseurs()));
        liste.add(fournisseur);
        this.addFields(liste);
    }    

    @Override
    public void onValidateClick() {
        //on spécifie l'action à faire pour chaque bouton
        System.out.println("appel de la bonne fonction");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}