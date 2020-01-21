/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.views;

import app.Main;
import app.modules.MainItem;
import com.jfoenix.controls.JFXButton;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author _Nprime496_
 */
public abstract class Form extends Pane{
    
    private JFXButton validate;
    private JFXButton cancel;
    private VBox content;
    private static String _DEFAULT_IMAGE_="fowl1.png";
    
    

    public Form(String path)
    {
        super();
        validate=new JFXButton("valider");
        cancel=new JFXButton("annuler");
        content=new VBox();
        VBox formBox=new VBox();
        HBox h=new HBox();
        System.out.println(path);
        System.out.println(getClass().getResource(path));
        ImageView image=new ImageView(new Image(getClass().getResource(path).toString()));
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
    
}
