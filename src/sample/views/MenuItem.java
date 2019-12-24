package sample.views;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

public class MenuItem extends Item implements IMenu {
    private boolean visible=true;
    private ArrayList<IOption> optionsList;
    private BorderPane options;
    private TabPane tp=new TabPane();
    public static String _DEFAULT_MENU_NAME_="defaultMenu";

    public MenuItem(String name,List options)
    {
        super(name);
        this.options=new BorderPane();
        tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        this.options.setCenter(tp);
        optionsList=(ArrayList<IOption>)options;
        this.options.setId("options");
        loadMenus(optionsList);
    }
    public MenuItem(List options)
    {
        this(_DEFAULT_MENU_NAME_,options);
    }

    private void displayOptions()
    {
        visible=false;
        if (optionsList !=null)
        {
            for(IOption option: optionsList)
            {
                if (option.isVisible())
                {
                    Tab tab=new Tab();
                    tab.setContent(option.getItem());
                    tab.setGraphic(new Label(option.toString()));
                    tp.getTabs().addAll(tab);
                    visible=true;
                }
                //ajout de menus
            }
        }
    }

    private void loadMenus(List options)
    {
        this.optionsList =(ArrayList<IOption>)options;
        displayOptions();
    }

    @Override
    public Parent getItem() {
        return this.options;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }
}
