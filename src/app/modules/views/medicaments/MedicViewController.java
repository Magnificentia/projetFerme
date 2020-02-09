package app.modules.views.medicaments;




import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;
import app.modules.database.DbManager;
import app.modules.model.Bande;
import app.modules.model.Medicament;

import app.modules.userType;
import app.modules.views.BaseView;
import app.modules.views.Form;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
//putain
public class MedicViewController extends BaseView<Medicament> implements Initializable, IController {
    
    @FXML
    private TextField rechercher;
    
    @FXML
    private HBox anchor;//Mouen a change ca en HBOX et a changer ces caracteristiques directement dans le fichier FXML
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        createTable();
        anchor.getChildren().add(item);
        table.setPrefWidth(800);
        
        
        this.Search();
        
    }
    
    public void loadData()
    {
        data=FXCollections.observableArrayList(DbManager.selectMedicaments());
    }
    
    public void createTable()
    {
        table.setPrefWidth(800);
        
        TableColumn<Medicament,String> col_nom=new TableColumn<>("designation");
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomFourn"));
        col_nom.setPrefWidth(250);
        
        TableColumn<Medicament,String> col_prix=new TableColumn<>("prix");
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_prix.setPrefWidth(250);
        
        TableColumn<Medicament,Integer> col_duree=new TableColumn<>("durée");
        col_duree.setCellValueFactory(new PropertyValueFactory<>("periode"));
        col_duree.setPrefWidth(250);
        
        
        TableColumn<Medicament,Integer> col_desc=new TableColumn<>("description");
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));//voir comment ajouter une liste de numeros
        col_desc.setPrefWidth(430);

        table.getColumns().addAll(col_nom,col_prix,col_duree,col_desc);
    }
    @FXML
    void Search() {

        data=FXCollections.observableArrayList(DbManager.selectMedicaments());
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Medicament> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        rechercher.textProperty().
        addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(vacc -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (vacc.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } 
                else if (Integer.toString((int) vacc.getPrix()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                

                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Medicament> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }

    @FXML
    public void onAjouterclicked(ActionEvent event)
    {
        FormMedicament form=new FormMedicament(null);
        form.show();
        updateData();
    }
    

    public void populateTableVaccin()
    {
        ObservableList<Medicament> liste=FXCollections.observableArrayList(DbManager.selectMedicaments());
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
}


class FormMedicament extends Form
{
    private Medicament medic;
    private final JFXTextField description;
    private final JFXTextField designation;
    private final JFXTextField prix;
    private final JFXTextField duree;
    
    public FormMedicament(Medicament medic)
    {
        this.medic=medic;
        
        designation=new JFXTextField();
        designation.setPromptText("designation");
        HBox hdesignation=new HBox();
        hdesignation.getChildren().add(designation);
        hdesignation.setPrefWidth(739);
        hdesignation.setPrefHeight(75);
        hdesignation.setAlignment(Pos.CENTER);
        hdesignation.setStyle("-fx-background-color:#f8f8ff");
        
        prix=new JFXTextField();
        prix.setPromptText("prix");
        HBox hprix=new HBox();
        hprix.getChildren().add(prix);
        hprix.setPrefWidth(739);
        hprix.setPrefHeight(75);
        hprix.setAlignment(Pos.CENTER);
        hprix.setStyle("-fx-background-color:#f8f8ff");
        
        duree=new JFXTextField();
        duree.setPromptText("duree");
        HBox hduree=new HBox();
        hduree.getChildren().add(duree);
        hduree.setPrefWidth(739);
        hduree.setPrefHeight(75);
        hduree.setAlignment(Pos.CENTER);
        hduree.setStyle("-fx-background-color:#f8f8ff");
        
        description=new JFXTextField();
        description.setPromptText("description");
        HBox hdescription=new HBox();
        hdescription.getChildren().add(description);
        hdescription.setPrefWidth(739);
        hdescription.setPrefHeight(195);
        hdescription.setAlignment(Pos.TOP_CENTER);
        hdescription.setStyle("-fx-background-color:#f8f8ff");
        hdescription.setMargin(description,new Insets(20,0,0,0));
        
        List a=new ArrayList();
        a.add(hdesignation);
        a.add(hprix);
        a.add(hduree);
        a.add(hdescription);
        //a.setMargin(hdescription,new Insets(30,0,0,0));
        addFields(a);
        if(medic!=null)
        {
            populate(medic);
        }
    }
    
    private void populate(Medicament medic)
    {
        //assez inutile
        designation.setText(medic.getDescription());
        prix.setText(new Double(medic.getPrix()).toString());
        duree.setText(medic.getPeriode().toString());
        description.setText(medic.getDescription());
    }
    

    @Override
    public void onValidateClick() {
        System.out.println("validate clicked");
      
        String designation=this.designation.getText();
        double duree=new Double(this.duree.getText());
        double prix=new Double(this.prix.getText());
        String description=this.description.getText();
        //save medic
        this.medic=new Medicament(designation,Bande.DEFAULT_DATE,description,prix);
        System.out.println("save medicament");
        if(DbManager.saveMedicaments(medic))
            System.out.println("enregistrement réussi");

        //this.medic.setDescription(description);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}