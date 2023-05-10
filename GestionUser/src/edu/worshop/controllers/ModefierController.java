/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.CategorieService;
import edu.worshop.model.CategorieAbionnement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *

 */
public class ModefierController implements Initializable {

    @FXML
    private TextField des;

    CategorieService categorieService = new CategorieService();
    @FXML
    private Label label;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CategorieAbionnement c= categorieService.get_CatById(AfficherCategorieController.id);
        des.setText(c.getDescription());
        // TODO
        
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 16);
        label.setFont(font);
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws SQLDataException {
        
        
        CategorieAbionnement c = new CategorieAbionnement();
        c.setDescription(des.getText());
        c.setId_categori(AfficherCategorieController.id);
        categorieService.ModifierCategorie(c);
        Parent root ;
                     try {
              root = FXMLLoader.load(getClass().getResource("/spotify/view/AfficherCategorie.fxml"));
               Stage myWindow = (Stage) des.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
               }
    }

    
}
