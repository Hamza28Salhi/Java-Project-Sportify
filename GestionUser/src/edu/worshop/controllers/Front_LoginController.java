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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    private Label LoginLabel;
    @FXML
    private Hyperlink redirectforgetpasssword;
    @FXML
    private Hyperlink redirectregister;
    @FXML
    private Button connexionF;
    private int passwordAttempts = 0;// variable pour vérifier si l'interface
    @FXML
    private AnchorPane logoo;
    @FXML
    private ImageView logo;
    

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
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 16);
        LoginLabel.setFont(font);
        connexionF.setFont(buttonfont);
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
            
            // Bloquer l'interface si l'utilisateur saisit un mot de passe incorrect 3 fois
            passwordAttempts++;
            if (passwordAttempts == 3) {
                connexionF.setDisable(true);
                passwordlogin.setDisable(true);
                emaillogin.setDisable(true);
                LoginLabel.setDisable(true);
                redirectforgetpasssword.setDisable(true);
                 
                redirectregister.setDisable(true);
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            connexionF.setDisable(false);
                            passwordlogin.setDisable(false);
                            emaillogin.setDisable(false);
                            LoginLabel.setDisable(false);
                            redirectforgetpasssword.setDisable(false);
                            passwordAttempts = 0;
                        });
                    }
                }, 60000); // 1 minute en millisecondes
        }
    }
    }

    
    private void displayErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "plz verify input", ButtonType.OK);
        alert.showAndWait();
    }


    
    

}
