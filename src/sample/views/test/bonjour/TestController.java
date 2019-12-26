package sample.views.test.bonjour;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import sample.views.IController;
import sample.views.OptionItem;
import sample.views.userType;

import java.net.URL;
import java.util.*;
//putain
public class TestController implements Initializable, IController {
    @FXML
    TableView table;

    @FXML
    VBox modify;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public Map<Node,List<userType>> getNodeRoles() {
        Map nodeRoles=new HashMap<Node,List<userType>>();
        table.setEditable(false);
        List<userType> liste=new ArrayList<>();
        //liste.add(userType._ADMIN_);
        liste.add(userType._SELLER_);
        nodeRoles.put((Node)table, liste);
        System.err.println(nodeRoles.get(table));
        System.err.println(nodeRoles);
        System.err.println(nodeRoles.keySet());
        return nodeRoles;
    }
}
