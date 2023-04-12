/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.ServiceUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class UserController implements Initializable {

    @FXML
    private TableColumn<User, String> dt;
    @FXML
    private TableColumn<User, String> namet;
    @FXML
    private TableColumn<User, String> emailt;
    @FXML
    private TableColumn<User, String> adresset;
    @FXML
    private TableView<User> usertable;
    ServiceUser su = new ServiceUser();
    @FXML
    private TextField idf;
    @FXML
    private TextField fullnameF;
    @FXML
    private TextField emailF;
    @FXML
    private TextField passwordF;
    @FXML
    private TextField adreeseF;
    int index = -1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         show();
    }

    @FXML
    private void ajouterclient(ActionEvent event) {
        String email = emailF.getText();
    String password = passwordF.getText();
    String address = adreeseF.getText();
    String full_name = fullnameF.getText();
    
    // Check if email is valid
    if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
        // Show an error message and return
        Alert alert = new Alert(AlertType.ERROR, "Invalid email address", ButtonType.OK);
        alert.showAndWait();
        return;
    }
    
    // Check if password is at least 8 characters long
    /*if (password.length() < 8) {
        // Show an error message and return
        Alert alert = new Alert(AlertType.ERROR, "Password must be at least 8 characters long", ButtonType.OK);
        alert.showAndWait();
        return;
    }*/
    
    // Check if address is not empty
    if (address.isEmpty()) {
        // Show an error message and return
        Alert alert = new Alert(AlertType.ERROR, "Address cannot be empty", ButtonType.OK);
        alert.showAndWait();
        return;
    }
    
    // Check if full name is not empty
    if (full_name.isEmpty()) {
        // Show an error message and return
        Alert alert = new Alert(AlertType.ERROR, "Full name cannot be empty", ButtonType.OK);
        alert.showAndWait();
        return;
    }
    
    // If all checks pass, create the user object and add it to the database
    User s = new User(email, address, password, full_name);
    su.add(s);
        show();
    }

    @FXML
    private void supprimerclient(ActionEvent event) {
        su.supprimer(new User(emailF.getText()));
        show();
    }

    /*
    @FXML
    private void modifierclient(ActionEvent event) {
       // su.modifier(new User(Integer.parseInt(idf.getText()) ,emailF.getText(),passwordF.getText(),adreeseF.getText(), fullnameF.getText()));
//su.modifier(new User(emailF.getText(), passwordF.getText(), adreeseF.getText(), fullnameF.getText()));
/*String email = emailF.getText();
      //  String password = passwordF.getText();
        String address = adreeseF.getText();
        String full_name = fullnameF.getText();
        User s = new User(email, address,  full_name);
        c.setAdresse(adressef.getText()); 
 User s = new User();
s.setAddress(adreeseF.getText());
s.setFull_name(fullnameF.getText());
        su.modifier(s);
        show(); */
/*
    User user = new User();
user.setId(Integer.parseInt(idf.getText())); 
user.setEmail(emailF.getText());
user.setPassword(""); // set empty password to indicate that it should not be changed
user.setAddress(adreeseF.getText());
user.setFull_name(fullnameF.getText());
su.update(user);
show();

        
    }*/

    public void show()
{
        ObservableList<User> User = FXCollections.observableArrayList(su.afficher());
        System.out.println("affichage" + su.afficher());
        
        dt.setCellValueFactory(new PropertyValueFactory<>("id"));
        namet.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        emailt.setCellValueFactory(new PropertyValueFactory<>("email"));
        //passwordt.setCellValueFactory(new PropertyValueFactory<>("Password"));
        adresset.setCellValueFactory(new PropertyValueFactory<>("address"));
        
       
        
        
        
        
        usertable.setItems(User);
    }

    @FXML
    private void clique(MouseEvent event) {
       User selectedUser = usertable.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
        emailF.setText(selectedUser.getEmail());
        adreeseF.setText(selectedUser.getAddress());
        //passwordF.setText(selectedUser.getPassword());
        fullnameF.setText(selectedUser.getFull_name());
        idf.setText(String.valueOf(selectedUser.getId()));

   
    
}
    }
    }
    
