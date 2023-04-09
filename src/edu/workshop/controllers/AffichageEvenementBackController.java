/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;

import edu.workshop.services.Evenement1CRUD;
import edu.worshop.interfaces.EvenementCRUD;
import edu.worshop.model.Evenement;
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
 * @author HOUYEM
 */
public class AffichageEvenementBackController implements Initializable {

    @FXML
    private ListView<Evenement> affichageEvenementBackfx;
    //static Evenement E;
static int id;
static Date date;
static String type,lieu,description,even_pic,titre;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListView<Evenement> list1 = affichageEvenementBackfx;
EvenementCRUD inter = new Evenement1CRUD();
List<Evenement> list2 = inter.afficherEvenement();
for (int i = 0; i < list2.size(); i++) {
    Evenement E = list2.get(i);
    list1.getItems().add(E); // add Evenement to ListView
}


        }
        // TODO

    @FXML
    private void SupprimerEvenementBack(ActionEvent event) {
    ListView<Evenement> list = (ListView<Evenement>) affichageEvenementBackfx;
    EvenementCRUD inter = new Evenement1CRUD();

    int selectedIndex = list.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Evenement E = list.getSelectionModel().getSelectedItem();
        inter.supprimerEvenement(E.getId());
        list.getItems().remove(selectedIndex);
    } else {
        System.out.println("Veuillez sélectionner un événement à supprimer.");
    }
    }  
    
    

    @FXML
    private void AjoutEvenementBack(ActionEvent event) {
    
    try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AjoutEvenementBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutEvenementBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
 
}

    @FXML
    private void ModifierEvenementBack(ActionEvent event) {
        
        ListView<Evenement> list = affichageEvenementBackfx;
        EvenementCRUD inter = new Evenement1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Evenement e = list.getSelectionModel().getSelectedItem();
 
        int id = e.getId();
        Date date = e.getDate();
        String type = e.getType();
        String lieu = e.getLieu();
        String description = e.getDescription();
        String even_pic = e.getEven_pic();
        String titre = e.getTitre();
        
        
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/ModifierEvenementBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageEvenementBackController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    }
    





      
    

