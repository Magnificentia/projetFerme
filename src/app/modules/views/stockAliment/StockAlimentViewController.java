package app.modules.views.stockAliment;



import app.Projet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;
import app.modules.database.DbManager;
import app.modules.model.Aliment;
import app.modules.model.Bande;
import app.modules.model.Fournisseur;
import app.modules.model.Medicament;
import app.modules.model.StockAliment;

import app.modules.userType;
import app.modules.views.BaseView;
import app.modules.views.Form;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//putain
public class StockAlimentViewController extends BaseView<StockAliment> implements Initializable, IController {

    @FXML
    private TextField recherche;
    

    
    @FXML
    private AnchorPane anchor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createTable();
        anchor.getChildren().add(item);
        this.Search();
        populateTableRation();
        table.setPrefWidth(800);
    }
    public void loadData()
    {
        data=FXCollections.observableArrayList(DbManager.selectStockAliment());
    }
    
    public void createTable()
    {
        table.setPrefWidth(800);
        
        TableColumn<StockAliment,String> col_nom=new TableColumn<>("designation");
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomStock"));
        
        TableColumn<StockAliment,String> col_aliment=new TableColumn<>("aliment");
        col_aliment.setCellValueFactory(new PropertyValueFactory<>("nomAli"));
        
        TableColumn<StockAliment,Integer> col_qte=new TableColumn<>("quantité");
        col_qte.setCellValueFactory(new PropertyValueFactory<>("qte"));//voir comment ajouter une liste de numeros
        
        TableColumn<StockAliment,Integer> col_date=new TableColumn<>("date d'arrivage");
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateArrivage"));
        
        TableColumn<StockAliment,Integer> col_fourn=new TableColumn<>("fournisseur");
        col_fourn.setCellValueFactory(new PropertyValueFactory<>("nomFournisseur"));

        table.getColumns().addAll(col_nom,col_aliment,col_date,col_fourn,col_qte);
    }
    
    public void populateTableRation()
    {
        ObservableList<StockAliment> liste=FXCollections.observableArrayList(DbManager.selectStockAliment());
        table.setItems(liste);
    }
    
    @FXML
    public void handleDelete(ActionEvent event) throws SQLException {

        StockAliment mat=table.getSelectionModel().getSelectedItem();

        if (mat!=null) {
            if(Projet.showAlert(Alert.AlertType.CONFIRMATION, null, "Form Error!",
                "voulez-vous supprimer cet utilisateur?"))
            {
                System.out.println("suppression");
                DbManager.suppStockAliment(mat);
                populateTableRation();
            }
            return;
        }
    }
    
        @FXML
    void Search() {

        data=FXCollections.observableArrayList(DbManager.selectStockAliment());
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<StockAliment> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        recherche.textProperty().
        addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(utilisateur -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (utilisateur.getNomAli().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (utilisateur.getNomFournisseur().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (utilisateur.getNomStock().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (Integer.toString(utilisateur.getQte()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<StockAliment> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }
    
    @FXML
    public void showAddStockAlimentrWindow(ActionEvent event) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("options/addStockAlimentOptions.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            //dialogStage.getIcons().add(new Image("file:resources/images/icon2.jpg"));
            dialogStage.setTitle("Ajouter un nouveau stock");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(Projet.getMainStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            populateTableRation();

            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    public void onAjouterclicked(ActionEvent event)
    {
        FormStockAliment form=new FormStockAliment(null);
        form.show();
        updateData();
    }
        public void updateTableBande()
    {
        table.getItems().clear();//importaant sinon les changements ne vont pas être éffectués lorsqu'on fait de simples modificatioobs
        data=FXCollections.observableArrayList(DbManager.selectStockAliment());
        table.setItems(data);
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


class FormStockAliment extends Form
{
    private StockAliment stock;
    private final JFXDatePicker date;
    private final JFXTextField designation;
    private final JFXTextField quantite;
    private final JFXComboBox<Aliment> aliment;
    private final JFXComboBox<Fournisseur> fournisseur;
    
    public FormStockAliment(StockAliment stock)
    {
        this.stock=stock;
        designation=new JFXTextField();
        designation.setPromptText("designation");
        
        quantite=new JFXTextField();
        quantite.setPromptText("quantite");
        
        aliment=new JFXComboBox<>();
        aliment.setPromptText("aliment");
        aliment.setItems(FXCollections.observableArrayList(DbManager.selectAliments()));
        
        fournisseur=new JFXComboBox<>();
        fournisseur.setPromptText("fournisseur");
        fournisseur.setItems(FXCollections.observableArrayList(DbManager.selectFournisseurs()));
        
        date=new JFXDatePicker();
        
        List a=new ArrayList();
        a.add(designation);
        a.add(aliment);
        a.add(quantite);
        a.add(date);
        a.add(fournisseur);
        addFields(a);
        if(stock!=null)
        {
            populate(stock);
        }
    }
    
    private void populate(StockAliment stock)
    {
        designation.setText(stock.getNomStock());
        quantite.setText(new Double(stock.getQte()).toString());
        //date.setText(stock.getDateArrivage());
        fournisseur.getSelectionModel().select(new Fournisseur(this.stock.getFourn_id()));
        aliment.getSelectionModel().select(new Aliment(this.stock.getAli_id()));
    }
    

    @Override
    public void onValidateClick() {
        System.out.println("validate clicked");
        if(this.stock==null)
        {    
            System.out.println("stockaliment");
            String designation=this.designation.getText();
            int idaliment=this.aliment.getSelectionModel().getSelectedItem().getIdali();
            int idfournisseur=this.fournisseur.getSelectionModel().getSelectedItem().getIdfourn();
            int qte=new Integer(this.quantite.getText());
            StockAliment stock=new StockAliment(qte,Bande.DEFAULT_DATE,idaliment,idfournisseur);
            //save stock
            if(DbManager.saveStockAliment(stock))
                System.out.println("stock saved");
        }
    }
}