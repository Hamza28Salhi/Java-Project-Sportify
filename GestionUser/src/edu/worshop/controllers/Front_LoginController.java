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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

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
    @FXML
    private TextField repeatPassword;
    @FXML
    private Label LoginLabel;
    @FXML
    private Hyperlink redirectforgetpasssword;
    @FXML
    private Hyperlink redirectregister;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        redirectforgetpasssword.setOnAction(event -> {
            try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Forget_Password.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
            //showAlert("Error loading");
        }
            
             });

        redirectregister.setOnAction(event -> {
            try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Front_Registration.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
            //showAlert("Error loading");
        }
            
             });
    }

    @FXML
    private void connexionF(ActionEvent event) throws IOException {
        String email = emaillogin.getText();
        String password = passwordlogin.getText();

        // create an instance of the UserService class
        ServiceUser userService = new ServiceUser();
        // call the authenticate method on the UserService instance
        User loggedInUser = userService.authenticate(email, password);

        if (loggedInUser != null) {
            List<String> roles = loggedInUser.getRoles();
            if (roles.contains("[ROLE_ADMIN]")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/User_List.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                    
                    Notifications.create()
                    .title("Notification")
                    .text("Welcome Back Admin!")
                    .position(Pos.BOTTOM_RIGHT)
                    .showInformation();
            } else if (roles.contains("[ROLE_USER]")) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/Front_Profile.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                    
                    Notifications.create()
                    .title("Notification")
                    .text("Welcome Back User!")
                    .position(Pos.BOTTOM_RIGHT)
                    .showInformation();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            displayErrorMessage();
        }
    }


    
    private void displayErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "plz verify input", ButtonType.OK);
        alert.showAndWait();
    }


    @FXML
    private void redirectforgetpasssword(ActionEvent event) {
    }
    
    

}
