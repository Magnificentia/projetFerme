/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.views.bonjour;

import com.jfoenix.controls.JFXButton;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author HP
 */
public class Formulaire extends VBox {
    
    private JFXButton Ok;
    private JFXButton Cancel;
    private ImageView image;
    private VBox paneElements;
    
    //public double size;

    public Formulaire(String imageUrl,double Widthsize,double Heightsize) {
        
        //this.Ok = new JFXButton(Ok);
        //this.Cancel = new JFXButton(Cancel);
        this.image = new ImageView(getClass().getResource(imageUrl).toString());
        this.paneElements=new VBox();
        
        /*HBox paneButton=new HBox(15);
	paneButton.getChildren().addAll(this.Ok,this.Cancel);
	paneButton.setAlignment(Pos.CENTER);
        */
        HBox paneImage=new HBox();
        paneImage.getChildren().addAll(image);
        paneImage.setAlignment(Pos.CENTER);
        //HBoxpaneImage.setMargin(this.,new Insets(18,0,0,0));
        
        super.getChildren().addAll(paneImage,this.paneElements);
        //super.getChildren().setMargin(paneImage,new Insets(10,0,0,0));
        super.setPrefWidth(Widthsize);
        super.setPrefHeight(Heightsize);
        super.setSpacing(25);
    }

    
    public void addElement(ArrayList<Node> TablesDesEntrees)
    {
     
        
        for(Node n:TablesDesEntrees)
        {
            System.out.println("adding");
            HBox hboxNode=new HBox();
            hboxNode.getChildren().addAll(n);
            hboxNode.setAlignment(Pos.CENTER);
            paneElements.getChildren().add(hboxNode);
            paneElements.setSpacing(5);
            
        }
        
    }
    
    
    
}
