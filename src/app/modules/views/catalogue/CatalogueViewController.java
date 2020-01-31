package app.modules.views.catalogue;



import app.modules.views.client.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;
import app.modules.database.DbManager;
import app.modules.model.Client;

import app.modules.userType;
import app.modules.views.BaseView;




import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
//putain
public class CatalogueViewController implements Initializable, IController {



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
    

