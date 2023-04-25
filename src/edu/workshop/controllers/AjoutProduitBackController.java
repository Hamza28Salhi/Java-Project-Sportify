/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;

//import static edu.workshop.controllers.AfficherProduitBackController.image;
import edu.workshop.services.Produit1CRUD;
import edu.worshop.model.Produit;
import edu.workshop.services.Categorie1CRUD;
import edu.worshop.model.Categorie;
import edu.worshop.utils.MyConnection;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import java.util.Optional;

import java.util.logging.Level;
import java.util.logging.Logger;


import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.awt.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author lenovo
 */




public class AjoutProduitBackController implements Initializable {
    
    
   
    @FXML
 private TextField NomProduitfx;
    @FXML
     private TextField PrixProduitfx;
    @FXML
     private TextField MarqueProduitfx;
    @FXML
     private TextField Imagefx;
    @FXML
     private TextField Quantitefx;
    
     Categorie C;
    static int categorie_id;
    @FXML
    private ImageView imageView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    @FXML
    private void AjouterProduitBack(ActionEvent event) {
      
        if (NomProduitfx.getText().isEmpty() || MarqueProduitfx.getText().isEmpty() /*|| Imagefx.getText().isEmpty()*/ ) {
        showAlert("Please fill in all fields.");
        return;
    }
        
        
        String nom_produit = NomProduitfx.getText();
        double prix_produit = Double.parseDouble(PrixProduitfx.getText());
        String marque_produit = MarqueProduitfx.getText();
        //String image = Imagefx.getText();
        //int quantite = Integer.parseInt(Quantitefx.getText());
     
              int minLength = 6;
               int minLength1 = 4;
 
               
               int quantite;
try {
    quantite = Integer.parseInt(Quantitefx.getText());
} catch (NumberFormatException e) {
    showAlert("Quantite  should be a number.");
    return;
}

                     
               
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
             
               // else if (image.isEmpty()) {
            //showAlert("L'image est vide ");
        
  //  }
            else if (quantite < 0) {
        showAlert("Quantite cannot be negative.");
        return;
    }
         
             else{
   
    {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("are you sure to add this Product ?");
Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)  {


                     
                    
                  String image = MyConnection.getImage_Name();
  
                    
            Produit P = new Produit(AfficherCategorieBackController.C.getId(),nom_produit, prix_produit, marque_produit, image, quantite);
                    Produit1CRUD event1 = new Produit1CRUD();
                    event1.ajouterProduit(P);
                    System.out.println(AfficherCategorieBackController.C.getId());
                    showAlert("Product added successfully");
                    
                    
    }
                        try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AfficherProduitBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AfficherProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
                     
    }


              
    }}

   
     private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", ".png", ".jpg", "*.gif")
        );
        Window window = ((Node) event.getTarget()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(window);
        if (selectedFile != null) {
            try {
                // Create a directory called "upload" if it doesn't exist
                File uploadDir = new File("upload");
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                // Copy the selected file to the "upload" directory
                String fileName = selectedFile.getName();
                File destFile = new File("upload/" + fileName);
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                // Set the ImageView's image to the selected image
               javafx.scene.image.Image image = new javafx.scene.image.Image(destFile.toURI().toString());
                imageView.setImage(image);
                //Save the image name in img_Saver
                MyConnection.setImage_Name(fileName);
            } catch (IOException ex) {
                Logger.getLogger(AjoutProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}