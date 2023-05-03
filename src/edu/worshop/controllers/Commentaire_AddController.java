/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Commentaire1CRUD;
import edu.workshop.services.ServiceUser;
import edu.worshop.model.Commentaire;
import edu.worshop.model.Post;
import edu.worshop.model.User;
import edu.worshop.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sammo
 */
public class Commentaire_AddController implements Initializable {

    @FXML
    private Label label;
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


                     
                    
                    
                    int userId = MyConnection.getUserId();
        User user = new ServiceUser().getUserById(userId);
              Commentaire C = new Commentaire(Post_ListController.P.getId(),contenu,auteur,userId);
                    Commentaire1CRUD res1 = new Commentaire1CRUD();
                    res1.ajouterCommentaire(C);
                    System.out.println(Post_ListController.P.getId());
                    showAlert("Commentaire ajouté successfully");
    }
    }

    }

    @FXML
    private void RetourPostBack(ActionEvent event) throws IOException {
    Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Commentaire_List.fxml"));
    Scene scene = new Scene(page1);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
}
