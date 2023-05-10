/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Commentaire1CRUD;
import edu.workshop.services.Post1CRUD;
import edu.workshop.services.ServiceUser;
import edu.worshop.interfaces.CommentaireCRUD;
import edu.worshop.interfaces.PostCRUD;
import edu.worshop.model.Commentaire;
import edu.worshop.model.Post;
import edu.worshop.model.User;
import edu.worshop.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sammo
 */
public class Commentaire_UpdateController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private TextArea ContenuCMfx;
    @FXML
    private TextField AuteurCMfx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AuteurCMfx.setText(String.valueOf(Commentaire_ListController.C.getAuteurCommentaire()));
        ContenuCMfx.setText(String.valueOf(Commentaire_ListController.C.getContenuCommentaire()));
        int userId = MyConnection.getUserId();
        User user = new ServiceUser().getUserById(userId);

    }    

private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void AddUser2(MouseEvent event) {
    }

    @FXML
    private void ModifierCommentaireBack(ActionEvent event) {
         CommentaireCRUD inter = new Commentaire1CRUD();
        int id ;
        String auteur = AuteurCMfx.getText();
        String contenu = ContenuCMfx.getText();
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
         }/*else if (!contenu.matches("(?s)[a-zA-Z\\s]+")) {
            showAlert("Le contenu doit être alphabétique avec des retours à la ligne");
        }*/else if (
       contenu.length() < 10) {
            showAlert("Le contenu doit contenir au moins " + 10 + " caractères");
         }else{
             
         
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("êtes vous sûr de vouloir modifier ce commencher ?");
Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {  
            int userId = MyConnection.getUserId();
        User user = new ServiceUser().getUserById(userId);
        Commentaire even = new Commentaire(Commentaire_ListController.C.getId(),Commentaire_ListController.C.getPost_id(),contenu,auteur,userId);
                inter.modifierCommentaire(even);
                showAlert("Post ajouté successfully");
    }
           
                 try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Post_List.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(Post_ListController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }   }
    }
        
    

    @FXML
    private void RetourCommentaireBack(ActionEvent event) throws IOException {
        
    Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Commentaire_List.fxml"));
    Scene scene = new Scene(page1);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
}
