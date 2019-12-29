package app;

import app.modules.IMenu;
import app.modules.IOption;
import app.modules.MainItem;
import app.modules.MenuItem;
import app.modules.OptionItem;
import app.modules.model.DbManager;
import app.modules.model.Employes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import app.modules.userType;

public class Main extends Application {
    public userType typeUser=userType._ADMIN_;

    @Override
    public void start(Stage primaryStage) throws Exception{
        
        
       String nom="mouen";
       String user="mouen";
       String login="mouen"; 
       String typeEm="f";
       Employes emp=new Employes(5, nom, user, login, typeEm);
       Employes emp2=new Employes(7, nom, user, login, typeEm);
       if (DbManager.create(emp) && DbManager.create(emp2))
       {
           System.out.println("Employee successfully added");
       }
       else
       {
           System.err.println("Error when registering employee");
       }
                        
        ///
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

        IMenu menu=new MenuItem("Elevage","ressources/icons8-chicken-32.png",options);//liste des menus
        IMenu menu2=new MenuItem("Fournisseur","ressources/image.png",options2);
        IMenu menu3=new MenuItem("Accueil" ,"ressources/icons8-home-32.png",options2);
        IMenu menu4=new MenuItem("Statistiques","ressources/progress.png",options2);
        List menus=new ArrayList<>();
        menus.add(menu3);
        menus.add(menu);
        menus.add(menu2);
        menus.add(menu4);




        MainItem main=new MainItem(menus);
        main.setHeader("views/globalview/header/header.fxml");
        //Parent root2 = FXMLLoader.load(getClass().getResource("hd.fxml"));
        main.setFooter("views/globalview/footer/footer.fxml");
        //main.getItem().setDisable(true);

        primaryStage.setTitle("Hello World");
        Scene scene=new Scene(main.getItem(), 1400, 905);
        scene.getStylesheets().add(MainItem.class.getResource("views/global.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
