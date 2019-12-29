package app.modules;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import app.modules.views.bonjour.TestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionItem extends Item implements IOption {

    private Parent item;
    private boolean visible=true;
    protected Map<Node, List<userType>> nodeRoles;

    public static String _DEFAULT_OPTION_NAME="defaultoption";

    public OptionItem()
    {
        super("default");
        //some default values
    }
    public OptionItem(String name,String path)//lorsqu'
    {
        super(name);
        nodeRoles=new HashMap<Node, List<userType>>();
        AnchorPane root= null;
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
            root = (AnchorPane) (fxmlLoader.load());
            System.err.println(((IController)fxmlLoader.getController()).getNodeRoles()+"");
            this.nodeRoles=((IController)fxmlLoader.getController()).getNodeRoles();
            this.item =root;
        } catch (IOException e) {
            System.err.println(root);
            e.printStackTrace();
        }
        //manageRoles();
    }


    public OptionItem(String path)
    {
        this(_DEFAULT_OPTION_NAME,path);
    }
    @Override
    public Parent getItem() {
        return this.item;
    }

    public ObservableList<Node> getSubOptions()
    {
        //en principe ce sont des panes qui contiennent les sous options
        return this.item.getChildrenUnmodifiable();
    }

    public void manageRoles()
    {
        //System.err.println(this.nodeRoles.keySet());
        if(this.nodeRoles==null)
            return;
        for (Node node : this.nodeRoles.keySet())
        {
            //chaque node est en principe un Pane
            if(this.nodeRoles.get(node).contains(userType._ADMIN_))
            {
                node.setVisible(false);
            }
        }
    }

    public boolean isVisible()
    {
        return this.visible;
    }

}