/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.views.login;

/**
 *
 * @author HP
 */
import app.Main;
import app.modules.database.DbManagerNnane;
import app.modules.model.Employes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sun.net.www.protocol.mailto.MailToURLConnection;

public class LoginController implements Initializable {

    @FXML
    private ImageView ImageViewPoussin;
    

    @FXML
    private JFXTextField textFieldUserName;

    @FXML
    private JFXTextField textFieldPassword;
        @FXML
    private Label information;

    @FXML
    private JFXButton buttonLogin;
    
    public Employes connnecter() throws IOException
    {
        Employes emp=DbManagerNnane.connecter(textFieldUserName.getText(),textFieldPassword.getText());

        if(emp!=null)
        {
            Main.getMainStage().hide();
            
            Main.loadApplication(emp);
            
            Main.getMainStage().show();
        }
        else
        {
            //loading.setVisible(false);
            information.setText("wrong credentials");                    
        }
        //message d'erreur
        return null;
        
    }

    @FXML
    private JFXButton buttonCancel;

    @FXML
    void NameTextField(ActionEvent event) {

    }

    @FXML
    void buttonCancelOnClick(ActionEvent event) {
            Stage s=(Stage)buttonCancel.getScene().getWindow();
            s.close();
    }

    @FXML
    void buttonLoginOnClick(ActionEvent event) throws IOException {
           
        String Name=textFieldUserName.getText();
        String Password=textFieldPassword.getText();
        connnecter();
    }

    @FXML
    void passwordTextField(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //loading.setVisible(false);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

