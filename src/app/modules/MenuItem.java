package app.modules;


import com.jfoenix.controls.JFXTabPane;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;


public class MenuItem extends Item implements IMenu {

    private boolean visible=true;
    private ArrayList<IOption> optionsList=new ArrayList<>();
    //private BorderPane options;
    private JFXTabPane tp=new JFXTabPane();
    private String iconPath;

    public static String _DEFAULT_MENU_NAME_="defaultMenu";
    public static String _DEFAULT_MENU_IMAGE_="image.png";

    public MenuItem(String name,String iconPath,List options)
    {
        super(name);
        tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        optionsList=(ArrayList<IOption>)options;
        this.iconPath=iconPath;
        loadMenus(optionsList);
    }
    public MenuItem(String name,String iconPath,IOption option)
    {
        super(name);
        tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        optionsList.add(option);
        this.iconPath=iconPath;
    }
    
    public void setIcon(String path)
    {
        VBox content=new VBox();
        
        Label label = new Label("Text");
        ImageView icon = new ImageView(new Image("image.png")); // for example
        icon.setFitWidth(25); icon.setFitHeight(25);

        content.getChildren().addAll(icon, label);
        
    }
    public MenuItem(List options)
    {
        this(_DEFAULT_MENU_NAME_,_DEFAULT_MENU_IMAGE_,options);
    }

    private void displayOptions()
    {
        visible=false;
        if (optionsList ==null) {
            return;
        }
        for(IOption option: optionsList)
        {
            if (option.isVisible())
            {
                //Insets insets = new Insets(20,20,20,20);
                Tab tab=new Tab();
                tab.setContent(option.getItem());
                //((Pane) tab.getContent()).setPadding(insets);
                tab.setGraphic(new Label(option.toString()));
                tp.getTabs().addAll(tab);
                visible=true;
            }
            //ajout de menus
        }
    }

    public ArrayList<IOption> getOptionsList() {
        return optionsList;
    }
    
    
    public String getIconPath()
    {
        System.out.println("retourne _"+this.iconPath);
        return this.iconPath;
    }

    private void loadMenus(List options)
    {
        this.optionsList =(ArrayList<IOption>)options;
        displayOptions();
    }

    @Override
    public Parent getItem(){
        return (Parent)this.optionsList.get(0);
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }
}
