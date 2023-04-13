/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;

import edu.workshop.services.Post1CRUD;
import edu.workshop.services.Commentaire1CRUD;
import edu.worshop.model.Post;
import edu.worshop.model.Commentaire;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sammo
 */
public class AjoutReservationFrontController implements Initializable {

    @FXML
    private TextField AuteurCfx;
    @FXML
    private TextArea ContenuCfx;
    
        Post P;
    static int post_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void AjoutCommentaireBack(ActionEvent event) {
        //String evenement_id = Evenement_idRfx.getValue();
        String auteur = AuteurCfx.getText();
        String contenu = ContenuCfx.getText();
        
         
    
    
    {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Etes vous sur de vouloir ajouter ce commentaire ?");
Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)  {


                     
                    
                    
                    
              Commentaire C = new Commentaire(AffichageEvenementBackController.P.getId(),contenu,auteur);
                    Commentaire1CRUD res1 = new Commentaire1CRUD();
                    res1.ajouterCommentaire(C);
                    System.out.println(AffichageEvenementBackController.P.getId());
                    showAlert("Commentaire ajouté successfully");
    }
    }

/*try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichageEvenementBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutEvenementBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }*/
    }
    
    
    
    
    
    
    
}
