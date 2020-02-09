package app.modules.views.client;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;
import app.modules.database.DbManager;
import app.modules.model.Client;
import app.modules.model.Fournisseur;
import app.modules.model.StockAliment;

import app.modules.userType;
import app.modules.views.BaseView;
import app.modules.views.Form;
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
public class ClientViewController extends BaseView<Client> implements Initializable, IController {
   
    @FXML
    private TextField recherche;

    @FXML
    private HBox anchor; // mouen a change ca en hbox et les caracteristiques sont dans le fichier fxml de la vue
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        createTable();
        anchor.getChildren().add(item);
        
        populateTableClient();
        table.setPrefWidth(800);

        this.Search();
        
        
    }

    @FXML
    public void onAjouterClicked(ActionEvent event)
    {
        FormClient b=new FormClient();
        b.show();
        System.out.println("updating clients' list");
        updateData();
    }
    public void loadData()
    {
        data=FXCollections.observableArrayList(DbManager.selectClients());
    }
    
    public void createTable()
    {
        //table.setPrefWidth(800);
        
        TableColumn<Client,String> col_nom=new TableColumn<>("nom");
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        col_nom.setPrefWidth(400);
        
        TableColumn<Client,String> col_adresse=new TableColumn<>("adresse");
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_adresse.setPrefWidth(400);
        
        TableColumn<Client,Integer> col_tel=new TableColumn<>("tel");
        col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        col_tel.setPrefWidth(380);
        
        
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
    
}


class FormClient extends Form
{
    //private final JFXDatePicker date;
    private final JFXTextField designation;
    private final JFXTextField adresse;
    //private final JFXTextField siteweb;
    //private final JFXTextField quantite;
    private final JFXTextField telephone;
    //private final JFXTextField email;
    //private final JFXComboBox<Aliment> aliment;
    //private final JFXComboBox<Fournisseur> fournisseur;
    
    public FormClient()
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
        
        telephone=new JFXTextField();
        telephone.setPromptText("telephone");
        HBox htelephone=new HBox();
        htelephone.getChildren().add(telephone);
        htelephone.setPrefWidth(739);
        htelephone.setPrefHeight(255);
        htelephone.setAlignment(Pos.TOP_CENTER);
        htelephone.setStyle("-fx-background-color:#f8f8ff");
        htelephone.setMargin(telephone,new Insets(20,0,0,0));
   
        List a=new ArrayList();
        a.add(hdesignation);
        a.add(hadresse);

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
   
            System.out.println("client");
            String designation=this.designation.getText();
            String adresse=this.adresse.getText();
            int tel=new Integer(this.telephone.getText());

            Client client=new Client(adresse,tel,designation);//(designation,adresse,tel,email,siteweb,1);
            //StockAliment stock=new StockAliment(qte,Bande.DEFAULT_DATE,idaliment,idfournisseur);
            //save stock
            if(DbManager.saveClient(client))
                System.out.println("client saved");
        }
    }