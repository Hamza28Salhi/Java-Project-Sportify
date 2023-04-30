/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Post1CRUD;
import edu.worshop.interfaces.PostCRUD;
import edu.worshop.model.Post;
import edu.worshop.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
public class Post_UpdateController implements Initializable {

    @FXML
    private ImageView imageView;
    private Label label;
    @FXML
    private TextArea ContenuMfx;
    @FXML
    private TextField AuteurMfx;
    @FXML
    private TextField TitreMfx;
    @FXML
    private ImageView imageView1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
//        label.setFont(font);
        TitreMfx.setText(String.valueOf(Post_ListController.P.getTitrePost()));
        AuteurMfx.setText(String.valueOf(Post_ListController.P.getAuteurPost()));
        ContenuMfx.setText(String.valueOf(Post_ListController.P.getContenuPost()));
        
File file = new File("upload/" + Post_ListController.P.getImagePost());
            Image image = new Image(file.toURI().toString());
            imageView1.setImage(image);
    }    

private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
            imageView1.setImage(image);
            //Save the image name in img_Saver
            MyConnection.setImage_Name(fileName);
        } catch (IOException ex) {
            Logger.getLogger(Post_UpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    }
    @FXML
    private void AddUser2(MouseEvent event) {
    }


    @FXML
    private void ModifierPostBack(ActionEvent event) {
        
        PostCRUD inter = new Post1CRUD();
        int id ;
        String titre = TitreMfx.getText();
        String contenu = ContenuMfx.getText();
        String auteur = AuteurMfx.getText();
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
         }
   
         else if (titre.isEmpty()) {
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
                alert.setContentText("êtes vous sûr de vouloir modifier ce Post ?");
Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {  
             String imagePost = MyConnection.getImage_Name();
        Post even = new Post(Post_ListController.P.getId(),titre,contenu,imagePost,auteur );
                inter.modifierPost(even);
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
    private void RetourPostBack(ActionEvent event) throws IOException {
        
    Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Post_List.fxml"));
    Scene scene = new Scene(page1);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
    
}
