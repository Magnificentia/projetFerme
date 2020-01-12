/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.model;

/**
 *
 * @author HP
 */
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Popup  {

   private static boolean confirmation;
   private static Stage stage;
	
	public static boolean show(String message,String title,VBox formulaire){
		
		
		stage=new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);
		
		JFXButton btn1=new JFXButton();
		btn1.setText("Ok");
		btn1.setOnAction(e->btnYes_clicked());
		
		
		
		JFXButton btn2=new JFXButton();
		btn2.setText("Cancel");
		btn2.setOnAction(e->btnNo_clicked());
		
		Label lab=new Label();
		lab.setText(message);
		
		HBox paneH=new HBox(15);
		paneH.getChildren().addAll(btn1,btn2);
		paneH.setAlignment(Pos.CENTER);
		
		
		VBox pane=new VBox (20);
		pane.getChildren().addAll(lab,formulaire,paneH);
		pane.setAlignment(Pos.CENTER);
		
		Scene scene=new Scene(pane);
		
		stage.setScene(scene);
		stage.show();
		
		return confirmation;
		
		
	}
	
	private static void btnYes_clicked() {
		
		confirmation=true;
		stage.close();
		
		
	}
	

	private static void btnNo_clicked() {
		
		confirmation=false;
		stage.close();
		
	}

   
    
}
