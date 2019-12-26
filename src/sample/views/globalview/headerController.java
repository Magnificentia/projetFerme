package sample.views.globalview;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class headerController implements Initializable {

    public ImageView imageViewHeader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageViewHeader.setImage(
                new Image(String.valueOf(getClass().getResource("poussin.jpg")))
        );
    }
}
