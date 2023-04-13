/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;

import static edu.workshop.controllers.AfficherCategorieBackController.C;
import edu.workshop.services.Categorie1CRUD;
import edu.workshop.services.Produit1CRUD;
import edu.worshop.interfaces.CategorieCRUD;
import edu.worshop.interfaces.ProduitCRUD;
import edu.worshop.model.Categorie;
import edu.worshop.model.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ModifierProduitBackController implements Initializable {

    @FXML
    private TextField NomProduitMfx;
    @FXML
    private TextField PrixProduitMfx;
    @FXML
    private TextField MarqueMfx;
    @FXML
    private TextField ImageMfx;
    @FXML
    private TextField QuantiteMfx;
    
     static int id;
    static int categorie_id;
    static String nom_produit;
    static double prix_produit;
    static String marque;
    static String image;
    static int quantite;
    
    
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        NomProduitMfx.setText(String.valueOf(AfficherProduitBackController.P.getNom_produit()));
        PrixProduitMfx.setText(String.valueOf(AfficherProduitBackController.P.getPrix_produit()));
        MarqueMfx.setText(String.valueOf(AfficherProduitBackController.P.getMarque_produit()));
        ImageMfx.setText(String.valueOf(AfficherProduitBackController.P.getImage()));
        QuantiteMfx.setText(String.valueOf(AfficherProduitBackController.P.getQuantite()));
        
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
    private void ModifierProduitBack(ActionEvent event) {
     ProduitCRUD inter = new Produit1CRUD();
        int id;
       
              String nom_produit = NomProduitMfx.getText();
        double prix_produit = Double.parseDouble(PrixProduitMfx.getText());
        String marque_produit = MarqueMfx.getText();
        String image = ImageMfx.getText();
        int quantite = Integer.parseInt(QuantiteMfx.getText());
        
         int minLength = 6;
               int minLength1 = 4;
               
               
           if (nom_produit.isEmpty()) {
            showAlert("le nom produit est vide ");
         }else if (!nom_produit.matches(".*([a-zA-Z])+")) {
            showAlert("le nom categorie doit être alphabétique ");
         }else if (nom_produit.matches(".*([a-zA-Z])\\1{2,}.*")) {
            showAlert("La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement.");
             }else if (nom_produit.length() < minLength1) {
            showAlert("Le type doit contenir au moins " + minLength1 + " caractères");
             }
              if (prix_produit < 0) {
        showAlert("Prix cannot be negative.");
        return;
    }
               else if (marque_produit.isEmpty()) {
            showAlert("la marque produit est vide ");
         }else if (!marque_produit.matches(".*([a-zA-Z])+")) {
            showAlert("le nom categorie doit être alphabétique ");
         }else if (marque_produit.matches(".*([a-zA-Z])\\1{2,}.*")) {
            showAlert("La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement.");
             }else if (marque_produit.length() < minLength1) {
            showAlert("Le type doit contenir au moins " + minLength1 + " caractères");
             }
               if (quantite < 0) {
        showAlert("Quantite cannot be negative.");
        return;
    }
                
         
             else{
   
    {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("are you sure to update this Product ?");
Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)  {


                     
                    
                    
                    
                  Produit pro = new Produit(AfficherProduitBackController.P.getId(), AfficherProduitBackController.P.getCategorie_id(), nom_produit, prix_produit, marque_produit, image, quantite);
                inter.modifierProduit(pro);
                    showAlert("Product updated successfully");
                    
                    
    }
                        try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AfficherProduitBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
    }
        
        

    }
    
    }}
