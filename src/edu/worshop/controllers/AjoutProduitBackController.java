/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Produit1CRUD;
import edu.worshop.model.Produit;
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
public class AjoutProduitBackController implements Initializable {
 private TextField NomProduitfx;
     private TextField PrixProduitfx;
     private TextField MarqueProduitfx;
     private TextField Imagefx;
     private TextField Quantitefx;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    @FXML
    private void AjouterCategorieBack(ActionEvent event) {
      
        String nom_produit = NomProduitfx.getText();
        String prix_produit = PrixProduitfx.getText();
        String marque_produit = MarqueProduitfx.getText();
        String image = Imagefx.getText();
        String quantite = Quantitefx.getText();
     




              Produit P = new Produit(nom_produit,prix_produit,marque_produit,image,quantite);
                    Produit1CRUD event1 = new Produit1CRUD();
                    event1.ajouterProduit(P);
    }
    
}
