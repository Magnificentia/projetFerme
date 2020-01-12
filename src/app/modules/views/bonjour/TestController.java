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

//putain
public class TestController implements Initializable, IController {
    @FXML
    TableView<Utilisateur> table;

    @FXML
    private AnchorPane root;
    
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
    private VBox hboxTest;    
            
    
    @FXML
    private TableColumn<Utilisateur,String> col_nom;

    @FXML
    private TableColumn<Utilisateur,String> col_user;

    @FXML
    private TableColumn<Utilisateur,String> col_password;

    @FXML
    private TableColumn<Utilisateur,String> col_type;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
   
        
        
        col_nom.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
        col_user.setCellValueFactory( new PropertyValueFactory<Utilisateur,String>("user") );
        col_password.setCellValueFactory( new PropertyValueFactory<Utilisateur,String>("password") );
        col_type.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("type"));
        
        
        
        
        table.setItems(loadData());
        
        //table.setPrefWidth(800);
    }
    
    public ObservableList<Utilisateur> loadData()
    {
         ObservableList<Utilisateur> data =FXCollections.observableArrayList();
         
         data.add(new Utilisateur("nnane","Client","!mkdfkdjf","ADMIN"));
         data.add(new Utilisateur("test","Client","!mkdfkdjf","invite"));
         data.add(new Utilisateur("mouen","Client"," ","user"));
         
         
         
         return data;
        
    }
    
       @FXML
    void buttonAjouterUtilisateur(ActionEvent event) {
       
        
       /* Scene scene=table.getScene();
        
        
        parentContainer.translateYProperty().set(scene.getWidth());
        //root.getChildren().add(parentContainer);
        
        /*Timeline timeline=new Timeline();
        KeyValue kv=new KeyValue(parentContainer.translateYProperty(),0, Interpolator.EASE_IN);
        KeyFrame kf;
        kf = new KeyFrame(Duration.seconds(1),kv);
        timeline.getKeyFrames().add(kf);
           System.out.println("sliiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiide");
        timeline.play();*/
       
       
       //Border.getCenter().se;
        System.out.println("click");
        Formulaire f=new Formulaire("ok","Cancel","/app/Ressources/elevage.png",250,500);
        
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
        Border.setRight(f);
        
        hboxTest.setAlignment(Pos.CENTER_LEFT);
        
       // table.setPrefWidth(250);
        TranslateTransition slide=new TranslateTransition();
        slide.setDuration(Duration.seconds(1));
        slide.setNode(f);
        hboxTest.setPrefWidth(hboxTest.getPrefWidth()-f.getPrefWidth()-55);
        slide.setToX(-100);
        slide.play();
        
        f.setTranslateX(0);
        
//slide.setOnFinished(e->)
        
        //parentContainer.setTranslateX(0);
          
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
