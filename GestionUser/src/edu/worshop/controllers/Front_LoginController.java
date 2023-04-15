/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.worshop.model.User;
import edu.workshop.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class Front_LoginController implements Initializable {

    @FXML
    private TextField emaillogin;
    @FXML
    private TextField passwordlogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceUser ServiceUser = new ServiceUser();
    }

    @FXML
    private void connexionF(ActionEvent event) {
        String email = emaillogin.getText();
        String password = passwordlogin.getText();

        // create an instance of the UserService class
        ServiceUser userService = new ServiceUser();
        // call the authenticate method on the UserService instance
        User loggedInUser = userService.authenticate(email, password);

        if (loggedInUser != null) {
            List<String> roles = loggedInUser.getRoles();
            if (roles.contains("[ROLE_ADMIN]")) {
                redirectToDashboard();
            } else if (roles.contains("[ROLE_USER]")){
                redirectToProfile();
            }
        } else {
            displayErrorMessage();
        }
    }

    @FXML
    private void mdpF(ActionEvent event) {
    }

    private void redirectToDashboard() {
        try { 
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("user.fxml"));
            Parent root = loader.load();
            
          /*  emaillogin.getScene().setRoot(root);*/

            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Get the current stage
            Stage stage = (Stage) emaillogin.getScene().getWindow();

            // Set the new scene to the current stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void redirectToProfile() {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontProfile.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Get the current stage
            Stage stage = (Stage) emaillogin.getScene().getWindow();

            // Set the new scene to the current stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "plz verify input", ButtonType.OK);
        alert.showAndWait();
    } 
    
}
