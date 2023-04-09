/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

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
      
        System.out.println(AfficherCategorieBackController.id);
        Categorie even = new Categorie(AfficherCategorieBackController.id, nom_categorie );
                inter.modifierCategorie(even);
                

    }
    }
    

