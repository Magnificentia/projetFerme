package app.modules.views.parametres;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import app.modules.IController;
import app.modules.MainItem;

import app.modules.userType;
import com.jfoenix.controls.JFXButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
//putain
public class ParametresViewController implements Initializable, IController {
    @FXML
    private JFXButton imagechooser;
    @FXML
    private ImageView image;
    
    @FXML
    private JFXButton save;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        imagechooser.setOnAction(
                new EventHandler<ActionEvent>(){
 
        @Override
        public void handle(ActionEvent t) {
            FileChooser fileChooser = new FileChooser();
             
            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
              
            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
                       
            try {
                if (file!=null) 
                {BufferedImage bufferedImage = ImageIO.read(file);
                Image im = SwingFXUtils.toFXImage(bufferedImage, null);
                image.setImage(im);}
            } catch (IOException | IllegalArgumentException ex) {
                //System.out.println("exception");
                Logger.getLogger(ParametresViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        }
    }   
    );   
        
    save.setOnAction(event->
    {
        Image im=image.getImage();
        if(im!=null)MainItem.setLogo(im);
    });
    
    }
    
    
    @Override
    public Map<Node,List<userType>> getNodeRoles() {
        Map nodeRoles=new HashMap<Node,List<userType>>();
        //table.setEditable(false);
        //List<userType> liste=new ArrayList<>();
        ////liste.add(userType._ADMIN_);
        //liste.add(userType._SELLER_);
        //nodeRoles.put((Node)table, liste);
        //System.err.println(nodeRoles.get(table));
        //System.err.println(nodeRoles);
        //System.err.println(nodeRoles.keySet());
        return nodeRoles;
    }
    

}
