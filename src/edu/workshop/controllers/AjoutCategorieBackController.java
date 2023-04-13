/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;

import edu.workshop.services.Categorie1CRUD;
import edu.worshop.model.Categorie;
import edu.worshop.model.Categorie;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.text.ParseException;


import java.util.Objects;
import java.util.Optional;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AjoutCategorieBackController implements Initializable {

    @FXML
    private TextField NomCategoriefx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterCategorieBack(ActionEvent event) {
      
        String nom_categorie = NomCategoriefx.getText();
        
               int minLength = 6;
               int minLength1 = 4;
 
     
if (nom_categorie.isEmpty()) {
            showAlert("le nom categorie est vide ");
         }else if (!nom_categorie.matches(".*([a-zA-Z])+")) {
            showAlert("le nom categorie doit être alphabétique ");
         }else if (nom_categorie.matches(".*([a-zA-Z])\\1{2,}.*")) {
            showAlert("La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement.");
             }else if (nom_categorie.length() < minLength1) {
            showAlert("Le type doit contenir au moins " + minLength1 + " caractères");
             }else{
             
         
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("are you sure to add this categorie ?");
Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {


              Categorie C = new Categorie(nom_categorie);
                    Categorie1CRUD event1 = new Categorie1CRUD();
                    event1.ajouterCategorie(C);
    
                    
                                 showAlert("Categorie added successfully");
    }
                 try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AfficherCategorieBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutCategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
 
}
}
 private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
   }
    