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
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

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
        
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 25);
        Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 16);
        EditUser.setFont(buttonfont);
        logout.setFont(buttonfont);
        Profile_Details.setFont(font);
        
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
        su.update(user);
        
        // Show a success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Profile Updated successfully!", ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    private void EditUser(ActionEvent event) {
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

}
