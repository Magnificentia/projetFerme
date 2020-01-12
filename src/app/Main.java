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
    public  double pref_width=1400;
    public  double pref_height=800;
    

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
        IOption option=new OptionItem("oeufs","views/bonjour/Test.fxml");
        IOption option2=new OptionItem("poulets","views/bonjour/Test.fxml");
        IOption statsVente=new OptionItem("statistiques","views/goodnight/goodnight.fxml");
        IOption statsElevage=new OptionItem("statistiques","views/goodnight/goodnight.fxml");
        IOption aliment=new OptionItem("Aliment","views/aliment/AlimentView.fxml");

        List elevage=new ArrayList<>();//premier menu
        elevage.add(option);
        elevage.add(option2);
        
        elevage.add(aliment);
        elevage.add(statsElevage);

        List vente=new ArrayList<>();//deuxieme menu
        vente.add(statsVente);
        

        IMenu elevageMenu=new MenuItem("Elevage","ressources/elevage.png",elevage);//liste des menus
        IMenu fournisseurMenu=new MenuItem("Fournisseur","ressources/fournisseur.png",vente);
        IMenu accueilMenu=new MenuItem("Accueil" ,"ressources/white-home.png",vente);
        IMenu menu4=new MenuItem("Statistiques","ressources/statistiques.png",vente);
        List menus=new ArrayList<>();
        menus.add(accueilMenu);
        menus.add(elevageMenu);
        menus.add(fournisseurMenu);
        menus.add(menu4);

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
