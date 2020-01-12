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
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.WindowEvent;

public class Main extends Application {
    public userType typeUser=userType._ADMIN_;
    public  double pref_width=1500;
    public  double pref_height=900;
    

    @Override
    public void start(Stage primaryStage) throws Exception{
        
        //pas important d'essayer de comprendre
        //ca sert � s'assurer que l'appli se ferme convenablement
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
        IOption statsElevage=new OptionItem("statistiques","views/goodnight/goodnight.fxml");
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


        
        List elevage=new ArrayList<>();//options menu elevage
        elevage.add(bandes);
        //elevage.add(option2);
        elevage.add(ration);
        //elevage.add(incubation);  
        elevage.add(aliment);
        elevage.add(statsElevage);
        elevage.add(stockAliment);
        elevage.add(collecteoeuf);
               

        List vente=new ArrayList<>();//options menu vente
        vente.add(statsVente);
        vente.add(fournissseurs);
        
        
        List sante=new ArrayList<>();//options menu veterinaire
        sante.add(bandeVaccine);
        sante.add(vaccins);
        sante.add(test);
        
        List utilisateur=new ArrayList<>();
        utilisateur.add(employes);
        utilisateur.add(incubation);
        

        IMenu elevageMenu=new MenuItem("Elevage","ressources/elevage.png",elevage);//liste des menus
        IMenu fournisseurMenu=new MenuItem("Fournisseur","ressources/fournisseur.png",vente);
        IMenu accueilMenu=new MenuItem("Accueil" ,"ressources/white-home.png",null);
        IMenu statistiquesMenu=new MenuItem("Statistiques","ressources/statistiques.png",null);
        IMenu santeMenu=new MenuItem("Sant�","ressources/statistiques.png",sante);
        IMenu utilisateurMenu=new MenuItem("Utilisateur","ressources/statistiques.png",utilisateur);
        List menus=new ArrayList<>();
        menus.add(accueilMenu);
        menus.add(elevageMenu);
        menus.add(fournisseurMenu);
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
}
