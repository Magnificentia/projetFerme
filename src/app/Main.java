package app;

import app.modules.IMenu;
import app.modules.IOption;
import app.modules.MainItem;
import app.modules.MenuItem;
import app.modules.OptionItem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import app.modules.userType;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class Main extends Application {
    public userType typeUser=userType._ADMIN_;
    public  double pref_width=1500;
    public  double pref_height=900;
    
    
    private static Object tampon;

    public Object getTampon() {
        return tampon;
    }

    public void setTampon(Object tampon) {
        this.tampon = tampon;
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
        //
        Parent root = FXMLLoader.load(getClass().getResource("modules/views/globalview/main/sample.fxml"));

        //ParentTest root=new ParentTest();
        //IOption option=new OptionItem("oeufs","views/bonjour/Test.fxml");
        IOption test=new OptionItem("poulets","views/bonjour/Test.fxml");
        IOption statsVente=new OptionItem("statistiques","views/goodnight/goodnight.fxml");
        //IOption statsElevage=new OptionItem("statistiques","views/goodnight/goodnight.fxml");
        IOption aliment=new OptionItem("Aliment","views/aliment/AlimentView.fxml");
        IOption bandeVaccine=new OptionItem("bande vaccine","views/bandevaccine/bandeVaccineView.fxml");
        IOption ration=new OptionItem("Ration","views/ration/rationView.fxml");
        IOption incubation=new OptionItem("Incubation","views/incubation/incubationView.fxml");
        IOption employes=new OptionItem("Employes","views/employes/employesView.fxml");
        IOption bandes=new OptionItem("Bande","views/bande/bandeView.fxml");
        IOption vaccins=new OptionItem("Vaccin","views/vaccin/vaccinView.fxml");
        IOption stockAliment=new OptionItem("Stock Aliment","views/stockAliment/stockAlimentView.fxml");
        IOption fournissseurs=new OptionItem("Fournisseur","views/fournisseur/fournisseurView.fxml");
        IOption collecteoeuf=new OptionItem("Collecte Oeuf","views/collecteoeuf/collecteOeufView.fxml");
        IOption statsElevage=new OptionItem("Elevage","views/statselevage/statsElevageView.fxml");
        IOption accueil=new OptionItem("Accueil","views/accueil/accueilView.fxml");
        IOption client=new OptionItem("Client","views/client/clientView.fxml");
        IOption venteOeuf=new OptionItem("Vente des Oeufs","views/vente/venteView.fxml");

        
        List elevage=new ArrayList<>();//options menu elevage
        elevage.add(bandes);
        //elevage.add(option2);
        elevage.add(ration);
        //elevage.add(incubation);  
        elevage.add(aliment);
        //elevage.add(statsElevage);
        elevage.add(stockAliment);
        elevage.add(collecteoeuf);
               

        List tiers=new ArrayList<>();//options menu vente
        //vente.add(statsVente);
        tiers.add(fournissseurs);
        tiers.add(client);
        
        List sante=new ArrayList<>();//options menu veterinaire
        sante.add(bandeVaccine);
        sante.add(vaccins);
        sante.add(test);
        
        List utilisateur=new ArrayList<>();
        utilisateur.add(employes);
        utilisateur.add(incubation);
        
        List statistiques=new ArrayList<>();
        statistiques.add(statsElevage);

        
        List ventes=new ArrayList<>();
        ventes.add(venteOeuf);
        
        
        IMenu elevageMenu=new MenuItem("Elevage","ressources/elevage.png",elevage);//liste des menus
        IMenu tiersMenu=new MenuItem("Tiers","ressources/fournisseur.png",tiers);
        IMenu accueilMenu=new MenuItem("Accueil" ,"ressources/white-home.png",accueil,1);
        IMenu statistiquesMenu=new MenuItem("Statistiques","ressources/statistiques.png",statistiques);
        IMenu santeMenu=new MenuItem("Santé","ressources/statistiques.png",sante);
        IMenu utilisateurMenu=new MenuItem("Utilisateur","ressources/statistiques.png",utilisateur);
        IMenu venteMenu=new MenuItem("Vente","ressources/statistiques.png",ventes);

        List menus=new ArrayList<>();
        menus.add(accueilMenu);
        menus.add(elevageMenu);
        menus.add(venteMenu);
        menus.add(tiersMenu);
        menus.add(santeMenu);
        menus.add(statistiquesMenu);
        
        menus.add(utilisateurMenu);

        MainItem main=new MainItem(menus);
        main.setHeader("views/globalview/header/header.fxml");

        primaryStage.setTitle("Hello World");
        Scene scene=new Scene(main.getItem());
        scene.getStylesheets().add(MainItem.class.getResource("views/global.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResource("modules/ressources/chicken.png").toString()));
        //primaryStage.setMaxHeight(pref_height);
        primaryStage.setMinHeight(pref_height);
        //primaryStage.setMaxWidth(pref_width);
        primaryStage.setMinWidth(pref_width);
        primaryStage.show();
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
