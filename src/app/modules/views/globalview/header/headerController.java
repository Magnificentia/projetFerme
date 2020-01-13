package app.modules.views.globalview.header;

import app.Main;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class headerController implements Initializable {
    @FXML
    public ImageView imageViewHeader;
    
    @FXML
    private Label username;

    @FXML
    private Label typeEmp;
    
        @FXML
    private Hyperlink deconnecter;
    


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(Main.getEmp().getNom());
        typeEmp.setText(Main.getEmp().getTypeEm());
        
        deconnecter.setOnMouseClicked(e->{try {
            Main.getMainStage().hide();
            Main.loadLogin();
            Main.getMainStage().show();
                    
            } catch (IOException ex) {
                Logger.getLogger(headerController.class.getName()).log(Level.SEVERE, null, ex);
            }
});
                
               
    }
    
    
}
