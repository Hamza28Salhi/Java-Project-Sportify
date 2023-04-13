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
import static java.util.Collections.list;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sammo
 */
public class AffichageEvenementBackController implements Initializable {

    @FXML
    private ListView<?> affichagePostBackfx;
    //static Post P ;
static int id;
static String titre_Post,contenu_Post,image_Post,auteur_Post;
static Post P = new Post();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListView<Post> list1 = (ListView<Post>) affichagePostBackfx; //ListView<Post> list1 = affichagePostBackfx;
PostCRUD inter = new Post1CRUD();
List<Post> list2 = inter.afficherPost();
for (int i = 0; i < list2.size(); i++) {
    Post P = list2.get(i);
    list1.getItems().add(P); // add Post to ListView
}


        }   

           @FXML
    private void SupprimerPostBack(ActionEvent event) {
    ListView<Post> list = (ListView<Post>) affichagePostBackfx;
    PostCRUD inter = new Post1CRUD();

    int selectedIndex = list.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {      
        Post P = list.getSelectionModel().getSelectedItem();
        inter.supprimerPost(P.getId());
        list.getItems().remove(selectedIndex);
    } else {
        showAlert("Veuillez sélectionner un post à supprimer.");
    }
    }

    @FXML
     private void AjoutPostBack(ActionEvent event) {
    
    try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AjoutPostBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutPostBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
 
}

    @FXML
    private void ModifierPostBack(ActionEvent event) {
        
        ListView<Post> list = (ListView<Post>) affichagePostBackfx; //ListView<Post> list = affichagePostBackfx;
        PostCRUD inter = new Post1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        
  if (selectedIndex >=0){
            
        
        Post p = list.getSelectionModel().getSelectedItem();

        int id = p.getId();
        String titre_Post = p.getTitrePost();
        String contenu_Post = p.getContenuPost();
        String image_Post = p.getImagePost();
        String auteur_Post = p.getAuteurPost();
        P=p;
      
        
        
        
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/ModifierPostBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageEvenementBackController.class.getName()).log(Level.SEVERE, null, ex);
        //AffichagePostBackController
        }
        }else{showAlert("Veuillez sélectionner un post à modifier.");
  }
    }
    
       private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

       //**********************************************************************************************
      @FXML
    private void AjoutCommentaireBack(ActionEvent event) {
            ListView<Post> list = (ListView<Post>) affichagePostBackfx; //ListView<Post> list = affichagePostBackfx;
        PostCRUD inter = new Post1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
         Post p = list.getSelectionModel().getSelectedItem();
        P=p;
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AjoutCommentaireBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageEvenementBackController.class.getName()).log(Level.SEVERE, null, ex);
            

        }
    
}
    
    
          @FXML
    private void AffichageCommentaireBack(ActionEvent event) {
            ListView<Post> list = (ListView<Post>) affichagePostBackfx; //ListView<Post> list = affichagePostBackfx;
        PostCRUD inter = new Post1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
         Post p = list.getSelectionModel().getSelectedItem();
        P=p;
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichageCommentaireBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageEvenementBackController.class.getName()).log(Level.SEVERE, null, ex);
            

        }
    
}
    
    
    
    
}
