package app.modules.views.bande;



import app.Projet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;
import app.modules.database.DbManager;
import app.modules.model.Bande;
import app.modules.model.Batiment;
import app.modules.model.CollecteOeuf;
import app.modules.model.Fournisseur;
import app.modules.model.Race;


import app.modules.userType;
import app.modules.views.BaseView;
//import app.modules.views.bonjour.Formulaire;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


//putain
public class BandeViewController extends BaseView<Bande> implements Initializable, IController  {


    @FXML
    private HBox AnchorTable;//Mouen a change ça en hbox et a dans le fxml de bande lui a donne des styles directement;
    @FXML
    private JFXButton informations;
    
     @FXML
    private TextField recherche;
    
        
    @Override
    public void initialize(URL location, ResourceBundle resources) {          
        createTable();
        
        //populateTableBande();
        //important d'ajouter le item là, même si tu ne sais pas d'où ça sort ...c'est l'ensemble (table+pagination)
        AnchorTable.getChildren().addAll(item);
        this.Search();
      
        
    }
    
    public void delete(Bande b)
    {
        DbManager.deleteBande(b);
    }
    public void loadData()
    {
        data=FXCollections.observableArrayList(DbManager.selectBandes());
    }
    
    public void createTable()
    {
        
        
        TableColumn<Bande,String> col_nom=new TableColumn<>("DESIGNATION");
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomBande"));
        col_nom.setPrefWidth(177);
   
        TableColumn<Bande,String> col_achat=new TableColumn<>("PRIX D'ACHAT");
        col_achat.setCellValueFactory(new PropertyValueFactory<>("prix_achat"));
        col_achat.setPrefWidth(100);
        
        TableColumn<Bande,Integer> col_age=new TableColumn<>("AGE");
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_age.setPrefWidth(100);
        
        TableColumn<Bande,String> col_date=new TableColumn<>("DATE DE DEMARRAGE");
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateDemarrage"));
        col_date.setPrefWidth(150);
        
        TableColumn<Bande,Integer> col_quantite=new TableColumn<>("QUANTITE");
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
        col_quantite.setPrefWidth(100);
        
        TableColumn<Bande,String> col_batiment=new TableColumn<>("BATIMENT");
        col_batiment.setCellValueFactory(new PropertyValueFactory<>("nomBatiment"));
        col_batiment.setPrefWidth(200);
        
        TableColumn<Bande,String> col_fournisseur=new TableColumn<>("FOURNISSEUR");
        col_fournisseur.setCellValueFactory(new PropertyValueFactory<>("nomFournisseur"));
        col_fournisseur.setPrefWidth(200);
        
        TableColumn<Bande,String> col_race=new TableColumn<>("RACE"); 
        col_race.setCellValueFactory(new PropertyValueFactory<>("nomRace"));
        col_race.setPrefWidth(160);
        
        table.getColumns().addAll(col_nom,col_achat,col_age,col_date,col_quantite,col_batiment,col_fournisseur,col_race);
        
    }
    @FXML
    public void onInformationsClicked(ActionEvent event)
    {
        
        InformationsBande b=new InformationsBande(table.getSelectionModel().getSelectedItem());
        b.show();
        System.out.println("updating...");
        updateData();
    }
    
        @FXML
    public void onAjouterClicked(ActionEvent event)
    {
        InformationsBande b=new InformationsBande(null);
        b.show();
        updateData();
    }
    
    @FXML
    void Search() {

        data=FXCollections.observableArrayList(DbManager.selectBandes());
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Bande> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        recherche.textProperty().
        addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(bande-> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (bande.getNomBande().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (bande.getNomFournisseur().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (bande.getNomBatiment().toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
                else if ((bande.getNomRace()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Bande> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }


    public void updateTableBande()
    {
        table.getItems().clear();//importaant sinon les changements ne vont pas être éffectués lorsqu'on fait de simples modificatioobs
        data=FXCollections.observableArrayList(DbManager.selectBandes());
        table.setItems(data);
    }

    @FXML
    public void handleDelete(ActionEvent event) throws SQLException {

        Bande mat=table.getSelectionModel().getSelectedItem();

        if (mat!=null) {
            if(Projet.showAlert(Alert.AlertType.CONFIRMATION, null, "Form Error!",
                "voulez-vous supprimer cet utilisateur?"))
            {
                System.out.println("suppression");
                DbManager.deleteBande(mat);
                //populateTableBande();
            }
            return;
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



final class InformationsBande extends VBox
{
    private JFXTabPane t;
    private Bande bande;
    
    /*formulaire*/
    JFXTextField qtedepart;
    JFXTextField prix_dachat;
    ComboBox<Batiment> batiment;
    ComboBox<Fournisseur> fournisseur;
    ComboBox<Race> race;
    
    
    
    public InformationsBande(Bande bande)
    {
        super();
        t=new JFXTabPane();
        this.bande=bande;
        JFXButton val=new JFXButton("valider");
        val.setStyle("-fx-background-color:#2aa15b");
        val.setPrefWidth(107);
        val.setPrefHeight(51);
        
        JFXButton ann=new JFXButton("annuler");
        ann.setPrefWidth(107);
        ann.setStyle("-fx-background-color:#2aa15b");
        ann.setPrefHeight(51);
        
        HBox hbuttonVal=new HBox();
        
        hbuttonVal.getChildren().addAll(val,ann);
        hbuttonVal.setSpacing(65);
        hbuttonVal.setAlignment(Pos.CENTER);
        hbuttonVal.setStyle("-fx-background-color:#f8f8ff");
        hbuttonVal.setPrefHeight(600);
        
        
        this.getChildren().addAll(t,hbuttonVal);
        this.setMargin( hbuttonVal, new Insets(10,20, 10, 20));
        t.getTabs().addAll(createInfosBase("informations de base"),createInfosMaladies("maladies et décès"),createInfosVente("vente"));
        val.setOnAction(e->{
          //informations
          boolean update=true;
          if(this.bande==null)
          {
              update=true;
              this.bande=new Bande();
          }   
          this.bande.setBat_id(batiment.getSelectionModel().getSelectedItem().getIdbat());
          this.bande.setFourn_id(fournisseur.getSelectionModel().getSelectedItem().getIdfourn());
          this.bande.setRace_id(race.getSelectionModel().getSelectedItem().getIdrace());
          this.bande.setPrix_achat(new Double(prix_dachat.getText()));
          System.out.println(qtedepart);
          this.bande.setQte(new Integer(qtedepart.getText()));
          if(update && DbManager.updateBande(this.bande))
          {
              System.out.println("update bande réussi");
          }       
          else
          {
              System.out.println("bande saved");
              DbManager.saveBande(this.bande); 
          }
                     
          //maladies et dece
          //vente
        });  
    }
    

    public Tab createInfosBase(String titre)
    {
        VBox v=new VBox();
        
        qtedepart=new JFXTextField();
        qtedepart.setPromptText("Quantité de depart");
        HBox hq=new HBox();
        hq.getChildren().add(qtedepart);
        hq.setAlignment(Pos.CENTER);
        hq.setStyle("-fx-background-color:#f8f8ff");
        hq.setPrefHeight(120);
               
        prix_dachat=new JFXTextField();
        prix_dachat.setPromptText("prix d'achat");
        HBox ha=new HBox();
        ha.getChildren().add(prix_dachat);
        ha.setAlignment(Pos.CENTER);
        ha.setStyle("-fx-background-color:#f8f8ff");
        ha.setPrefHeight(120);
        
        
        JFXTextField prixvente=new JFXTextField();
        prixvente.setPromptText("prix de vente");
        HBox hP=new HBox();
        hP.getChildren().add(prixvente);
        hP.setAlignment(Pos.CENTER);
        hP.setStyle("-fx-background-color:#f8f8ff");
        hP.setPrefHeight(120);
        
        JFXDatePicker dateachat=new JFXDatePicker();
        dateachat.setPromptText("date d'achat");
        HBox hdate=new HBox();
        hdate.getChildren().add(dateachat);
        hdate.setAlignment(Pos.CENTER);
        hdate.setStyle("-fx-background-color:#f8f8ff");
        hdate.setPrefHeight(120);
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        //LocalDate localDate = LocalDate.parse(this.bande.getDateDemarrage(), formatter);
        //dateachat.setValue(localDate);
        
        batiment=new ComboBox<>();
        batiment.setPromptText("batiment");
        batiment.getItems().addAll(DbManager.selectBatiments());
        HBox hbat=new HBox();
        hbat.getChildren().add(batiment);
        hbat.setAlignment(Pos.CENTER);
        hbat.setStyle("-fx-background-color:#f8f8ff");
        hbat.setPrefHeight(120);
        
        
        fournisseur=new ComboBox<>();
        fournisseur.setPromptText("fournisseur");
        fournisseur.getItems().addAll(DbManager.selectFournisseurs());//(this.bande.getFourn_id()));
        HBox hfour=new HBox();
        hfour.getChildren().add(fournisseur);
        hfour.setAlignment(Pos.CENTER);
        hfour.setStyle("-fx-background-color:#f8f8ff");
        hfour.setPrefHeight(120);
        
        race=new ComboBox<>();
        race.setPromptText("race");
        race.getItems().addAll(DbManager.selectRaces());
        HBox hrace=new HBox();
        hrace.getChildren().add(race);
        hrace.setAlignment(Pos.CENTER);
        hrace.setStyle("-fx-background-color:#f8f8ff");
        hrace.setPrefHeight(120);
        //race.getSelectionModel().select(DbManagerNnane.selectRaceById(this.bande.getRace_id()));
        
        JFXTextField qteStock=new JFXTextField();
        qteStock.setPromptText("quantité en stock");
        HBox hqteStock=new HBox();
        hqteStock.getChildren().add(qteStock);
        hqteStock.setAlignment(Pos.CENTER);
        hqteStock.setStyle("-fx-background-color:#f8f8ff");
        hqteStock.setPrefHeight(120);
        
        //populating
        if(this.bande!=null)
        {
            System.out.println("populating bandes infos!");
            qtedepart.setText(new Integer(this.bande.getQte()).toString());
            batiment.getSelectionModel().select(DbManager.selectBatimentById(this.bande.getBat_id()));
            prix_dachat.setText(new Double(this.bande.getPrix_achat()).toString());
            fournisseur.getSelectionModel().select(DbManager.selectFournisseurById(this.bande.getFourn_id()));
            race.getSelectionModel().select(DbManager.selectRaceById(this.bande.getRace_id()));
            qteStock.setText(new Integer(this.bande.getQte()).toString());
        }
                
        
        v.setStyle("-fx-background-color:#e3e9ee");
        v.getChildren().addAll(hq,ha,hP,hdate,hbat,hfour,hrace,hqteStock);
        v.setMargin(hq,new Insets(10,20,0,20));
        v.setMargin(ha, new Insets(0, 20, 0, 20) );
        v.setMargin(hP, new Insets(0, 20, 0, 20) );
        v.setMargin(hdate, new Insets(0, 20, 0, 20) );
        v.setMargin(hbat, new Insets(0, 20, 0, 20) );
        v.setMargin(hfour, new Insets(0, 20, 0, 20) );
        v.setMargin(hrace, new Insets(0, 20, 0, 20) );
        v.setMargin(hqteStock, new Insets(0, 20, 0, 20) );
         
        //v.setSpacing(25);
        //v.setStyle("-fx-alignment:center");
        
        Tab t =new Tab();
        t.setClosable(false);
        t.setGraphic(new Label(titre));
        t.setContent(v);
        return t;
    }
    
    public Tab createInfosMaladies(String titre)
    {
        VBox v=new VBox();
        //TODO
        TableView tableMaladie=new TableView<>();
        //tableMaladie.setPrefWidth(150);
        
        TableColumn<Bande,String> col_nombre=new TableColumn<>("nombre de malades");
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("maladie"));
        col_nombre.setPrefWidth(350);
        
        TableColumn<Bande,String> col_maladie=new TableColumn<>("maladie");
        col_maladie.setCellValueFactory(new PropertyValueFactory<>("prix_achat"));
        col_maladie.setCellFactory(ComboBoxTableCell.forTableColumn("test","test1"));
        col_maladie.setPrefWidth(366);
        
        
        tableMaladie.getColumns().addAll(col_nombre,col_maladie);
        tableMaladie.getItems().add(new Bande("bonjour"));
        
        
        TableView tableDeces=new TableView<>();
        //tableDeces.setPrefWidth(150);
        
        TableColumn<Bande,String> col_nombreDeces=new TableColumn<>("nombre de deces");
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("maladie"));
        col_nombreDeces.setPrefWidth(350);
        
        TableColumn<Bande,String> col_cause=new TableColumn<>("cause");
        col_maladie.setCellValueFactory(new PropertyValueFactory<>("prix_achat"));
        col_cause.setPrefWidth(367);
        
        tableDeces.getColumns().addAll(col_nombreDeces,col_cause);
        
        Tab t =new Tab();
        t.setClosable(false);
        t.setGraphic(new Label(titre));
        
        HBox htabMaladie =new HBox();
        HBox htableDeces=new HBox();
        
        htabMaladie.getChildren().add(tableMaladie);
        //htabMaladie.setStyle("-fx-background:#f8f8ff");
        htableDeces.getChildren().add(tableDeces);
        //htableDeces.setStyle("-fx-background:#f8f8ff");
        
        htabMaladie.setMargin(tableMaladie, new Insets(10, 20, 10, 20) );
        htableDeces.setMargin(tableDeces, new Insets(10, 20, 10, 20) );
        v.getChildren().addAll(htabMaladie,htableDeces);
        v.setSpacing(2);
        
        
        t.setContent(v);
        return t;
    }
    public Tab createInfosAutres(String titre)
    {
        VBox v=new VBox();
        //TODO
        Tab t =new Tab();
        t.setGraphic(new Label(titre));
        t.setContent(v);
        return t;
    }
    
    public Tab createInfosVente(String titre)
    {
        VBox v=new VBox();
        //TODO
        Tab t =new Tab();
        t.setClosable(false);
        t.setGraphic(new Label(titre));
        t.setContent(v);
        return t;
    }
    
    public void show()
    {
        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        //dialogStage.getIcons().add(new Image("file:resources/images/icon2.jpg"));
        dialogStage.setTitle("Ajouter un nouveau stock");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(Projet.getMainStage());
        Scene scene = new Scene(this,757,707);
        scene.getStylesheets().add("app/modules/views/global.css");
        dialogStage.setScene(scene);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }
}

