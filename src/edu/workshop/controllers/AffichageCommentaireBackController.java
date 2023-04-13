/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;

import edu.workshop.services.Commentaire1CRUD;
import edu.workshop.services.Post1CRUD;
import edu.worshop.interfaces.CommentaireCRUD;
import edu.worshop.interfaces.PostCRUD;
import edu.worshop.model.Commentaire;
import edu.worshop.model.Post;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author sammo
 */
public class AffichageCommentaireBackController implements Initializable {

  @FXML
    private ListView<?> affichageCommentaireBackfx;
    //static Post P ;
static int id;
static String titre_Post,contenu_Post,image_Post,auteur_Post;
static Commentaire C = new Commentaire();    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        // TODO

                ListView<Commentaire> list1 = (ListView<Commentaire>) affichageCommentaireBackfx; //ListView<Post> list1 = affichagePostBackfx;
CommentaireCRUD inter = new Commentaire1CRUD();
List<Commentaire> list2 = inter.afficherCommentaireParPostId(AffichageEvenementBackController.P.getId());
for (int i = 0; i < list2.size(); i++) {
    Commentaire C = list2.get(i);
    list1.getItems().add(C); // add Post to ListView
}

    }    
        private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void SupprimerCommentaireBack(){
        
    ListView<Commentaire> list = (ListView<Commentaire>) affichageCommentaireBackfx;
    CommentaireCRUD inter = new Commentaire1CRUD();

    int selectedIndex = list.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {      
        Commentaire C = list.getSelectionModel().getSelectedItem();
        inter.supprimerCommentaire(C.getId());
        list.getItems().remove(selectedIndex);
    } else {
        showAlert("Veuillez sélectionner un commentaire à supprimer.");
    }
}
    public void AjoutCommentaireBack(){
    
}
    public void ModifierCommentaireBack(){}
}
