/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;


import edu.workshop.services.Post1CRUD;
import edu.worshop.interfaces.PostCRUD;
import edu.worshop.model.Post;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import static java.util.Collections.list;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sammo
 */
public class ModifierEvenementBackController implements Initializable {

    @FXML
    private TextField TitreMfx;
    @FXML
    private TextArea ContenuMfx;
    @FXML
    private TextField ImagePostMfx;
    @FXML
    private TextField AuteurMfx;
    
    static int id;
    static String titre;
    static String contenu;
    static String auteur;
    static String imagePost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TitreMfx.setText(String.valueOf(AffichageEvenementBackController.P.getTitrePost()));
        AuteurMfx.setText(String.valueOf(AffichageEvenementBackController.P.getAuteurPost()));
        ContenuMfx.setText(String.valueOf(AffichageEvenementBackController.P.getContenuPost()));
        ImagePostMfx.setText(String.valueOf(AffichageEvenementBackController.P.getImagePost()));
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
    @FXML
    private void ModifierPostBack(ActionEvent event) {
                PostCRUD inter = new Post1CRUD();
        int id ;
        String titre = TitreMfx.getText();
        String contenu = ContenuMfx.getText();
        String auteur = AuteurMfx.getText();
        String imagePost = ImagePostMfx.getText();
                       int minLength = 6;
               int minLength1 = 4;
 

/*if(date==null) {
   showAlert("La date ne peut pas être vide");
}*/
      
         
         if (auteur.isEmpty()) {
            showAlert("l'auteur est vide ");
         }else if (!auteur.matches(".*([a-zA-Z])+")) {
            showAlert("l'auteur doit être alphabétique ");
         }else if (auteur.length() < minLength1) {
            showAlert("L'auteur doit contenir au moins " + minLength1 + " caractères");    
         
             }else if (contenu.isEmpty()) {
            showAlert("Le contenu est vide ");
         }else if (!contenu.matches("(?s)[a-zA-Z\\s]+")) {
            showAlert("Le contenu doit être alphabétique avec des retours à la ligne");
        }else if (
       contenu.length() < 10) {
            showAlert("Le contenu doit contenir au moins " + 10 + " caractères");
         }
   
         else if (imagePost.isEmpty()) {
            showAlert("L'image est vide ");
        
    }else if (titre.isEmpty()) {
            showAlert("Le titre est vide ");
            }else if (
       titre.length() < minLength1) {
            showAlert("Le titre doit contenir au moins " + minLength1 + " caractères");
         }else if (!titre.matches("[a-zA-Z]+")) {
            showAlert("le titre doit être alphabétique ");
         }else if (!Character.isUpperCase(titre.charAt(0))) {
            showAlert("Le titre doit commencer par une majuscule");
         }else{
             
         
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("êtes vous sûr de vouloir modifier ce Post ?");
Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {  
        Post even = new Post(AffichageEvenementBackController.P.getId(),titre,contenu,imagePost,auteur );
                inter.modifierPost(even);
                showAlert("Post ajouté successfully");
    }
           
                 try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichagePostBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutEvenementBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }   }
    }

     @FXML
    private void RetourPostBack(ActionEvent event) throws IOException {
    Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichagePostBack.fxml"));
    Scene scene = new Scene(page1);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
    
}
