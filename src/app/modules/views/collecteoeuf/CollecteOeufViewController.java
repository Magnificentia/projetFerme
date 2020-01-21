package app.modules.views.collecteoeuf;



import app.Projet;
import app.modules.views.employes.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;
import app.modules.database.DbManagerNnane;
import app.modules.model.CollecteOeuf;
import app.modules.model.Fournisseur;

import app.modules.userType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
//putain
public class CollecteOeufViewController implements Initializable, IController {
        @FXML
    private TableView<CollecteOeuf> table;
   @FXML
    private TextField recherche;

    @FXML
    private TableColumn<?, ?> col_bande;

    @FXML
    private TableColumn<?, ?> col_typeoeuf;

    @FXML
    private TableColumn<?, ?> col_quantite;

    @FXML
    private TableColumn<?, ?> col_incubation;

    @FXML
    private TableColumn<?, ?> col_prix;

    @FXML
    private TableColumn<?, ?> col_quantitecasse;
    
    @FXML
    private TableColumn<?, ?> col_date;
    
    private ImageView image;
    private VBox paneElements;
    private Boolean Confirmation;
    private JFXButton btn1;
    private JFXButton btn2;
    private JFXTextField prixAlveole;
   private JFXTextField quantite;
    private JFXComboBox<String> typeOeuf ;        
    private JFXComboBox<String> bande;
    private  JFXComboBox<String> Incubation;
    private JFXDatePicker date;
    private ObservableList<CollecteOeuf> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        

        col_bande.setCellValueFactory(new PropertyValueFactory<>("nomBande"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix_alveole"));
        col_incubation.setCellValueFactory(new PropertyValueFactory<>("incubation"));
        col_quantitecasse.setCellValueFactory(new PropertyValueFactory<>("qteCasse"));
        col_typeoeuf.setCellValueFactory(new PropertyValueFactory<>("nomTypeOeuf"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateCollect"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
        populateTableCollecteOeuf();
        table.setPrefWidth(800);
        
        
       /* this.image = new ImageView(Main.class.getResource("modules/ressources/icons8-chicken-32.png").toString());
        this.paneElements.setMargin(image,new Insets(0,0,25,0));
        HBox paneImage=new HBox();
        paneImage.getChildren().addAll(image);
        paneImage.setAlignment(Pos.CENTER);
        
        paneElements=new VBox();
        
            
            
        prixAlveole=new JFXTextField();
        prixAlveole.setPromptText("veuillez entrez le prixAlveole");
        prixAlveole.setPrefSize(242,24);
        
        quantite=new JFXTextField();
        quantite.setPromptText("veuillez entrez la quantite");
        quantite.setPrefSize(242,24);
        
       
    
   
    
                typeOeuf = new JFXComboBox<String>();
        typeOeuf.setPromptText("choisir le typeOeuf");
        typeOeuf.getItems().addAll("jaune");
        typeOeuf.setPrefSize(242, 24);
        
              bande = new JFXComboBox<String>();
        bande.setPromptText("choisir la bande");
        bande.getItems().addAll("bandeI");
        bande.setPrefSize(242, 24);
        
            Incubation = new JFXComboBox<String>();
        Incubation.setPromptText("choisir la bande");
        Incubation.getItems().addAll("bandeI");
        Incubation.setPrefSize(242, 24);
        
        date=new JFXDatePicker();
        
        HBox hboxNode0=new HBox();
        hboxNode0.getChildren().addAll(typeOeuf);
        hboxNode0.setAlignment(Pos.CENTER);
        paneElements.getChildren().add(hboxNode0);
        
       
        HBox hboxNode1=new HBox();
        hboxNode1.getChildren().addAll(bande);
        hboxNode1.setAlignment(Pos.CENTER);
        paneElements.getChildren().add(hboxNode1);
        
        
        HBox hboxNode2=new HBox();
        hboxNode2.getChildren().addAll(Incubation);
        hboxNode2.setAlignment(Pos.CENTER);
        paneElements.getChildren().add(hboxNode2);
     
       
        HBox hboxNode3=new HBox();
        hboxNode3.getChildren().addAll(date);
        hboxNode3.setAlignment(Pos.CENTER);
        paneElements.getChildren().add(hboxNode3);
        
    
    
                btn1=new JFXButton();
		btn1.setText("Ok");
		btn1.setOnAction(e->btnYes_clicked());
                btn1.setPrefSize(105, 51);
                
                 btn2=new JFXButton();
		btn2.setText("Cancel");
		btn2.setOnAction(e->btnNo_clicked());
                btn2.setPrefSize(105, 51);
		
		Label lab=new Label();
		lab.setText(message);
		
		HBox paneH=new HBox(15);
		paneH.getChildren().addAll(btn1,btn2);
		paneH.setAlignment(Pos.CENTER);
                
                paneElements.getChildren().add(paneH);
     
        
         paneElements.setSpacing(35);
        */
       
       this.Search();
        
    }
    
    @FXML
    void Search() {

        data=FXCollections.observableArrayList(DbManagerNnane.selectCollecteOeufs());
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<CollecteOeuf> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        recherche.textProperty().
        addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(collecte -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (collecte.getNomBande().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (collecte.getNomTypeOeuf().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (Integer.toString(collecte.getIncubation()).toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
                else if (Integer.toString(collecte.getQte()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<CollecteOeuf> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }

    public void populateTableCollecteOeuf()
    {
        ObservableList<CollecteOeuf> liste=FXCollections.observableArrayList(DbManagerNnane.selectCollecteOeufs());
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
    void ajouterElementOnclick(ActionEvent event) {
        
        this.afficherPopup(500,500);
        
        
     

        //(new Popup(){public Object returnItem(){return new Bande(1);}}).show("Confirmation",f);   
        
     }
    
    private void afficherPopup (double Widthsize,double Heightsize)
    {
        
        
        //HBoxpaneImage.setMargin(this.,new Insets(18,0,0,0));
                Scene scene=new Scene(paneElements);
		Stage stage=new Stage();
		stage.setScene(scene);
                
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(table.getScene().getWindow());
                
		stage.show();
                
                
    }
    
    private void btnYes_clicked()
    {
        
    }
    
    private void btnNo_clicked()
    {
        
    }
}
