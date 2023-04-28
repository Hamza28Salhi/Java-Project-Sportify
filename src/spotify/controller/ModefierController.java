/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotify.controller;

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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import spotify.entities.Categorie;
import spotify.services.CategorieService;

/**
 * FXML Controller class
 *

 */
public class ModefierController implements Initializable {

    @FXML
    private TextField des;

    CategorieService categorieService = new CategorieService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Categorie c= categorieService.get_CatById(AfficherCategorieController.id);
        des.setText(c.getDescription());
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws SQLDataException {
        
        
        Categorie c = new Categorie();
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
