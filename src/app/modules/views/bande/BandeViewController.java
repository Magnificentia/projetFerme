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
import app.modules.model.Fournisseur;
import app.modules.model.Race;


import app.modules.userType;
import app.modules.views.BaseView;
//import app.modules.views.bonjour.Formulaire;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


//putain
public class BandeViewController extends BaseView<Bande> implements Initializable, IController  {


    @FXML
    private AnchorPane AnchorTable;
    @FXML
    private JFXButton informations;
        
    @Override
    public void initialize(URL location, ResourceBundle resources) {          
        createTable();
        
        //populateTableBande();
        //important d'ajouter le item l�, m�me si tu ne sais pas d'o� �a sort ...c'est l'ensemble (table+pagination)
        AnchorTable.getChildren().addAll(item);
        
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
        table.setPrefWidth(800);
        
        TableColumn<Bande,String> col_nom=new TableColumn<>("nom");
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomBande"));
        
        TableColumn<Bande,String> col_achat=new TableColumn<>("prix d'achat");
        col_achat.setCellValueFactory(new PropertyValueFactory<>("prix_achat"));
        
        TableColumn<Bande,Integer> col_age=new TableColumn<>("age");
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        
        TableColumn<Bande,String> col_date=new TableColumn<>("date");
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateDemarrage"));
        
        TableColumn<Bande,Integer> col_quantite=new TableColumn<>("quantit�");
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
        
        TableColumn<Bande,String> col_batiment=new TableColumn<>("batiment");
        col_batiment.setCellValueFactory(new PropertyValueFactory<>("nomBatiment"));
        
        TableColumn<Bande,String> col_fournisseur=new TableColumn<>("fournisseur");
        col_fournisseur.setCellValueFactory(new PropertyValueFactory<>("nomFournisseur"));
        
        TableColumn<Bande,String> col_race=new TableColumn<>("race"); 
        col_race.setCellValueFactory(new PropertyValueFactory<>("nomRace"));
        
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
    


    public void updateTableBande()
    {
        table.getItems().clear();//importaant sinon les changements ne vont pas �tre �ffectu�s lorsqu'on fait de simples modificatioobs
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
    private TabPane t;
    private Bande bande;
    
    /*formulaire*/
    JFXTextField qtedepart;
    JFXTextField prix_dachat;
    JFXComboBox<Batiment> batiment;
    JFXComboBox<Fournisseur> fournisseur;
    JFXComboBox<Race> race;
    
    
    
    public InformationsBande(Bande bande)
    {
        super();
        t=new TabPane();
        this.bande=bande;
        JFXButton val=new JFXButton("valider");
        this.getChildren().addAll(t,val);
        t.getTabs().addAll(createInfosBase("informations de base"),createInfosMaladies("maladies et d�c�s"),createInfosVente("vente"));
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
              System.out.println("update bande r�ussi");
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
        qtedepart.setPromptText("Quantit� de depart");
               
        prix_dachat=new JFXTextField();
        prix_dachat.setPromptText("prix d'achat");
        
        
        JFXTextField prixvente=new JFXTextField();
        prixvente.setPromptText("prix de vente");
        
        JFXDatePicker dateachat=new JFXDatePicker();
        dateachat.setPromptText("date d'achat");
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        //LocalDate localDate = LocalDate.parse(this.bande.getDateDemarrage(), formatter);
        //dateachat.setValue(localDate);
        
        batiment=new JFXComboBox<>();
        batiment.setPromptText("batiment");
        batiment.getItems().addAll(DbManager.selectBatiments());
        
        
        fournisseur=new JFXComboBox<>();
        fournisseur.setPromptText("fournisseur");
        fournisseur.getItems().addAll(DbManager.selectFournisseurs());//(this.bande.getFourn_id()));
        
        
        race=new JFXComboBox<>();
        race.setPromptText("race");
        race.getItems().addAll(DbManager.selectRaces());
        //race.getSelectionModel().select(DbManagerNnane.selectRaceById(this.bande.getRace_id()));
        
        JFXTextField qteStock=new JFXTextField();
        qteStock.setPromptText("quantit� en stock");
        
        //populating
        if(this.bande!=null)
        {
            System.out.println("populating bandes infos!");
            qtedepart.setText(new Integer(this.bande.getQte()).toString());
            batiment.getSelectionModel().select(DbManager.selectBatimentById(this.bande.getBat_id()));
            prix_dachat.setText(new Double(this.bande.getPrix_achat()).toString());
            fournisseur.getSelectionModel().select(DbManager.selectFournisseurById(this.bande.getFourn_id()));
            qteStock.setText(new Integer(this.bande.getQte()).toString());
        }
        
        v.getChildren().addAll(qtedepart,prix_dachat,prixvente,dateachat,batiment,fournisseur,race,qteStock);
        
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
        
        TableColumn<Bande,String> col_nombre=new TableColumn<>("nombre de malades");
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("maladie"));
        
        TableColumn<Bande,String> col_maladie=new TableColumn<>("maladie");
        col_maladie.setCellValueFactory(new PropertyValueFactory<>("prix_achat"));
        col_maladie.setCellFactory(ComboBoxTableCell.forTableColumn("test","test1"));
        
        
        tableMaladie.getColumns().addAll(col_nombre,col_maladie);
        tableMaladie.getItems().add(new Bande("bonjour"));
        
        
        TableView tableDeces=new TableView<>();
        
        TableColumn<Bande,String> col_nombreDeces=new TableColumn<>("nombre de deces");
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("maladie"));
        
        TableColumn<Bande,String> col_cause=new TableColumn<>("cause");
        col_maladie.setCellValueFactory(new PropertyValueFactory<>("prix_achat"));
        
        tableDeces.getColumns().addAll(col_nombreDeces,col_cause);
        
        Tab t =new Tab();
        t.setClosable(false);
        t.setGraphic(new Label(titre));
        v.getChildren().addAll(tableMaladie,tableDeces);
        
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
        Scene scene = new Scene(this,600,500);
        dialogStage.setScene(scene);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }
    
}

