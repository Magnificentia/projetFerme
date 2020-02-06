package app.modules;


import com.jfoenix.controls.JFXTreeView;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainItem extends Item {
    private ArrayList<IMenu> menusList;
    private Parent header=new HBox();
    private Parent footer=new HBox();

    private JFXTreeView menus;
    private Pane container;
    private Pane view;

    
    public static double _FOOTER_WIDTH_=30;
    public static double _HEADER_HEIGHT_=30;
    
    private GridPane gridpane;
    
    private static ImageView logo;


    public MainItem(String name,List menus)
    {
        super(name);
        
        logo=new ImageView();
        logo.setFitHeight(50);
        logo.setFitWidth(50);
        VBox v=new VBox();
        
        gridpane=new GridPane();//pour la mise en page
        gridpane.getColumnConstraints().add(new ColumnConstraints(150));//specifie la longueur (horizontalement) de l'espace pour menus et icone
        gridpane.getRowConstraints().add(new RowConstraints(80));//specifie la largeur (verticalement) de l'espace pour le header
        
        v.getChildren().addAll(logo,new Label("slogan"));
        gridpane.add(v, 0, 0);
        gridpane.add(new Label("icone et label"), 1, 0);
        
        view=new AnchorPane();        
        
        this.reload();
        loadMenus(menus);
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
        TreeItem<IItem> rootItem=new TreeItem<IItem>(new MenuItem(menusList));
        for(IMenu menu: menusList) {
            //this.menus.setRotateGraphic(true);
            if(menu.isVisible())
            {
                HBox content=new HBox();//mouen change le hbox initial en vbox
                Label label = new Label(menu.toString());
                //essai de mettre un tree view
                System.out.println("TEST SUR LE MENU"+menu);
                TreeItem<IItem> subrootItem = new TreeItem<>(menu);
                
                for(IOption option: menu.getOptionsList())
                {
                    //Hyperlink op=new Hyperlink(option.toString());
                    TreeItem<IItem> item = new TreeItem<>(option);
                    subrootItem.getChildren().add(item);
                }
                if(subrootItem.getChildren().size()==1)
                {
                    rootItem.getChildren().add(subrootItem.getChildren().get(0));
                }
                else
                {
                    rootItem.getChildren().add(subrootItem);
                }

            }
        }
        System.out.println("rootItem="+rootItem.getChildren());
        menus=new JFXTreeView(rootItem);
         menus.setPrefHeight(1500);
         menus.setPrefWidth(1500);
        menus.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> handle((TreeItem<IItem>)newValue)
        );
        menus.setShowRoot(false);

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

    private void handle(TreeItem<IItem> newValue) {
        
        System.out.println(newValue.getChildren());
        if(newValue.getChildren().size()==0)//si l'élement n'a pas de sous element alors c'est une option
        {
            System.out.println("click on "+newValue.getValue());
            view.getChildren().clear();
            
            view.getChildren().add(newValue.getValue().getItem());
            newValue.getValue().onShowDo();
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
