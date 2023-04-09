/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Categorie1CRUD;
import edu.worshop.model.Categorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

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
     




              Categorie C = new Categorie(nom_categorie);
                    Categorie1CRUD event1 = new Categorie1CRUD();
                    event1.ajouterCategorie(C);
    }
    }
    

