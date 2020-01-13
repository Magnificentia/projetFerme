package app.modules.views.client;



import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;
import app.modules.database.DbManagerNnane;
import app.modules.model.Client;
import app.modules.model.CollecteOeuf;

import app.modules.userType;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;


import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
//putain
public class ClientViewController implements Initializable, IController {
   
        @FXML
    private TableView<Client> table;

    @FXML
    private TableColumn<?, ?> col_nom;

    @FXML
    private TableColumn<?, ?> col_adresse;

    @FXML
    private TableColumn<?, ?> col_tel;

    @FXML
    private JFXButton buttonSupprimer;
    private ImageView image;
    private JFXButton btn1;
    private JFXButton btn2;
    private VBox paneElements;
    private TextField nom;
    private TextField Telephone;
    private TextField adresse;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        populateTableClient();
        table.setPrefWidth(800);
        paneElements=new VBox();
        
        this.image = new ImageView(Main.class.getResource("modules/ressources/Users.png").toString());
        this.paneElements.setMargin(image,new Insets(0,0,25,0));
        HBox paneImage=new HBox();
        paneImage.getChildren().addAll(image);
        paneImage.setAlignment(Pos.CENTER);
        
        nom=new JFXTextField();
        nom.setPromptText("veuillez entrez le nom");
        nom.setPrefSize(242,24);
        
        Telephone=new JFXTextField();
        Telephone.setPromptText("veuillez entrez le Telephone");
        Telephone.setPrefSize(242,24);
        
        adresse=new JFXTextField();
        adresse.setPromptText("veuillez entrez l' adresse");
        adresse.setPrefSize(242,24);
        
        
        HBox hboxNode0=new HBox();
        hboxNode0.getChildren().addAll(nom);
        hboxNode0.setAlignment(Pos.CENTER);
        paneElements.getChildren().add(hboxNode0);
        
       
        HBox hboxNode1=new HBox();
        hboxNode1.getChildren().addAll(Telephone);
        hboxNode1.setAlignment(Pos.CENTER);
        paneElements.getChildren().add(hboxNode1);
        
        
        HBox hboxNode2=new HBox();
        hboxNode2.getChildren().addAll(adresse);
        hboxNode2.setAlignment(Pos.CENTER);
        paneElements.getChildren().add(hboxNode2);
        
                btn1=new JFXButton();
		btn1.setText("Ok");
		btn1.setOnAction(e->btnYes_clicked());
                btn1.setPrefSize(105, 51);
                
                 btn2=new JFXButton();
		btn2.setText("Cancel");
		btn2.setOnAction(e->btnNo_clicked());
                btn2.setPrefSize(105, 51);
		
		/*Label lab=new Label();
		lab.setText(message);*/
		
		HBox paneH=new HBox(15);
		paneH.getChildren().addAll(btn1,btn2);
		paneH.setAlignment(Pos.CENTER);
                
                paneElements.getChildren().add(paneH);
     
        
         paneElements.setSpacing(35);
        
        
        
    }

    
    public void populateTableClient()
    {
        ObservableList<Client> liste=FXCollections.observableArrayList(DbManagerNnane.selectClients());
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
    
    /*@FXML
    /*void ButtonSupprimerOnClick(ActionEvent event) {
       boolean bool= Popup.show("Veuillez entrez les informations", "Editer",null);
    }*/
    
      @FXML
    void ajouterClientOnclick(ActionEvent event) {

        this.afficherPopup(500,500);
    }
    
     private void afficherPopup (double Widthsize,double Heightsize)
    {
        
        
        //HBoxpaneImage.setMargin(this.,new Insets(18,0,0,0));
                Scene scene=new Scene(paneElements);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
                
    }
    
    
     private void btnYes_clicked()
    {
        
    }
    
       
    
     
    private void btnNo_clicked()
    {
        
    }
    
}
