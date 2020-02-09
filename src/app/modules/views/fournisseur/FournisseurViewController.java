package app.modules.views.fournisseur;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;
import app.modules.database.DbManager;
import app.modules.model.Fournisseur;
import app.modules.model.StockAliment;

import app.modules.userType;
import app.modules.views.BaseView;
import app.modules.views.Form;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
//putain
public class FournisseurViewController extends BaseView<Fournisseur> implements Initializable, IController {

    
    @FXML
    private TextField rechercher;
     
    @FXML
    private HBox anchor;//mouen a change ca en hbox et a gerer ces caracteristiques dans un fichier fxml


    @FXML
    private JFXButton buttonSupprimer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {       
        createTable();

        anchor.getChildren().add(item);
       // table.setPrefWidth(800);
        System.out.println("FOURNISSEUR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        
        
         this.Search();
        
    }
    
    public void onAjouterClicked(ActionEvent event)
    {
        FormFournisseur b=new FormFournisseur();
        b.show();
        updateData();
    }
    




    
    public void loadData()
    {
        data=FXCollections.observableArrayList(DbManager.selectFournisseurs());
    }
    
    public void createTable()
    {
        //table.setPrefWidth(800);
        
        TableColumn<Fournisseur,String> col_nom=new TableColumn<>("designation");
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomFourn"));
        col_nom.setPrefWidth(200);
        
        TableColumn<Fournisseur,String> col_adresse=new TableColumn<>("adresse");
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_adresse.setPrefWidth(150);
        
        TableColumn<Fournisseur,Integer> col_mail=new TableColumn<>("e-mail");
        col_mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_mail.setPrefWidth(240);
        
        TableColumn<Fournisseur,Integer> col_siteweb=new TableColumn<>("siteweb");
        col_siteweb.setCellValueFactory(new PropertyValueFactory<>("siteweb"));
        col_siteweb.setPrefWidth(250);
        
        
        TableColumn<Fournisseur,Integer> col_tel=new TableColumn<>("telephone");
        col_tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));//voir comment ajouter une liste de numeros
        col_tel.setPrefWidth(140);
        
        TableColumn<Fournisseur,String> col_type=new TableColumn<>("type de fournisseur");
        col_type.setPrefWidth(200);
        

        table.getColumns().addAll(col_nom,col_adresse,col_mail,col_siteweb,col_tel,col_type);
    }
    
    @FXML
    void Search() {

        data=FXCollections.observableArrayList(DbManager.selectFournisseurs());
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
        ObservableList<Fournisseur> liste=FXCollections.observableArrayList(DbManager.selectFournisseurs());
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


class FormFournisseur extends Form
{
    //private final JFXDatePicker date;
    private final JFXTextField designation;
    private final JFXTextField adresse;
    private final JFXTextField siteweb;
    //private final JFXTextField quantite;
    private final JFXTextField telephone;
    private final JFXTextField email;
    //private final JFXComboBox<Aliment> aliment;
    //private final JFXComboBox<Fournisseur> fournisseur;
    
    public FormFournisseur()
    {
        designation=new JFXTextField();
        designation.setPromptText("designation");
        HBox hdesignation=new HBox();
        hdesignation.getChildren().add(designation);
        hdesignation.setPrefWidth(739);
        hdesignation.setPrefHeight(75);
        hdesignation.setAlignment(Pos.CENTER);
        hdesignation.setStyle("-fx-background-color:#f8f8ff");
        //hdescription.setMargin(description,new Insets(20,0,0,0));
        
        adresse=new JFXTextField();
        adresse.setPromptText("adresse");
        HBox hadresse=new HBox();
        hadresse.getChildren().add(adresse);
        hadresse.setPrefWidth(739);
        hadresse.setPrefHeight(75);
        hadresse.setAlignment(Pos.CENTER);
        hadresse.setStyle("-fx-background-color:#f8f8ff");
        
        email=new JFXTextField();
        email.setPromptText("mail");
        HBox hemail=new HBox();
        hemail.getChildren().add(email);
        hemail.setPrefWidth(739);
        hemail.setPrefHeight(75);
        hemail.setAlignment(Pos.CENTER);
        hemail.setStyle("-fx-background-color:#f8f8ff");
        
        siteweb=new JFXTextField();
        siteweb.setPromptText("siteweb");
        HBox hsiteweb=new HBox();
        hsiteweb.getChildren().add(siteweb);
        hsiteweb.setPrefWidth(739);
        hsiteweb.setPrefHeight(75);
        hsiteweb.setAlignment(Pos.CENTER);
        hsiteweb.setStyle("-fx-background-color:#f8f8ff");
        
        
        telephone=new JFXTextField();
        telephone.setPromptText("telephone");
        HBox htelephone=new HBox();
        htelephone.getChildren().add(telephone);
        htelephone.setPrefWidth(739);
        htelephone.setPrefHeight(115);
        htelephone.setAlignment(Pos.TOP_CENTER);
        htelephone.setStyle("-fx-background-color:#f8f8ff");
        htelephone.setMargin(telephone,new Insets(20,0,0,0));
   
        List a=new ArrayList();
        a.add(hdesignation);
        a.add(hadresse);
        a.add(hemail);
        a.add(hsiteweb);
        a.add(htelephone);
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
            String adresse=this.adresse.getText();
            String email=this.email.getText();
            String siteweb=this.siteweb.getText();
            int tel=new Integer(this.telephone.getText());

            Fournisseur fourn=new Fournisseur(designation,adresse,tel,email,siteweb,1);
            //StockAliment stock=new StockAliment(qte,Bande.DEFAULT_DATE,idaliment,idfournisseur);
            //save stock
            if(DbManager.saveFournisseur(fourn))
                System.out.println("fournisseur saved");
        }
    }
