package app.modules.views.employes;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;
import app.modules.database.DbManager;
import app.modules.model.Employes;
import app.modules.model.StockAliment;

import app.modules.userType;
import app.modules.views.BaseView;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
//putain
public class EmployesViewController extends BaseView<Employes> implements Initializable, IController {

    
      @FXML
    private TextField recherche;
      
    @FXML
    private AnchorPane anchor;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createTable();
        table.setPrefWidth(800);
        System.out.println(anchor);
        anchor.getChildren().add(item);
         this.Search();
        
    }
    
    public void onAjouterClicked(ActionEvent event)
    {
        FormEmployes b=new FormEmployes();
        b.show();
        updateData();
    }
    
    public void loadData()
    {
        data=FXCollections.observableArrayList(DbManager.selectEmployes());
    }
    
    public void createTable()
    {
        table.setPrefWidth(800);
        
        TableColumn<Employes,String> col_nom=new TableColumn<>("nom");
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        TableColumn<Employes,String> col_login=new TableColumn<>("login");
        col_login.setCellValueFactory(new PropertyValueFactory<>("user"));
        
        TableColumn<Employes,Integer> col_pwd=new TableColumn<>("mot de passe");
        col_pwd.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        TableColumn<Employes,String> col_statut=new TableColumn<>("statut");
        

        table.getColumns().addAll(col_nom,col_login,col_pwd,col_statut);
    }
    
    @FXML
    void Search() {

        data=FXCollections.observableArrayList(DbManager.selectEmployes());
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Employes> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        recherche.textProperty().
        addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(emp -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (emp.getLogin().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (emp.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if(emp.getTypeEm().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if(emp.getUser().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                

                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Employes> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }


    public void populateTableEmployes()
    {
        table.getItems().clear();
        ObservableList<Employes> liste=FXCollections.observableArrayList(DbManager.selectEmployes());
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



class FormEmployes extends Form
{
    //private final JFXDatePicker date;
    private final JFXTextField designation;
    private final JFXTextField user;
    private final JFXTextField login;
    //private final JFXTextField quantite;

    private final JFXComboBox<String> typeEmp;
    //private final JFXComboBox<Fournisseur> fournisseur;
    
    public FormEmployes()
    {
        designation=new JFXTextField();
        designation.setPromptText("designation");
        
        user=new JFXTextField();
        user.setPromptText("adresse");
        
        login=new JFXTextField();
        login.setPromptText("mail");
        
        typeEmp=new JFXComboBox<>();
        List l=new ArrayList();
        l.add("vendeur");
        l.add("fermier");
        l.add("commercial");
        l.add("administrateur");
        typeEmp.setItems(FXCollections.observableArrayList(l));
        //siteweb.setPromptText("siteweb");
        
   
        List a=new ArrayList();
        a.add(designation);
        a.add(user);
        a.add(login);
        a.add(typeEmp);
        addFields(a);
    }
    
    private void populate(StockAliment stock)
    {
        designation.setText(stock.getNomStock());
        //quantite.setText(new Double(stock.getQte()).toString());
        //date.setText(stock.getDateArrivage());
        //fournisseur.getSelectionModel().select(new Fournisseur(this.stock.getFourn_id()));
        //aliment.getSelectionModel().select(new Aliment(this.stock.getAli_id()));
    }
    

    @Override
    public void onValidateClick() {
        System.out.println("validate clicked");
   
            System.out.println("fournisseur");
            String designation=this.designation.getText();
            String user=this.user.getText();
            String login=this.login.getText();
            String typeEmp=this.typeEmp.getSelectionModel().getSelectedItem();


            Employes emp=new Employes(login, user, login, typeEmp);
            //StockAliment stock=new StockAliment(qte,Bande.DEFAULT_DATE,idaliment,idfournisseur);
            //save stock
            if(DbManager.saveEmploye(emp))
                System.out.println("fournisseur saved");
        }
    }
