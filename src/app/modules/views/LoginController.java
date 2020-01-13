/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.views;

/**
 *
 * @author HP
 */
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private ImageView ImageViewPoussin;

    @FXML
    private JFXTextField textFieldUserName;

    @FXML
    private JFXTextField textFieldPassword;

    @FXML
    private JFXButton buttonLogin;

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
    void buttonLoginOnClick(ActionEvent event) {
           
        String Name=textFieldUserName.getText();
        String Password=textFieldPassword.getText();
    }

    @FXML
    void passwordTextField(ActionEvent event) {

    }

}

