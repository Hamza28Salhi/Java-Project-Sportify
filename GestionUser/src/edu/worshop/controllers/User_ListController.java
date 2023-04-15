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
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class User_ListController implements Initializable {

    @FXML
    private ListView<User> User_Listfx;

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
        List<User> list2 = inter.afficher();
        for (int i = 0; i < list2.size(); i++) {
            User E = list2.get(i);
            list1.getItems().add(E); // add Matches to ListView
        }

    }
    
    @FXML
    private void SupprimerMatchesBack(ActionEvent event) {
    ListView<User> list = (ListView<User>) User_Listfx;
    ServiceUser inter = new ServiceUser();

    int selectedIndex = list.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        User E = list.getSelectionModel().getSelectedItem();
        inter.supprimer(E.getId());
        list.getItems().remove(selectedIndex);
    } else {
        showAlert("Veuillez sélectionner une Matches à supprimer.");
    }
    }  

    

}
