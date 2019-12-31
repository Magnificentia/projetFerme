package app.modules;


import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;

public class MainItem extends Item {
    private ArrayList<IMenu> menusList;
    private Parent header=new HBox();
    private Parent footer=new HBox();
    private JFXTabPane menus;
    private static Pane container;
    public static double _FOOTER_WIDTH_=30;
    public static double _HEADER_HEIGHT_=30;


    public MainItem(String name,List menus)
    {
        super(name);
        this.menus=new JFXTabPane();
        this.menus.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        this.menus.setId("menus");
        this.menus.setRotateGraphic(true);
        this.menus.setSide(Side.LEFT);
        this.menus.setPrefSize(500,500);
        this.reload();
        loadMenus(menus);
    }
    public void reload()
    {
        this.container=new BorderPane();
        this.container.setPrefSize(500,500);
        if (this.header!=null) {((BorderPane)this.container).setTop(this.header);}
        ((BorderPane)this.container).setCenter(this.menus);

        /*modification par le mouen*/
        ((BorderPane)this.container).setMargin(this.menus,new Insets(0,15,0,0));

        if (this.footer!=null){((BorderPane)this.container).setBottom(this.footer);}
        //if (this.footer!=null){((BorderPane)this.container).setRight(this.footer);}
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
        for(IMenu menu: menusList) {
            //this.menus.setRotateGraphic(true);
            if(menu.isVisible())
            {
                VBox content=new VBox();//mouen change le hbox initial en vbox
                Label label = new Label(menu.toString());
          
                //label.setAlignment(Pos.BOTTOM_CENTER)
                System.out.println(getClass().getResource(menu.getIconPath()));
                ImageView icon = new ImageView(new Image(getClass().getResource(menu.getIconPath()).toString())); // for example
                icon.setFitWidth(50); icon.setFitHeight(50);//mouen augmente la taille des icones
               

                content.getChildren().addAll(icon, label);
                content.setAlignment(Pos.CENTER);// je change la position dans le pane;
                Tab tab=new Tab();
                tab.setGraphic(content);
                tab.setContent(menu.getItem());
                menus.getTabs().addAll(tab);
            }
        }
    }
    private void loadMenus(List menus)
    {
        this.menusList =(ArrayList<IMenu>)menus;
        displayMenus();
    }

    @Override
    public Parent getItem() {
        return this.container;
    }

    @Override
    public boolean isVisible() {
        return false;
    }
}
