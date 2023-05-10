/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import edu.worshop.utils.MyConnection;
import edu.workshop.services.ServiceUser;
/**
 * FXML Controller class
 *
 * @author azizo
 */
import edu.worshop.model.User;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;


/**
 * FXML Controller class
 *
 * @author azizo
 */
public class Front_ProfileController implements Initializable {

    @FXML
    private TextField full_namet;
    @FXML
    private TextField date_naisst;
    @FXML
    private TextField emailt;
    @FXML
    private TextField addresst;
    ServiceUser su = new ServiceUser();
    
    @FXML
    private Text Profile_Details;

    @FXML
    private ImageView ProfileImage;
    @FXML
    private Button EditUser;
    @FXML
    private Button logout;
    @FXML
    private Button chooseimage;
    @FXML
    private Text fullname;
    @FXML
    private Text dateofbirth;
    @FXML
    private Text Addresss;
    @FXML
    private Text Email;
    @FXML
    private Button logout1;
    @FXML
    private Button logout2;
    @FXML
    private Button logout21;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int userId = MyConnection.getUserId();
        User user = new ServiceUser().getUserById(userId);
        if (user != null) {
            full_namet.setText(user.getFull_name());
            emailt.setText(user.getEmail());
            addresst.setText(user.getAddress());
            date_naisst.setText(user.getDate_naissance().toString());

            File file = new File("upload/" + user.getImg_user());
            
            Image image = new Image(file.toURI().toString());
            ProfileImage.setImage(image);
        }
        
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 16);
        EditUser.setFont(buttonfont);
        logout.setFont(buttonfont);
        Profile_Details.setFont(font);
        fullname.setFont(buttonfont);
        dateofbirth.setFont(buttonfont);
        Addresss.setFont(buttonfont);
        Email.setFont(buttonfont);
        chooseimage.setFont(buttonfont);
        logout1.setFont(buttonfont);
        logout2.setFont(buttonfont);
        logout21.setFont(buttonfont);
        
    }

    @FXML
    private void EditUser(MouseEvent event) {

        int userId = MyConnection.getUserId();
        User user = new User();
        user.setId(userId);
        user.setEmail(emailt.getText());
        user.setPassword(""); // set empty password to indicate that it should not be changed
        user.setAddress(addresst.getText());
        user.setFull_name(full_namet.getText());
        String img_user = MyConnection.getImage_Name();
        user.setImg_user(img_user);
        su.updatee(user);
        
        // Show a success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Profile Updated successfully!", ButtonType.OK);
        alert.showAndWait();
    }


    @FXML
    private void logout(ActionEvent event) {
         try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/Front_Login.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                    
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }
    @FXML
    private void chooseimage(ActionEvent event) {
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
                ProfileImage.setImage(image);
                //Save the image name in img_Saver
                MyConnection.setImage_Name(fileName);
            } catch (IOException ex) {
                Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    

    @FXML
    private void redirecttoshop(ActionEvent event) {
        try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/AfficherProduitFront.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                    
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

    @FXML
    private void redirecttoblog(ActionEvent event) {
        try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/PostFront_List.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                    
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

    

    @FXML
    private void redirecttoMatch(ActionEvent event) {
        try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/AffichageMatchesFront.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                    
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

}
