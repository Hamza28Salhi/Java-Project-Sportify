/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.PasswordEncryption;
import edu.worshop.model.User;
import edu.workshop.services.ServiceUser;
import edu.worshop.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class Front_RegistrationController implements Initializable {

    @FXML
    private TextField full_nameF;
    @FXML
    private TextField emailF;
     @FXML
    private TextField passwordFF;
    @FXML
    private TextField addressF;
    @FXML
    private DatePicker dateNaissanceField;
    
    
    ServiceUser su = new ServiceUser();
    @FXML
    private ImageView imageView;
    @FXML
    private Hyperlink redirectlogin;
    @FXML
    private Button Register;
    @FXML
    private Button chooseImage;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        redirectlogin.setOnAction(event -> {
            try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Front_Login.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
            //showAlert("Error loading");
        }
            
             });
        Image defaultImage = new Image("default.png");
        imageView.setImage(defaultImage);
        
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 16);
        label.setFont(font);
        Register.setFont(buttonfont);
        chooseImage.setFont(buttonfont);
        
    }    

    @FXML
    private void Register(MouseEvent event) throws Exception {
        
        String email = emailF.getText();
        String password = PasswordEncryption.encrypt(passwordFF.getText());
        String address = addressF.getText();
        String full_name = full_nameF.getText();
        java.sql.Date dateNaissance = new java.sql.Date(Date.valueOf(dateNaissanceField.getValue()).getTime());
        

        // Check if email is valid
        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid email address", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Check if password is at least 8 characters long
        if (password.length() < 8) {
        // Show an error message and return
        Alert alert = new Alert(AlertType.ERROR, "Password must be at least 8 characters long", ButtonType.OK);
        alert.showAndWait();
        return;
    }
        // Check if address is not empty
        // Check if any field is null
    if (email.isEmpty() || password.isEmpty() || address.isEmpty() || full_name.isEmpty() || dateNaissance == null) {
        // Show an error message and return
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields", ButtonType.OK);
        alert.showAndWait();
        
    }
        if (address.isEmpty()) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Address cannot be empty", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Check if full name is not empty
        if (full_name.isEmpty()) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Full name cannot be empty", ButtonType.OK);
            alert.showAndWait();
            return;

        }
        if (su.emailExist(email)) {
        // Show an error message and return
        Alert alert = new Alert(Alert.AlertType.ERROR, "Email already exists", ButtonType.OK);
        alert.showAndWait();
        return;
    }
        // Check if password is at least 8 characters long
        if (password.length() < 8) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Password must be at least 8 characters long.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        String img_user = MyConnection.getImage_Name();
        // If all checks pass, create the user object and add it to the database
        User s = new User(email, address, password, full_name, dateNaissance,img_user);
        su.register(s);
        
        
         // Show a success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "thank you for joining Sportify!", ButtonType.OK);
        alert.showAndWait();
        
        // Load and display the login interface
    try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Front_Login.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
            //showAlert("Error loading");
        }
        
    }
    
    @FXML
    private void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        Window window = ((Node) event.getTarget()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(window);
        if (selectedFile != null) {
            try {
                // Create a directory called "upload" if it doesn't exist
                File uploadDir = new File("upload");
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                // Copy the selected file to the "upload" directory
                String fileName = selectedFile.getName();
                File destFile = new File("upload/" + fileName);
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                // Set the ImageView's image to the selected image
                Image image = new Image(destFile.toURI().toString());
                imageView.setImage(image);
                //Save the image name in img_Saver
                MyConnection.setImage_Name(fileName);
            } catch (IOException ex) {
                Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void Register(ActionEvent event) {
    }
    
}
