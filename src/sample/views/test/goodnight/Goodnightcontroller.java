package sample.views.test.goodnight;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import sample.views.IController;
import sample.views.IOption;
import sample.views.OptionItem;
import sample.views.userType;
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
