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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        
        validate.setPrefWidth(107);
        validate.setPrefHeight(51);
        validate.setStyle("-fx-background:#2aa15b");
        validate.setStyle("-fx-text-fill:white");
        
        cancel.setPrefWidth(107);
        cancel.setPrefHeight(51);
        cancel.setStyle("-fx-background:#2aa15b");
        cancel.setStyle("-fx-text-fill:white");
        
        content=new VBox();
        content.setStyle("-fx-background-color:#f8f8ff");
        VBox formBox=new VBox();
        /*formBox.setPrefHeight(705);
        formBox.setPrefHeight(757);
        */
        
        //ajout image et configuration hbox
        System.out.println(path);
        ImageView image=new ImageView(new Image(Projet.class.getResource(path).toString(),30,30,false,false));
        HBox himage=new HBox();
        himage.getChildren().add(image);
        himage.setPrefWidth(739);
        himage.setPrefHeight(145);
        himage.setAlignment(Pos.CENTER);
        himage.setStyle("-fx-background-color:#f8f8ff");
        
        //ajout des boutons et configuration hbox
        HBox h=new HBox();
        h.getChildren().add(validate);
        h.getChildren().add(cancel);
        h.setSpacing(65);
        h.setPrefWidth(739);
        h.setPrefHeight(75);
        h.setAlignment(Pos.CENTER);
        h.setStyle("-fx-background-color:#f8f8ff");
        
        formBox.getChildren().add(himage);
        formBox.getChildren().add(content);
        formBox.getChildren().add(h);
        //formBox.setSpacing(15);
        formBox.setMargin( h, new Insets(10,10, 10, 10));
        formBox.setMargin( content, new Insets(0,10, 5, 10));
        formBox.setMargin( himage, new Insets(10,10, 0, 10));
        formBox.setAlignment(Pos.CENTER);
        
        
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
        Scene scene = new Scene(this,757,682);
        scene.getStylesheets().add("app/modules/views/global.css");;
        dialogStage.setScene(scene);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }
}
