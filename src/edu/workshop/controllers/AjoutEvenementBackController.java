/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;

import edu.workshop.services.Post1CRUD;
import edu.worshop.interfaces.PostCRUD;
import edu.worshop.model.Post;
import edu.worshop.utils.MyConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Objects;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import static jdk.nashorn.internal.objects.NativeJava.type;
/**
 * FXML Controller class
 *
 * @author sammo
 */
public class AjoutEvenementBackController implements Initializable {

    @FXML
    private TextField TitrePostfx;
    @FXML
    private TextField AuteurPostfx;
    @FXML
    private TextArea ContenuPostfx;
    @FXML
    private TextField ImagePostfx;
    @FXML
    private ImageView imageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
        @FXML
    
    private void AjouterPostBack(ActionEvent event) {
        
        String auteur = AuteurPostfx.getText();
        String contenu = ContenuPostfx.getText();
        //String imagePost = ImagePostfx.getText();
        String titre = TitrePostfx.getText();
        
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
       contenu.length() < 50) {
            showAlert("Le contenu doit contenir au moins " + 50 + " caractères");
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
                alert.setContentText("êtes vous sûr de vouloir ajouter ce Post ?");
Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {

                String imagePost = MyConnection.getImage_Name();
              Post P = new Post(titre,contenu,imagePost,auteur);
                    Post1CRUD event1 = new Post1CRUD();
                    event1.ajouterPost(P);
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
    }
 
    
}}
   
    private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void RetourPostBack(ActionEvent event) throws IOException {
    Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichagePostBack.fxml"));
    Scene scene = new Scene(page1);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
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
            Logger.getLogger(AjoutEvenementBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
    
    
    
    
}
