/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.views;

import app.Projet;
import app.modules.MainItem;
import com.jfoenix.controls.JFXButton;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author _Nprime496_
 */
public abstract class Form extends Pane{
    
    private JFXButton validate;
    private JFXButton cancel;
    private VBox content;
    private static String _DEFAULT_IMAGE_="modules/ressources/fowl.png";
    
    

    public Form(String path)
    {
        super();
        validate=new JFXButton("valider");
        cancel=new JFXButton("annuler");
        content=new VBox();
        VBox formBox=new VBox();
        HBox h=new HBox();
        System.out.println(path);
        ImageView image=new ImageView(new Image(Projet.class.getResource(path).toString()));
        formBox.getChildren().add(image);
        formBox.getChildren().add(content);
        //ajout des boutons
        h.getChildren().add(validate);
        h.getChildren().add(cancel);
        formBox.getChildren().add(h);
        
        this.validate.setOnAction(event->{onValidateClick();});
        this.cancel.setOnAction(event->{onCancelClick();});
        
        this.getChildren().add(formBox);
    }
    public Form()
    {
        this(_DEFAULT_IMAGE_);
    }
    
    public void addFields(List<? extends Node> liste)
    {
        this.content.getChildren().addAll(liste);
    }
    
    public abstract void onValidateClick();
  
    
    public void onCancelClick()
    {
        ObservableList<Node> n=this.content.getChildren();
        for(Node node : n)
        {
            System.out.println("cancel");
            //node.
        }
    }
    
    public void show()
    {
        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        //dialogStage.getIcons().add(new Image("file:resources/images/icon2.jpg"));
        dialogStage.setTitle("Ajouter un nouveau stock");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(Projet.getMainStage());
        Scene scene = new Scene(this,600,500);
        dialogStage.setScene(scene);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }
}
