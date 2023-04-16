/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import edu.workshop.services.ServiceUser;
import edu.worshop.interfaces.IService;
import edu.worshop.model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class User_ListController implements Initializable {

    @FXML
    private ListView<User> User_Listfx;
    ServiceUser sp= new ServiceUser();
    @FXML
    private TextField searchField;
    int index = -1;
    
    // Declare an ObservableList to store the users
    private ObservableList<User> userList;

    static int id;
    static Date date;
    static String full_name, email, address, password;
    static User U = new User();

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
   
  ListView<User> list1 = User_Listfx;
    ServiceUser inter = new ServiceUser();
    List<User> list2 = inter.ListUsers();
    userList = FXCollections.observableArrayList(list2);
    list1.setCellFactory(param -> new ListCell<User>() {
        @Override
        protected void updateItem(User item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                setText(item.getId() + "   |   " + item.getFull_name() + "   |   " + item.getEmail() + "   |   " + item.getAddress() + "   |   " + item.getRoles() + "   |   " + " (" + item.getDate_naissance() + ")");
            }
        }
    });

    // Ajouter une fonction de recherche
    FilteredList<User> filteredList = new FilteredList<>(userList, p -> true);
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredList.setPredicate(user -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

        String lowerCaseFilter = newValue.toLowerCase();

        if (user.getFull_name().toLowerCase().contains(lowerCaseFilter)) {
            return true; // Recherche par nom complet
        } else if (user.getEmail().toLowerCase().contains(lowerCaseFilter)) {
            return true; // Recherche par adresse e-mail
        } else if (String.valueOf(user.getId()).toLowerCase().contains(lowerCaseFilter)) {
            return true; // Recherche par ID
        } else if (user.getDate_naissance().toString().toLowerCase().contains(lowerCaseFilter)) {
            return true; // Recherche par date de naissance
        } else if (user.getAddress().toLowerCase().contains(lowerCaseFilter)) {
            return true; // Recherche par adresse
        }

        return false; // Aucune correspondance trouvée
    });
});

SortedList<User> sortedList = new SortedList<>(filteredList);
list1.setItems(sortedList);

   
    
    
    
}
    
    @FXML
    private void deleteUser(ActionEvent event) {
    ListView<User> list = (ListView<User>) User_Listfx;
    ServiceUser inter = new ServiceUser();

    int selectedIndex = list.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        User E = list.getSelectionModel().getSelectedItem();
        inter.supprimer(E);
        list.getItems().remove(selectedIndex);
    } else {
         Alert alert = new Alert(Alert.AlertType.INFORMATION, "plz select a user to delete", ButtonType.OK);
        alert.showAndWait(); 
    }
    }
    
    @FXML
    private void updateUser(ActionEvent event) {
        
        ListView<User> list = (ListView<User>) User_Listfx;
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        User e = list.getSelectionModel().getSelectedItem();
 
        int id = e.getId();
        String name = e.getFull_name();
        String email = e.getEmail();
        String address = e.getAddress();
        String password = e.getPassword();
        String img_user = e.getImg_user();
        Date date = e.getDate_naissance();
     
        U=e;
        
        
        
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/User_Update.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_UpdateController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        
    }
    
    @FXML
    private void addUser(ActionEvent event) {
           try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/User_Add.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
 
    }

    
    
   

    
       
    }

    