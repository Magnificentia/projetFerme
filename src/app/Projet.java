package app;

import app.modules.IMenu;
import app.modules.IOption;
import app.modules.MainItem;
import app.modules.MenuItem;
import app.modules.OptionItem;
import app.modules.model.Employes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.scene.control.agenda.*;

import java.util.ArrayList;
import java.util.List;
import app.modules.userType;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class Projet extends Application {
    public userType typeUser=userType._ADMIN_;
    public  static double pref_width=1500;
    public  static double pref_height=900;
    
    private static Stage mainStage;

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage mainStage) {
        Projet.mainStage = mainStage;
    }
    
    
    private static Employes emp;

    public static Employes getEmp() {
        return emp;
    }

    public static void setEmp(Employes emp) {
        Projet.emp = emp;
    }


    
    

    @Override
    public void start(Stage primaryStage) throws Exception{
        
        //pas important d'essayer de comprendre
        //ca sert à s'assurer que l'appli se ferme convenablement
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        mainStage=primaryStage;
        
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        loadLogin();
        //

        primaryStage.show();
    }

    public static void loadLogin() throws IOException
    {
        
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        Parent root = FXMLLoader.load(Projet.class.getResource("modules/views/login/Login.fxml"));
        Scene scene=new Scene(root,500,600);
        mainStage.setScene(scene);
        mainStage.setHeight(800);
        mainStage.setWidth(850);
        
        //scene.getStylesheets().add(MainItem.class.getResource("views/global.css").toExternalForm());
        
        
    }
    public static void loadApplication(Employes employe) throws IOException
    {
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        emp=employe;

        IOption ration=new OptionItem("Ration","views/ration/rationView.fxml");
        IOption employes=new OptionItem("utilisateurs","views/employes/employesView.fxml");
        
        IOption usersgroups=new OptionItem("Groupes","views/groupes/groupesView.fxml");
        IOption calendrier=new OptionItem("Calendrier","views/taches/taskView.fxml");
        IOption enregisterfacture=new OptionItem("Facture","views/facture/factureView.fxml");
        
        IOption bandes=new OptionItem("Bande","views/bande/bandeView.fxml");
        IOption medicaments=new OptionItem("Medicaments","views/medicaments/medicView.fxml");
        IOption stockAliment=new OptionItem("Stock Aliment","views/stockAliment/stockAlimentView.fxml");
        IOption fournissseurs=new OptionItem("Fournisseur","views/fournisseur/fournisseurView.fxml");
        IOption collecteoeuf=new OptionItem("Collecte Oeuf","views/collecteoeuf/collecteOeufView.fxml");
        IOption statsElevage=new OptionItem("Statitistiques","views/statselevage/statsElevageView.fxml");
        IOption accueil=new OptionItem("Accueil","views/accueil/accueilView.fxml");
        IOption client=new OptionItem("Client","views/client/clientView.fxml");
        IOption catalogue=new OptionItem("Catalogue","views/catalogue/catalogueView.fxml");
        IOption commande=new OptionItem("commande","views/commande/commandeView.fxml");
        IOption parametres=new OptionItem("commande","views/parametres/parametresView.fxml");
        IOption infoselevage=new OptionItem("infos elevage","views/infosElevage/eleveurView.fxml");
        IOption infoscommercial=new OptionItem("commande","views/infosCommercial/commercialView.fxml");
        
        List elevage=new ArrayList<>();//options menu elevage
        elevage.add(infoselevage);
        elevage.add(bandes);
        elevage.add(ration);
        elevage.add(collecteoeuf);
   
        List magasin=new ArrayList<>();
        magasin.add(infoscommercial);
        magasin.add(medicaments);
        
        magasin.add(stockAliment);
        
        List tiers=new ArrayList<>();//options menu vente
        tiers.add(fournissseurs);
        tiers.add(client);
        
        
        List utilisateur=new ArrayList<>();
        utilisateur.add(employes);
        //utilisateur.add(usersgroups);
        
        
        List statistiques=new ArrayList<>();
        statistiques.add(statsElevage);

        
        List ventes=new ArrayList<>();
        ventes.add(catalogue);
        ventes.add(enregisterfacture);
        ventes.add(commande);
        
        
        IMenu elevageMenu=new MenuItem("Elevage","ressources/elevage.png",elevage);//liste des menus
        IMenu tiersMenu=new MenuItem("Tiers","ressources/fournisseur.png",tiers);

        IMenu accueilMenu=new MenuItem("Accueil" ,"ressources/home.png",accueil);
        IMenu statistiquesMenu=new MenuItem("Statistiques","ressources/stats.png",statistiques);

        IMenu calendrierMenu=new MenuItem("Calendrier","ressources/health.png",calendrier);
        IMenu parametresMenu=new MenuItem("paramètres","ressources/health.png",parametres);
        IMenu utilisateurMenu=new MenuItem("Utilisateur","ressources/icons8-utilisateur-masculin-240.png",utilisateur);
        IMenu venteMenu=new MenuItem("Vente","ressources/panier.png",ventes);
        IMenu magasinMenu=new MenuItem("Magasin","ressources/entrepot.png",magasin);


        List menus=new ArrayList<>();
        menus.add(accueilMenu);
        menus.add(calendrierMenu);
        menus.add(elevageMenu);
        menus.add(venteMenu);
        menus.add(magasinMenu);
        menus.add(tiersMenu);
        
        menus.add(statistiquesMenu);
        
        
        menus.add(utilisateurMenu);
        menus.add(parametresMenu);

        MainItem main=new MainItem(menus);
        main.setHeader("views/globalview/header/header.fxml");

        mainStage.setTitle("Hello World");
        Scene scene=new Scene(main.getItem());
        scene.getStylesheets().add(MainItem.class.getResource("views/global.css").toExternalForm());
        mainStage.setScene(scene);
        mainStage.setMinHeight(pref_height);
        //primaryStage.setMaxWidth(pref_width);
        mainStage.setMinWidth(pref_width);
        //primaryStage.setMaxHeight(pref_height);
        mainStage.setResizable(true);
    }
    public static void main(String[] args) {
        
        launch(args);
    }
    
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public static boolean showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        //alert.show();
        Optional<ButtonType> result = alert.showAndWait();
        return (result.get() == ButtonType.OK);
    }
}
