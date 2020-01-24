package app.modules.views.bande;



import app.Projet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import app.modules.IController;
import app.modules.database.DbManagerNnane;
import app.modules.model.Bande;

import app.modules.model.StockAliment;

import app.modules.userType;
import app.modules.views.Form;
//import app.modules.views.bonjour.Formulaire;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;


//putain
public class BandeViewController implements Initializable, IController {

    

    private TableView<Bande> table;



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
    private int rowsPerPage=3;
    @FXML
    private AnchorPane AnchorTable;
    
    ObservableList<Bande> data;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table=new TableView<>();
        createTable();
        data=FXCollections.observableArrayList(DbManagerNnane.selectBandes());
        Pagination pagination = new Pagination((data.size() / rowsPerPage + 1), 0);
        //pagination.setPageFactory(this::createPage);
	

        pagination.currentPageIndexProperty().addListener(
                new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("pagination changed");
                createPage(newValue.intValue());
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        populateTableBande();
        VBox v=new VBox();v.getChildren().addAll(table,pagination);
        AnchorTable.getChildren().addAll(v);
        
        

        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("menu");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Menu 2");
        cm.getItems().add(mi2);

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.SECONDARY) {
                    cm.show(table, t.getScreenX(), t.getScreenY());
                }
            }
   });
        
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
        
        TableColumn<Bande,Integer> col_quantite=new TableColumn<>("quantité");
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
        
        TableColumn<Bande,String> col_batiment=new TableColumn<>("batiment");
        col_batiment.setCellValueFactory(new PropertyValueFactory<>("nomBatiment"));
        
        TableColumn<Bande,String> col_fournisseur=new TableColumn<>("fournisseur");
        col_fournisseur.setCellValueFactory(new PropertyValueFactory<>("nomFournisseur"));
        
        TableColumn<Bande,String> col_race=new TableColumn<>("race"); 
        col_race.setCellValueFactory(new PropertyValueFactory<>("nomRace"));
        
        table.getColumns().addAll(col_nom,col_achat,col_age,col_date,col_quantite,col_batiment,col_fournisseur,col_race);
    }
    private void createPage(int pageIndex) {

        table.setPrefWidth(800);
        System.out.println("page index = "+pageIndex);
        int fromIndex = (pageIndex) * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, data.size()-1);
        //System.out.println("subdata from "+fromIndex+" to "+toIndex+" = "+data.subList(fromIndex, toIndex));
        //table.getItems().clear();
        if (fromIndex<=toIndex)table.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));

        //return new AnchorPane(table);
    }

    public void populateTableBande()
    {
        table.getItems().clear();//importaant sinon les changements ne vont pas être éffectués lorsqu'on fait de simples modificatioobs
        data=FXCollections.observableArrayList(DbManagerNnane.selectBandes());
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
                DbManagerNnane.suppBande(mat);
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
    
    

}

class FormBande extends Form
{
    //voici un exemple création de formulaire

    public FormBande() {
        //on spécifie les différents champs
        super();
        List<Node> liste=new ArrayList();
        liste.add(new JFXTextField());
        JFXComboBox fournisseur=new JFXComboBox();
        fournisseur.setItems(FXCollections.observableArrayList(DbManagerNnane.selectFournisseurs()));
        liste.add(fournisseur);
        this.addFields(liste);
    }    

    @Override
    public void onValidateClick() {
        //on spécifie l'action à faire pour chaque bouton
        System.out.println("appel de la bonne fonction");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}