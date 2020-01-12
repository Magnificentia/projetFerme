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
        
        /*String nom="mou897n";
        String user="moun00";
        String login="mou8en"; 
        String typeEm="f";
        Employes emp=new Employes(190, nom, user, login, typeEm);
        //Employes emp2=new Employes(9, nom, user, login, typeEm);
        DAO<Employes> empl=new DAO(Employes.class);
        empl.persist(emp);
        System.out.println(empl.findAll());
        //System.out.println(empl.findAll());
        System.err.println("");
        //empl.persist(emp2);
        empl.closeCurrentSessionwithTransaction();
        ///
        
 
*/
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
        IOption option3=new OptionItem("factures","views/goodnight/goodnight.fxml");
        IOption option4=new OptionItem("autre chose","views/goodnight/goodnight.fxml");
        IOption option5=new OptionItem("je sais pas trop","views/goodnight/goodnight.fxml");

        List options=new ArrayList<>();//premier menu
        options.add(option);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        options.add(option5);

        List options2=new ArrayList<>();//deuxieme menu
        options2.add(option3);

        IMenu menu=new MenuItem("Elevage","ressources/elevage.png",options);//liste des menus
        IMenu menu2=new MenuItem("Fournisseur","ressources/fournisseur.png",options2);
        IMenu menu3=new MenuItem("Accueil" ,"ressources/white-home.png",options2);
        IMenu menu4=new MenuItem("Statistiques","ressources/statistiques.png",options2);
        List menus=new ArrayList<>();
        menus.add(menu3);
        menus.add(menu);
        menus.add(menu2);
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
