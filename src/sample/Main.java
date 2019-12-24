package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.views.*;


import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public userType typeUser=userType._ADMIN_;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        //ParentTest root=new ParentTest();
        IOption option=new OptionItem("oeufs","test\\bonjour\\Test.fxml");
        IOption option2=new OptionItem("poulets","test\\bonjour\\Test.fxml");
        IOption option3=new OptionItem("factures","test\\goodnight\\goodnight.fxml");
        IOption option4=new OptionItem("autre chose","test\\goodnight\\goodnight.fxml");
        IOption option5=new OptionItem("je sais pas trop","test\\goodnight\\goodnight.fxml");

        List options=new ArrayList<IOption>();//premier menu
        options.add(option);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        options.add(option5);

        List options2=new ArrayList<IOption>();//deuxieme menu
        options2.add(option3);

        IMenu menu=new MenuItem("elevage",options);//liste des menus
        IMenu menu2=new MenuItem("fournisseur",options2);
        IMenu menu3=new MenuItem("Accueil" ,options2);
        IMenu menu4=new MenuItem("statistiques",options2);
        List menus=new ArrayList<IMenu>();
        menus.add(menu3);
        menus.add(menu);
        menus.add(menu2);
        menus.add(menu4);




        MainItem main=new MainItem(menus);
        main.setHeader("globalview\\header.fxml");
        //Parent root2 = FXMLLoader.load(getClass().getResource("hd.fxml"));
        main.setFooter("globalview\\footer.fxml");
        //main.getItem().setDisable(true);

        primaryStage.setTitle("Hello World");
        Scene scene=new Scene(main.getItem(), 300, 275);
        scene.getStylesheets().add(MainItem.class.getResource("global.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
