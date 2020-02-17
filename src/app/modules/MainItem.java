package app.modules;


import app.Projet;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeView;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainItem extends Item {
    private ArrayList<IMenu> menusList;
    private Parent header=new HBox();
    private Parent footer=new HBox();

    private HBox menus;
    private Pane container;
    private Pane view;

    
    public static double _FOOTER_WIDTH_=30;
    public static double _HEADER_HEIGHT_=30;
    
    private GridPane gridpane;
    
    private static ImageView logo;


    public MainItem(String name,List menus)
    {
        super(name);
       
        
        gridpane=new GridPane();//pour la mise en page
        gridpane.getColumnConstraints().add(new ColumnConstraints(210));//specifie la longueur (horizontalement) de l'espace pour menus et icone
        gridpane.getRowConstraints().add(new RowConstraints(55));//specifie la largeur (verticalement) de l'espace pour le header
        
        createHeader();
        
        view=new AnchorPane();        
        
        this.reload();
        loadMenus(menus);
    }
    public void createHeader()
    {
         
        logo=new ImageView(getClass().getResource("ressources/MenuEnBlanc.png").toString());
        logo.setFitHeight(20);
        logo.setFitWidth(20);
        Label Lmenu=new Label("Menu");
        Lmenu.setTextFill(Color.web("#F8F8FF"));
        Lmenu.setStyle("-fx-font-size: 25px");
        
        
        
        HBox h=new HBox();
        
        h.getChildren().addAll(Lmenu,logo);
        h.setSpacing(95);
        h.setMargin(Lmenu,new Insets(10,0,0,10));
        h.setMargin(logo,new Insets(16,10,0,0));
        h.setStyle("-fx-background-color:#2aa15b");
       
        gridpane.add(h, 0, 0);
        
        HBox h2=new HBox();
        h2.getChildren().addAll(new Label("icone et label"));
        h2.setStyle("-fx-background-color:#2aa15b");
        
        //gridpane.add(h2, 1, 0);
        
    }
    public static void setLogo(Image image)
    {
        logo.setImage(image);
    }
    
    public void reload()
    {
        //j'ai enlevé le header pour changer le design
        this.container=new BorderPane();
        this.container.setPrefSize(500,500);
        //if (this.header!=null) {((BorderPane)this.container).setTop(this.header);}
        ((BorderPane)this.container).setCenter(this.gridpane);
        ((BorderPane)this.container).prefWidthProperty().bind(this.gridpane.widthProperty());

        /*modification par le mouen*/
        //((BorderPane)this.container).setMargin(this.menus,new Insets(0,15,0,0));

        if (this.footer!=null){((BorderPane)this.container).setBottom(this.footer);}

    }
    public void setHeader(String path) throws IOException {
        this.header=FXMLLoader.load(getClass().getResource(path));
        ((Pane)this.header).setPrefHeight(_HEADER_HEIGHT_);
        this.reload();
    }
    public void setFooter(String path) throws IOException {
        this.footer=FXMLLoader.load(getClass().getResource(path));
        ((Pane)this.footer).setPrefHeight(_FOOTER_WIDTH_+30);
        this.reload();
    }
    public void setStyle(String path)
    {

    }
    public MainItem(List menus)
    {
        this("defaultMenus",menus);
    }
    private void displayMenus()
    {
        //creation du treeview
        VBox rootItem=new VBox();
        for(IMenu menu: menusList) {
            //this.menus.setRotateGraphic(true);
            if(menu.isVisible())
            {
                HBox content=new HBox();//mouen change le hbox initial en vbox

                content.setAlignment(Pos.CENTER);

                // Set padding on the left side to avoid overlapping the TitlePane's expand arrow
                // We will also pad the right side
                content.setPadding(new Insets(0, 10, 0, 35));

                // Now, since the TitlePane's graphic node generally has a fixed size, we need to bind our
                // content pane's width to match the width of the TitledPane. This will account for resizing as well
                //content.minWidthProperty().bind(titledPane.widthProperty());

                // Create a Region to act as a separator for the title and button
                //HBox region = new HBox();
                //region.setMaxWidth(Double.MAX_VALUE);
                //HBox.setHgrow(region, Priority.ALWAYS);

                ImageView imageView=new ImageView(MainItem.class.getResource(menu.getIconPath()).toString());
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                // Add our nodes to the contentPane
                content.getChildren().addAll(
                        imageView,
                        new Label(menu.toString())
                );
                

                //essai de mettre un tree view
                System.out.println("TEST SUR LE MENU"+menu);
                System.out.println("LE PATH "+menu.getIconPath());

                //Parent subrootItem;//= new TreeItem<>(menu,imageView);
                VBox subrootItem=new VBox();
                VBox v=new VBox();
                System.out.println(menu.getOptionsList());

                if(menu.getOptionsList().size()<=1)
                {
                    //autrement dit, menu pur ou menu ayant un seul element
                    System.out.println("new button");
                    JFXButton b=new JFXButton();
                    b.setGraphic(content);
                    rootItem.getChildren().add(b);
                    b.setOnAction(e->
                        {
                            System.out.println("click");
                            view.getChildren().clear();
                            view.getChildren().add(menu.getOptionsList().get(0).getItem());
                            menu.getOptionsList().get(0).onShowDo();

                        });
                }
                else{
                    for(IOption option: menu.getOptionsList())
                    {
                        JFXButton b=new JFXButton(option.toString());
                        subrootItem.getChildren().add(b);
                        b.setOnAction(e->
                        {
                            System.out.println("click");
                            view.getChildren().clear();
                            view.getChildren().add(option.getItem());
                            option.onShowDo();

                        });
                    }

                    System.out.println("new titledpane");
                    TitledPane a=new TitledPane();
                    a.setGraphic(content);
                    a.setContent(subrootItem);
                    rootItem.getChildren().add(a);
                }
            }
        }
        System.out.println("rootItem="+rootItem.getChildren());
        //menus=new JFXTreeView(rootItem);
        menus=new HBox();
        menus.getChildren().add(rootItem);
        menus.setPrefHeight(1500);
        menus.setPrefWidth(1500);
        (rootItem).prefWidthProperty().bind(this.menus.widthProperty());
        /*menus.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> handle((TreeItem<IItem>)newValue)
        );*/
        //menus.setShowRoot(false);

        gridpane.add(menus, 0, 1);
        gridpane.add(view,1,1);
        System.out.println(menus.getChildrenUnmodifiable());

    }
    private void loadMenus(List menus)
    {
        this.menusList =(ArrayList<IMenu>)menus;
        displayMenus();
    }

    @Override
    public Parent getItem() {
        System.out.println("container"+container.getChildren());
        return this.container;
    }

    @Override
    public boolean isVisible() {
        return false;
    }


}
