package app.modules.views.bande;



import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;
import app.modules.database.DbManagerNnane;
import app.modules.model.Bande;

import app.modules.model.StockAliment;

import app.modules.userType;
//import app.modules.views.bonjour.Formulaire;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


//putain
public class BandeViewController implements Initializable, IController {

    
    @FXML
    private TableView<Bande> table;

    @FXML
    private TableColumn<?, ?> col_nom;

    @FXML
    private TableColumn<?, ?> col_quantite;

    @FXML
    private TableColumn<?, ?> col_age;

    @FXML
    private TableColumn<?, ?> col_race;

    @FXML
    private TableColumn<?, ?> col_date;

    @FXML
    private TableColumn<?, ?> col_achat;

    @FXML
    private TableColumn<?, ?> col_fournisseur;

    @FXML
    private TableColumn<?, ?> col_batiment;

    //Popup
   
    private ImageView image;
    private VBox paneElements;
    private Boolean Confirmation;
    private JFXButton btn1;
    private JFXButton btn2;
    private JFXTextField textQuantite;
    private JFXTextField textAge;        
    private JFXComboBox<String> comboRace ;        
    private JFXComboBox<String> comboBatiment;
    private  JFXComboBox<String> comboFournisseur; 
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomBande"));
        col_achat.setCellValueFactory(new PropertyValueFactory<>("prix_achat"));
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateDemarrage"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
        col_batiment.setCellValueFactory(new PropertyValueFactory<>("nomBatiment"));
        col_fournisseur.setCellValueFactory(new PropertyValueFactory<>("nomFournisseur"));
        col_race.setCellValueFactory(new PropertyValueFactory<>("nomRace"));
        populateTableBande();
        table.setPrefWidth(800);
        
        paneElements=new VBox();
        
       
        this.image = new ImageView(Main.class.getResource("modules/ressources/elevage.png").toString());
        
        this.paneElements.setMargin(image,new Insets(0,0,25,0));
        HBox paneImage=new HBox();
        paneImage.getChildren().addAll(image);
        paneImage.setAlignment(Pos.CENTER);
        
        textQuantite=new JFXTextField();
        textQuantite.setPromptText("veuillez entrez la quantite");
        textQuantite.setPrefSize(242,24);
        textAge=new JFXTextField();
        textAge.setPromptText("veuillez entrez l'age");
        textAge.setPrefSize(242, 24);
        
          
        
        
        comboRace = new JFXComboBox<String>();
        comboRace.setPromptText("choisir Race");
        comboRace.getItems().addAll("pouletJaune");
        comboRace.setPrefSize(242, 24);
        
        comboBatiment = new JFXComboBox<String>();
        comboBatiment.getItems().addAll("pouletJaune");
        comboBatiment.setPromptText("choisir Batiment");
        comboBatiment.setPrefSize(242, 24);
        
        comboFournisseur = new JFXComboBox<String>();
        comboFournisseur.getItems().addAll("pouletJaune");
        comboFournisseur.setPromptText("choisir Fournisseur");
        comboFournisseur.setPrefSize(242, 24);
        
        this.paneElements=new VBox();
        System.out.println("before array");
        
        //list.add(Quantite);
        
        
        
        
        HBox hboxNode0=new HBox();
        hboxNode0.getChildren().addAll(textQuantite);
        hboxNode0.setAlignment(Pos.CENTER);
        paneElements.getChildren().add(hboxNode0);
        
       
        HBox hboxNode1=new HBox();
        hboxNode1.getChildren().addAll(textAge);
        hboxNode1.setAlignment(Pos.CENTER);
        paneElements.getChildren().add(hboxNode1);
        
        
        HBox hboxNode2=new HBox();
        hboxNode2.getChildren().addAll(comboRace);
        hboxNode2.setAlignment(Pos.CENTER);
        paneElements.getChildren().add(hboxNode2);
     
       
        HBox hboxNode3=new HBox();
        hboxNode3.getChildren().addAll(comboBatiment);
        hboxNode3.setAlignment(Pos.CENTER);
        paneElements.getChildren().add(hboxNode3);
        
        
        HBox hboxNode4=new HBox();
        hboxNode4.getChildren().addAll(comboFournisseur);
        hboxNode4.setAlignment(Pos.CENTER);
        paneElements.getChildren().add(hboxNode4);
        //f.addElement(list);
        
                JFXButton btn1=new JFXButton();
		btn1.setText("Ok");
		btn1.setOnAction(e->btnYes_clicked());
                btn1.setPrefSize(105, 51);
                
                JFXButton btn2=new JFXButton();
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

    public void populateTableBande()
    {
        ObservableList<Bande> liste=FXCollections.observableArrayList(DbManagerNnane.selectBandes());
        table.setItems(liste);
    }

    @FXML
    public void handleDelete(ActionEvent event) throws SQLException {

        Bande mat=table.getSelectionModel().getSelectedItem();

        if (mat!=null) {
            if(Main.showAlert(Alert.AlertType.CONFIRMATION, null, "Form Error!",
                "voulez-vous supprimer cet utilisateur?"))
            {
                System.out.println("suppression");
                //DbManagerNnane.suppBande(mat);
                populateTableBande();
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
    
    @FXML
    void ajouterElementOnclick(ActionEvent event) {
        
        this.afficherPopup(500,500);
        
        
     

        //(new Popup(){public Object returnItem(){return new Bande(1);}}).show("Confirmation",f);   
        
     }
    
    private void afficherPopup (double Widthsize,double Heightsize)
    {
        
        
        //HBoxpaneImage.setMargin(this.,new Insets(18,0,0,0));
                Scene scene=new Scene(paneElements);
                scene.getStylesheets().add(getClass().getResource("/modules/global.css").toExternalForm());
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
