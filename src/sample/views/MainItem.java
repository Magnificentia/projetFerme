package sample.views;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainItem extends Item {
    private ArrayList<IMenu> menusList;
    private Parent header=new HBox();
    private Parent footer=new HBox();
    private TabPane menus;
    private Pane container;
    public static double _FOOTER_WIDTH_=30;
    public static double _HEADER_HEIGHT_=30;

    public MainItem(String name,List menus)
    {
        super(name);
        this.menus=new TabPane();
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
                Tab tab=new Tab();
                tab.setGraphic(new Label(menu.toString()));
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
