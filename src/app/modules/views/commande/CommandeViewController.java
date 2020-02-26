package app.modules.views.commande;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;
import app.modules.database.DbManager;
import app.modules.model.Bande;
import app.modules.model.Client;
import app.modules.model.CollecteOeuf;
import app.modules.model.StockAliment;
import app.modules.model.Vente;
import app.modules.model.VenteBande;

import app.modules.userType;
import app.modules.views.BaseView;
import app.modules.views.Form;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//putain
public class CommandeViewController extends BaseView<Vente> implements Initializable, IController {
    
    @FXML
    private HBox hbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        createTable();
        System.out.println("HBOX===================="+item);
        hbox.getChildren().add(item);
        table.setPrefWidth(800);
    }
    
    public void loadData()
    {
        data=FXCollections.observableArrayList(DbManager.selectVentesBande());
    }


    @FXML
    void onInformationsClicked(ActionEvent event) {

    }
    
    public void createTable()
    {
        table.setPrefWidth(800);
        
        TableColumn<Vente,String> col_nom=new TableColumn<>("id");
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomVente"));
        
        TableColumn<Vente,String> col_achat=new TableColumn<>("client");
        col_achat.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        
        TableColumn<Vente,Integer> col_age=new TableColumn<>("date");
        col_age.setCellValueFactory(new PropertyValueFactory<>("dateVente"));
        
        TableColumn<Vente,Integer> col_quantite=new TableColumn<>("quantité");
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
        
        TableColumn<Vente,String> col_date=new TableColumn<>("total");
        col_date.setCellValueFactory(new PropertyValueFactory<>("total_prix"));
        

        
        TableColumn<Vente,String> col_batiment=new TableColumn<>("employe");
        col_batiment.setCellValueFactory(new PropertyValueFactory<>("employe_id"));
        
        //TableColumn<Vente,String> col_fournisseur=new TableColumn<>("fournisseur");
        //col_fournisseur.setCellValueFactory(new PropertyValueFactory<>("nomFournisseur"));
        
        //TableColumn<Vente,String> col_race=new TableColumn<>("race"); 
        //col_race.setCellValueFactory(new PropertyValueFactory<>("nomRace"));
        
        table.getColumns().addAll(col_nom,col_achat,col_age,col_date,col_quantite,col_batiment);//,col_fournisseur,col_race);
    }

        public void populateTableCollecteOeuf()
    {
        ObservableList<Vente> liste=FXCollections.observableArrayList(DbManager.selectVentesBande());
        table.setItems(liste);
    }

    @FXML
    public void onAjouterClicked(ActionEvent event)
    {
        FormVente b=new FormVente();
        b.show();
        updateData();
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


class FormVente extends Form
{
    //private final JFXDatePicker date;
    //private final JFXTextField designation;
    private final JFXTextField quantite;
    private final ComboBox<Client>  client;
    private final ComboBox<Bande>  bande;
    private final JFXDatePicker datevente;
    //private final ComboBox<Bande>  bande;
    private final JFXTextField total_prix;

    

    public FormVente()
    {
        quantite=new JFXTextField();
        quantite.setPromptText("quantite");
        HBox hquantite=new HBox();
        hquantite.getChildren().add(quantite);
        hquantite.setPrefWidth(739);
        hquantite.setPrefHeight(75);
        hquantite.setAlignment(Pos.CENTER);
        hquantite.setStyle("-fx-background-color:#f8f8ff");
        
        
        total_prix=new JFXTextField();
        total_prix.setPromptText("nombre cassés");
        HBox hqteCasse=new HBox();
        hqteCasse.getChildren().add(total_prix);
        hqteCasse.setPrefWidth(739);
        hqteCasse.setPrefHeight(75);
        hqteCasse.setAlignment(Pos.CENTER);
        hqteCasse.setStyle("-fx-background-color:#f8f8ff");
        
        datevente=new JFXDatePicker();
        datevente.setPromptText("Choisir date");
        HBox hdatecollect=new HBox();
        hdatecollect.getChildren().add(datevente);
        hdatecollect.setPrefWidth(739);
        hdatecollect.setPrefHeight(75);
        hdatecollect.setAlignment(Pos.CENTER);
        hdatecollect.setStyle("-fx-background-color:#f8f8ff");
        
        
        bande=new ComboBox<>();
        bande.setPromptText("Choisir Bande");
        HBox hbande=new HBox();
        hbande.getChildren().add(bande);
        hbande.setPrefWidth(739);
        hbande.setPrefHeight(75);
        hbande.setAlignment(Pos.CENTER);
        hbande.setStyle("-fx-background-color:#f8f8ff");
        bande.setItems(FXCollections.observableArrayList(DbManager.selectBandes()));
        
        
        client=new ComboBox<>();
        client.setPromptText("Choisir Bande");
        HBox hclient=new HBox();
        hclient.getChildren().add(client);
        hclient.setPrefWidth(739);
        hclient.setPrefHeight(75);
        hclient.setAlignment(Pos.CENTER);
        hclient.setStyle("-fx-background-color:#f8f8ff");
        client.setItems(FXCollections.observableArrayList(DbManager.selectClients()));
        //siteweb.setPromptText("siteweb");
        
   
        List a=new ArrayList();
        a.add(hclient);
        a.add(hbande);
        a.add(hquantite);
        a.add(hdatecollect);
        a.add(hqteCasse);
        addFields(a);
    }
    
    private void populate(StockAliment stock)
    {
        //designation.setText(stock.getNomStock());
        //quantite.setText(new Double(stock.getQte()).toString());
        //date.setText(stock.getDateArrivage());
        //fournisseur.getSelectionModel().select(new Fournisseur(this.stock.getFourn_id()));
        //aliment.getSelectionModel().select(new Aliment(this.stock.getAli_id()));
    }
    

    @Override
    public void onValidateClick() {
        System.out.println("validate clicked");
   
            System.out.println("collecte oeuf");
            int qte=new Integer(this.quantite.getText());
            int idbande=this.bande.getSelectionModel().getSelectedItem().getIdBande();
            int total_prix= new Integer(this.total_prix.getText());
            int idclient=new Integer(this.client.getSelectionModel().getSelectedItem().getIdClient());
            int idemploye=1;
            //String typeEmp=this.typeEmp.getSelectionModel().getSelectedItem();



            VenteBande col=new VenteBande(idbande,idclient,Bande.DEFAULT_DATE,total_prix,qte,idemploye);
            //save stock
            if(DbManager.saveVenteBande(col))
                System.out.println("fournisseur saved");
        }
    }

