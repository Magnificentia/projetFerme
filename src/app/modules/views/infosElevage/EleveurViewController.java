package app.modules.views.infosElevage;



import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;

import app.modules.userType;




import java.net.URL;
import java.util.*;
//putain
public class EleveurViewController implements Initializable, IController {



    @Override
    public Map<Node,List<userType>> getNodeRoles() {
        Map nodeRoles=new HashMap<Node,List<userType>>();
        return nodeRoles;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
    

