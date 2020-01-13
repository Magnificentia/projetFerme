package app.modules.views.bonjour;



import app.modules.IController;
import app.modules.userType;

import app.modules.views.bonjour.Utilisateur;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;


import java.net.URL;
//import java.time.Duration;
//import java.time.Duration;
import java.util.*;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;

//putain
public class TestController implements Initializable, IController {
    @FXML
    TableView<Utilisateur> table;

    @FXML
    private AnchorPane root;
    
    @FXML
    private HBox ParentHbox;
    
    /*@FXML
    private VBox VboxRight;
    
    @FXML
    private BorderPane BorderPane;
    */ 
      @FXML
    private AnchorPane PaneTable;
    
    @FXML
    private BorderPane Border;
    /*
    @FXML
    VBox modify;
    */
    /*
    @FXML
    private StackPane parentContainer;
    */
    @FXML
    private TextField filterTextField;
    
    @FXML
    private VBox VBoxTest;   
            
    @FXML
    private VBox VBoxFormular;
    
    @FXML
    private TableColumn<Utilisateur,String> col_nom;

    @FXML
    private TableColumn<Utilisateur,String> col_user;

    @FXML
    private TableColumn<Utilisateur,String> col_password;

    @FXML
    private TableColumn<Utilisateur,String> col_type;
    
    private ObservableList<Utilisateur> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
   
        
        
        col_nom.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
        col_user.setCellValueFactory( new PropertyValueFactory<Utilisateur,String>("user") );
        col_password.setCellValueFactory( new PropertyValueFactory<Utilisateur,String>("password") );
        col_type.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("type"));
        
        
        
        
        
        
        table.setItems(loadData());
        table.setPrefWidth(1800);
        this.Search();
        
        //table.setPrefWidth(800);
    }
    
    public ObservableList<Utilisateur> loadData()
    {
        this.data =FXCollections.observableArrayList();
         
         data.add(new Utilisateur("nnane","Client","!mkdfkdjf","ADMIN"));
         data.add(new Utilisateur("test","Client","!mkdfkdjf","invite"));
         data.add(new Utilisateur("mouen","Client"," ","user"));
         
         
         
         return data;
        
    }
    
       @FXML
    void buttonAjouterUtilisateur(ActionEvent event) {
        
        
       /* Label nom=new Label("nom");
        Label prenom=new Label("prenom");
        
        JFXTextField textNom=new JFXTextField();
        JFXTextField textPrenom=new JFXTextField();
        System.out.println("before array");
        
        ArrayList<Node> list=new ArrayList<Node>();
        list.add(nom);
        list.add(textNom);
        list.add(prenom);
        list.add(textPrenom);
        

        Popup.show("Confirmation",f);*/
       /* System.out.println("click");
        Formulaire f=new Formulaire("ok","Cancel","elevage.png",600,500);
        
        Label nom=new Label("nom");
        Label prenom=new Label("prenom");
        
        JFXTextField textNom=new JFXTextField();
        JFXTextField textPrenom=new JFXTextField();
        System.out.println("before array");
        
        ArrayList<Node> list=new ArrayList<Node>();
        list.add(nom);
        list.add(textNom);
        list.add(prenom);
        list.add(textPrenom);
        
        f.addElement(list);

        ParentHbox.getChildren().add(f);
        //ParentHbox.setSpacing(0.5);

        //table.setPrefWidth(250);
        TranslateTransition slide=new TranslateTransition();
        slide.setDuration(Duration.seconds(2));
        slide.setNode(f);
        //slide.get
        //VBoxTest.setPrefWidth(VBoxTest.getPrefWidth() - f.getPrefWidth()-10);
        slide.setToX(-50);
        slide.play();
        
        f.setTranslateY(-5);
        */
    }
     @FXML
    void Search() {

        this.loadData();
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Utilisateur> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        filterTextField.textProperty().
        addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(utilisateur -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (utilisateur.getUser().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (utilisateur.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (utilisateur.getPassword().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (utilisateur.getType().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }
        
    


    /*
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
    }*/


    @Override
    public Map<Node, List<userType>> getNodeRoles() {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
