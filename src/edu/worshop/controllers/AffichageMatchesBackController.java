/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import edu.workshop.services.Matches1CRUD;
import edu.worshop.interfaces.MatchesCRUD;
import edu.worshop.model.Matches;
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
 * @author Ace River
 */
public class AffichageMatchesBackController implements Initializable {
     @FXML
    private ListView<Matches> affichageMatchesBackfx;
    //static Matches E;
static int id;   
static Date date;
static String nom,stade,score,nomEquipeId;
static Matches E = new Matches();
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        ListView<Matches> list1 = affichageMatchesBackfx;
MatchesCRUD inter = new Matches1CRUD();
List<Matches> list2 = inter.afficherMatches();
for (int i = 0; i < list2.size(); i++) {
    Matches E = list2.get(i);
    list1.getItems().add(E); // add Matches to ListView
}


        } 
    


   @FXML
    private void SupprimerMatchesBack(ActionEvent event) {
    ListView<Matches> list = (ListView<Matches>) affichageMatchesBackfx;
    MatchesCRUD inter = new Matches1CRUD();

    int selectedIndex = list.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Matches E = list.getSelectionModel().getSelectedItem();
        inter.supprimerMatches(E.getId());
        list.getItems().remove(selectedIndex);
    } else {
        showAlert("Veuillez sélectionner une Matches à supprimer.");
    }
    }  
    


  @FXML
    private void ModifierMatchesBack(ActionEvent event) {
        
        ListView<Matches> list = affichageMatchesBackfx;
        MatchesCRUD inter = new Matches1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Matches e = list.getSelectionModel().getSelectedItem();
 
        int id = e.getId();
        String nom = e.getNom();
        String stade = e.getStade();
        String score = e.getScore();
        String nomEquipeId = e.nomEquipeId.getNom();
     
        E=e;
        
        
        
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/ModifierMatchesBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageMatchesBackController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
  private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void AjoutMatchesBack(ActionEvent event) {
           try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AjoutMatchesBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutMatchesBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
 
    }

    @FXML
    private void Matchesref(ActionEvent event) {
                try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichageMatchesBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutMatchesBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
        
    }

    @FXML
    private void Equipesref(ActionEvent event) {
                try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichageEquipeBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutMatchesBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
    }


    }
