/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.gui;

import com.google.zxing.qrcode.QRCodeWriter;
import edu.workshop.services.Evenement1CRUD;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author HOUYEM
 */
public class GestionEvenement extends Application {
    
  
    @Override
    public void start(Stage primaryStage) {
      
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        // tackPane root = new StackPane();
        //root.getChildren().add(btn);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AffichageEvenementBack.fxml"));
            Scene scene = new Scene(root);
 
            primaryStage.setTitle("Trippie");

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}


