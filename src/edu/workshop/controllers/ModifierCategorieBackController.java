/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;

import edu.workshop.services.Categorie1CRUD;
import edu.worshop.interfaces.CategorieCRUD;
import edu.worshop.model.Categorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


import javafx.stage.Stage;

import javafx.scene.control.ButtonType;
/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ModifierCategorieBackController implements Initializable {

    @FXML
    private TextField NomCategorieMfx;
    static int id;
    static String nom_categorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        NomCategorieMfx.setText(String.valueOf(AfficherCategorieBackController.C.getNom_categorie()));
     
    }  
    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void ModifierCategorieBack(ActionEvent event) {
        CategorieCRUD inter = new Categorie1CRUD();
        
        
        String nom_categorie = NomCategorieMfx.getText();
      
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
                alert.setContentText("are you sure to update this categorie ?");
Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {


              Categorie even = new Categorie(AfficherCategorieBackController.C.getId(), nom_categorie );
                inter.modifierCategorie(even);
    
                    
                                 showAlert("Categorie updated successfully");
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
    }
    
