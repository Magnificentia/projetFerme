package app.modules.views.goodnight;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import app.modules.IController;
import app.modules.IOption;
import app.modules.OptionItem;
import app.modules.userType;
import sun.misc.GC;

import java.net.URL;
import java.util.*;

public class Goodnightcontroller implements Initializable, IController {

    public Goodnightcontroller()
    {
        super();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public Map<Node, List<userType>> getNodeRoles() {
        //return null;
        Map nodeRoles=new HashMap<Node,List<userType>>();
        return nodeRoles;
    }
}
